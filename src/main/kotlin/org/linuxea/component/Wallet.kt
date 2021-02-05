package org.linuxea.component

import org.linuxea.log.ConsoleLogger

data class Wallet(var userName: String?, var addMoney: Int?, var subMoney: Int?) {

    constructor() : this(null, null, null)


    private val log = ConsoleLogger(this.javaClass)

    fun look() {
        log.info("${this.userName} 累计金额 ${this.addMoney}, 累计花费 ${this.subMoney}")
    }

}