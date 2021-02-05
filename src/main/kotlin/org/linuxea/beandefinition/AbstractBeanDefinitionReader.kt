package org.linuxea.beandefinition

import org.linuxea.resource.ResourceLoader

abstract class AbstractBeanDefinitionReader(val resourceLoader: ResourceLoader) : BeanDefinitionReader {

    val registry: MutableMap<String, BeanDefinition>

    init {
        this.registry = HashMap()
    }


}