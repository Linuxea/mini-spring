package org.linuxea.beanfactory

interface BeanFactory {

    fun getBean(name: String): Any

}