package org.linuxea.resource

import java.io.InputStream
import java.net.URL

class URLResource(private val url: URL) : Resource {

    override fun inputStream(): InputStream {
        val openConnection = url.openConnection()
        openConnection.connect()
        return openConnection.getInputStream()
    }
}