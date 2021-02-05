package org.linuxea.resource

import java.io.InputStream

interface Resource {

    fun inputStream(): InputStream

}