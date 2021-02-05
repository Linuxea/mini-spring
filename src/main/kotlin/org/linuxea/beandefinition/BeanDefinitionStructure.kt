package org.linuxea.beandefinition

class BeanDefinitionStructure {

    lateinit var name: String

    lateinit var clazzPath: String

    lateinit var properties: List<BeanDefinitionStructureProperty>


    class BeanDefinitionStructureProperty {

        lateinit var name: String

        var value: Any? = null

        var ref: String? = null
    }


}

