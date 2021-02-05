package org.linuxea.resource


class ResourceLoader {


    fun loader(location: String): Resource {
        val resource = this.javaClass.classLoader.getResource(location)!!
        return URLResource(resource)
    }

}