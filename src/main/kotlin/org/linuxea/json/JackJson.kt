package org.linuxea.json

import com.fasterxml.jackson.databind.ObjectMapper

class JackJson {

    val objectMapper = ObjectMapper()


    fun <T> toJsonString(obj: T): String {
        return objectMapper.writeValueAsString(obj)
    }


    inline fun <reified T> toObj(jsonString: String): T {
        return objectMapper.readValue(jsonString, T::class.java)
    }

    fun <T> parseArray(json: String, tClass: Class<T>): List<T> {
        val typeFactory = objectMapper.typeFactory
        return objectMapper.readValue(json, typeFactory.constructCollectionType(MutableList::class.java, tClass))
    }

}