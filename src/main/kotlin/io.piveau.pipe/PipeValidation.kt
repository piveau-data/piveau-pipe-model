package io.piveau.pipe

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.everit.json.schema.loader.SchemaLoader
import org.everit.json.schema.ValidationException
import org.json.JSONObject
import org.json.JSONTokener

object PipeSchema {
    val schema = SchemaLoader.load(JSONObject(JSONTokener(object {}.javaClass.classLoader.getResourceAsStream("piveau-pipe.schema.json"))))

    fun validate(pipe: JSONObject): Boolean = try {
        schema.validate(pipe)
        true
    } catch (e: ValidationException) {
        false
    }

}

fun validatePipe(pipe: Pipe): Boolean = PipeSchema.validate(JSONObject(jacksonObjectMapper().writeValueAsString(pipe)))
