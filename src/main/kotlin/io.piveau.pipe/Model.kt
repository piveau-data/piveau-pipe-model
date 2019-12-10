package io.piveau.pipe

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import java.util.*
import com.fasterxml.jackson.module.kotlin.*

enum class Transport {
    payload
}

enum class DataType {
    text,
    base64
}

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class Credentials(
    var username: String? = null,
    var password: String? = null
)

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class Authentication(
    var authMethod: String? = null,
    var credentials: Credentials? = null
)

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class Endpoint(
    var protocol: String? = null,
    var address: String? = null,
    var method: String? = null,
    var authentication: Authentication? = null
)

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class Segment(
    var header: SegmentHeader = SegmentHeader(),
    var body: SegmentBody = SegmentBody()
)

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class SegmentHeader(
    var id: String? = null,
    var name: String = "",
    var segmentNumber: Int = 0,
    var title: String? = null,
    var processed: Boolean = false,
    var errorHandlerId: String? = null
)

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class SegmentBody(
    var endpoint: Endpoint? = null,
    var config: JsonNode? = null,
    var payload: Payload? = null
)

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class Payload(
    var header: PayloadHeader = PayloadHeader(),
    var body: PayloadBody = PayloadBody()
)

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class PayloadHeader(
    var seqNumber: Int = 0,
    var dataType: DataType = DataType.text,
    var total: Int? = null,
    var signed: Boolean? = null,
    var signature: String? = null,
    var signee: String? = null,
    var encrypted: Boolean? = null
)

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class PayloadBody(
    var key: String? = null,
    var cypher: String? = null,
    var dataMimeType: String? = null,
    var data: String = "",
    var dataInfo: ObjectNode? = null
)

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class Pipe(
    var header: PipeHeader = PipeHeader(),
    var body: PipeBody = PipeBody()
)

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class PipeHeader(
    var id: String = "",
    var name: String = "",
    var title: String? = null,
    var version: String = "1.0.0",
    var context: String? = null,
    var transport: Transport = Transport.payload,
    var runId: String? = null,
    var startTime: Date? = null,
    var lastRun: Date? = null
)

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class PipeBody(
    var segments: List<Segment> = mutableListOf(),
    var payloads: List<Payload>? = null
)

fun loadPipe(resource: String): Pipe? = object {}.javaClass.classLoader.getResourceAsStream(resource)?.let {
    jacksonObjectMapper().readValue<Pipe>(it).apply {
        if (header.id.isBlank()) header.id = UUID.randomUUID().toString()
    }
}

fun Pipe.prettyPrint(): String = jacksonObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this)
