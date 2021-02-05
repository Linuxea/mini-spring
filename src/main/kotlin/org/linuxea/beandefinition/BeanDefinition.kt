package org.linuxea.beandefinition

/**
 * bean definition interface
 */
class BeanDefinition {

    lateinit var name: String

    var bean: Any? = null

    lateinit var clazz: Class<*>

    lateinit var beanProperties: BeanProperties

}

/**
 * bean property item
 */
class BeanProperty {

    lateinit var name: String

    lateinit var value: Any

}

/**
 * bean properties
 */
class BeanProperties(val properties: List<BeanProperty>)