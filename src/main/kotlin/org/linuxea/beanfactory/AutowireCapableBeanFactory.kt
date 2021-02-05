package org.linuxea.beanfactory

import org.linuxea.beandefinition.BeanDefinition
import org.linuxea.beandefinition.BeanReference
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
        log.info("${bean.javaClass} bean's properties ${json.toJsonString(properties)}")
        properties.forEach {
            val name = it.name
            var value = it.value

            if (value is BeanReference) {
                val subBean = super.getBean(value.name)
                value = subBean
            }

            val setMethodPrefix = "set"
            val methodName = setMethodPrefix + name.capitalize()
            log.info("methodName $methodName")
            try {
                val declaredMethod = bean.javaClass.getDeclaredMethod(methodName, value.javaClass)
                declaredMethod.isAccessible = true
                declaredMethod.invoke(bean, value)
            } catch (exception: NoSuchMethodException) {
                log.warn("${bean.javaClass} has no method $methodName ${value.javaClass}")
                // use field injection
                // field has no overload
                val declaredField = bean.javaClass.getDeclaredField(name)
                declaredField.isAccessible = true
                declaredField.set(bean, value)
            }

        }

    }


}