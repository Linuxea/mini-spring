package org.linuxea.beandefinition

import org.linuxea.json.JackJson
import org.linuxea.log.ConsoleLogger
import org.linuxea.resource.ResourceLoader
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class JsonBeanDefinitionReader(resourceLoader: ResourceLoader) : AbstractBeanDefinitionReader(resourceLoader) {

    private val log = ConsoleLogger(this.javaClass)
    private val jackJson = JackJson()

    override fun loadBeanDefinitions(location: String) {
        val resource = super.resourceLoader.loader(location)
        log.info("resource $resource")
        val inputStream = resource.inputStream()
        processJsonInputStream(inputStream)
    }

    private fun processJsonInputStream(inputStream: InputStream) {
        val inputStreamReader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        val lines = bufferedReader.readLines().joinToString(separator = " ").replace(Regex("\\s+"), " ")
        log.info("json 文本内容 $lines")
        inputStream.close()
        inputStreamReader.close()
        bufferedReader.close()
        val beanDefinitionStructures: List<BeanDefinitionStructure> =
            jackJson.parseArray(lines, BeanDefinitionStructure().javaClass)
        log.info("beanDefinitionStructures is $beanDefinitionStructures")
        buildBeanDefinition(beanDefinitionStructures)
        log.info(jackJson.toJsonString(super.registry))

    }

    private fun buildBeanDefinition(beanDefinitionStructures: List<BeanDefinitionStructure>) {
        beanDefinitionStructures.forEach {
            val beanDefinition = BeanDefinition()
            beanDefinition.name = it.name
            beanDefinition.clazz = Class.forName(it.clazzPath)
            val properties = it.properties
            val beanProperties = properties.map { property ->
                val beanProperty = BeanProperty()
                // set name
                beanProperty.name = property.name
                // set value
                if (property.value != null) {
                    beanProperty.value = property.value!!
                } else {
                    val beanReference = BeanReference()
                    beanReference.name = property.name
                    beanReference.bean = property.ref!!
                    beanProperty.value = beanReference
                }
                beanProperty
            }.toList()
            beanDefinition.beanProperties = BeanProperties(beanProperties)
            super.registry[it.name] = beanDefinition
        }
    }

}