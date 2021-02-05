package org.linuxea.beanfactory

import org.linuxea.beandefinition.BeanDefinition
import java.util.concurrent.ConcurrentHashMap

abstract class AbstractBeanFactory : BeanFactory {

    private val beanDefinitionMap: MutableMap<String, BeanDefinition> = ConcurrentHashMap()


    /**
     * register bean definition
     * continuous add
     */
    open fun registerBeanDefinition(name: String, beanDefinition: BeanDefinition) {
        beanDefinitionMap[name] = beanDefinition
    }

    @Suppress("SimplifyBooleanWithConstants")
    override fun getBean(name: String): Any {
        if (beanDefinitionMap.containsKey(name) == false) {
            throw Exception("no such bean definition $name")
        }
        val beanDefinition = beanDefinitionMap[name]!!
        val bean = beanDefinition.bean
        if (null == bean) {
            val clazz = beanDefinition.clazz
            // 实例化
            beanDefinition.bean = clazz.newInstance()
            // 实例属性填充
            this.applyPropertyValues(beanDefinition.bean!!, beanDefinition)
        }

        return beanDefinition.bean!!

    }


    protected abstract fun applyPropertyValues(bean: Any, beanDefinition: BeanDefinition)

}