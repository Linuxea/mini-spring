package org.linuxea.log

interface Logger {

    fun trace(message: String)

    fun info(message: String)

    fun warn(message: String)

    fun error(message: String)

    fun fatal(message: String)

}