package org.linuxea.log

interface LoggerFormatter {

    fun logFormatter(clazz: Class<Any>, levelEnum: LogLevelEnum, message: String): String
}