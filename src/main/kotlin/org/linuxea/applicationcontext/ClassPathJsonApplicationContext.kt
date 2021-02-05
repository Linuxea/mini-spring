package org.linuxea.applicationcontext

import org.linuxea.beandefinition.JsonBeanDefinitionReader
import org.linuxea.beanfactory.AbstractBeanFactory
import org.linuxea.beanfactory.AutowireCapableBeanFactory
import org.linuxea.resource.ResourceLoader

class ClassPathJsonApplicationContext(private var location: String, private val beanFactory: AbstractBeanFactory?) :
    AbstractApplicationContext(beanFactory) {

    constructor(location: String) : this(location, AutowireCapableBeanFactory())

    init {
        super.refresh()
    }


    override fun loadBeanDefinitions(beanFactory: AbstractBeanFactory?) {
        val beanDefinitionReader = JsonBeanDefinitionReader(ResourceLoader())
        beanDefinitionReader.loadBeanDefinitions(this.location)
        for ((key, value) in beanDefinitionReader.registry.entries) {
            beanFactory!!.registerBeanDefinition(key, value)
        }
    }
}