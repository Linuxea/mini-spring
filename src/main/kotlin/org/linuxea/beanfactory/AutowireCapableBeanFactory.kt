package org.linuxea.beanfactory

import org.linuxea.beandefinition.BeanDefinition
import org.linuxea.json.JackJson
import org.linuxea.log.ConsoleLogger

/**
 * 自动装配 bean factory
 */
class AutowireCapableBeanFactory : AbstractBeanFactory() {

    private val log = ConsoleLogger(this.javaClass)
    private val json = JackJson()

    override fun applyPropertyValues(bean: Any, beanDefinition: BeanDefinition) {
        val beanProperties = beanDefinition.beanProperties
        val properties = beanProperties.properties
        log.info("bean's properties ${json.toJsonString(properties)}")
        properties.forEach {
            val name = it.name
            val value = it.value
            val setMethodPrefix = "set"
            val methodName = setMethodPrefix + name.capitalize()
            log.info("methodName $methodName")
            val declaredMethod = bean.javaClass.getDeclaredMethod(methodName, value.javaClass)
            declaredMethod.isAccessible = true
            declaredMethod.invoke(bean, value)
        }

    }


}