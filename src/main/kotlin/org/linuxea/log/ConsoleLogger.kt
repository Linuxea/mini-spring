package org.linuxea.log


class ConsoleLogger(private val clazz: Class<Any>, private val formatter: LoggerFormatter) : Logger {


    constructor(clazz: Class<Any>) : this(clazz, SimpleLoggerFormatter())


    override fun trace(message: String) {
        println(this.formatter.logFormatter(this.clazz, LogLevelEnum.TRACE, message))
    }

    override fun info(message: String) {
        println(this.formatter.logFormatter(this.clazz, LogLevelEnum.INFO, message))
    }

    override fun warn(message: String) {
        println(this.formatter.logFormatter(this.clazz, LogLevelEnum.WARN, message))
    }

    override fun error(message: String) {
        println(this.formatter.logFormatter(this.clazz, LogLevelEnum.ERROR, message))
    }

    override fun fatal(message: String) {
        println(this.formatter.logFormatter(this.clazz, LogLevelEnum.FATAL, message))
    }


}