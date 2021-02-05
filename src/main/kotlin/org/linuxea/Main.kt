package org.linuxea

import org.linuxea.applicationcontext.ApplicationContext
import org.linuxea.applicationcontext.ClassPathJsonApplicationContext
import org.linuxea.component.Person

fun main() {

    val applicationContext: ApplicationContext = ClassPathJsonApplicationContext("bean.json")
    val person = applicationContext.getBean("linuxea") as Person
    person.say()

}