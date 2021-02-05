package org.linuxea.applicationcontext

import org.linuxea.beanfactory.AbstractBeanFactory

abstract class AbstractApplicationContext(beanFactory: AbstractBeanFactory?) : ApplicationContext {

    private var beanFactory: AbstractBeanFactory = beanFactory!!


    open fun refresh() {
        loadBeanDefinitions(beanFactory)
        registerBeanPostProcessors(beanFactory)
        onRefresh()
    }

    protected abstract fun loadBeanDefinitions(beanFactory: AbstractBeanFactory?)

    protected open fun registerBeanPostProcessors(beanFactory: AbstractBeanFactory) {
//        val beanPostProcessors: List<*> = beanFactory.getBeansForType(BeanPostProcessor::class.java)
//        for (beanPostProcessor in beanPostProcessors) {
//            beanFactory.addBeanPostProcessor(beanPostProcessor as BeanPostProcessor?)
//        }
    }

    @Throws(Exception::class)
    protected open fun onRefresh() {
//        beanFactory.preInstantiateSingletons()
    }


    override fun getBean(name: String): Any {
        return beanFactory.getBean(name)
    }
}