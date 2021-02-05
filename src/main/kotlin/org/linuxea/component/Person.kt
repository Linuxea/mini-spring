package org.linuxea.component

import org.linuxea.log.ConsoleLogger

data class Person(var name: String? = null, var sex: String? = null, var age: Int? = 0) {

    private val log = ConsoleLogger(this.javaClass)

    fun say() {
        log.warn("hello my name is $name and sex is $sex, my age is $age")
    }


}