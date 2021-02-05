package org.linuxea.log

import java.text.SimpleDateFormat
import java.util.*

class SimpleLoggerFormatter : LoggerFormatter {


    override fun logFormatter(clazz: Class<Any>, levelEnum: LogLevelEnum, message: String): String {
        val nowPrettyString = SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(Date())
        return "[$nowPrettyString]-[${Thread.currentThread().name}]-[${clazz.name}]\t[${levelEnum.name}]-[${message}]"
    }

}