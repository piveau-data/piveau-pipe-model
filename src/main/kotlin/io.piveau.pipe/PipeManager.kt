package io.piveau.pipe

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.util.*

class PipeManager private constructor(val pipe: Pipe) {
    companion object {
        @JvmStatic
        fun create(pipe: Pipe): PipeManager = PipeManager(pipe)
        @JvmStatic
        fun create(resource: String): PipeManager = PipeManager(loadPipe(resource)!!)
    }

    val currentSegment = pipe.body.segments.filterNot { it.header.processed }.minBy { it.header.segmentNumber }

    val config: JsonNode
        get() = currentSegment?.body?.config ?: jacksonObjectMapper().createObjectNode()

    val title: String?
        get() = currentSegment?.header?.title

    val data: String
        get() = currentSegment?.body?.payload?.body?.data ?: ""

    val binaryData: ByteArray
        get() = Base64.getDecoder().decode(data)

    val isBase64Payload: Boolean
        get() = currentSegment?.body?.payload?.header?.dataType == DataType.base64

    val mimeType: String?
        get() = currentSegment?.body?.payload?.body?.dataMimeType

    val dataInfo: ObjectNode
        get() = currentSegment?.body?.payload?.body?.dataInfo ?: jacksonObjectMapper().createObjectNode()

    val nextSegment: Segment?
        get() = pipe.body.segments.filterNot { it.header.processed }.filter { it.header.segmentNumber > currentSegment?.header?.segmentNumber ?: 0 }.minBy { it.header.segmentNumber }

    val currentEndpoint: Endpoint?
        get() = currentSegment?.body?.endpoint

    val nextEndpoint: Endpoint?
        get() = nextSegment?.body?.endpoint

    fun finished(processed: Boolean) {
        currentSegment?.header?.processed = processed
    }

    fun setPayloadData(
        data: String,
        dataType: DataType = DataType.text,
        mimeType: String? = null,
        dataInfo: ObjectNode? = null
    ) {
        nextSegment?.let {
            it.body.payload = Payload(
                header = PayloadHeader(
                    dataType = dataType,
                    seqNumber = 0
                ),
                body = PayloadBody(
                    data = data,
                    dataMimeType = mimeType,
                    dataInfo = dataInfo
                )
            )
        }
    }

    fun setPayloadData(data: ByteArray, mimeType: String? = null, dataInfo: ObjectNode? = null) {
        setPayloadData(Base64.getEncoder().encodeToString(data), DataType.base64, mimeType, dataInfo)
    }

    fun getProcessedPipe(): Pipe {
        finished(true)
        val processedPipe = jacksonObjectMapper().readValue<Pipe>(jacksonObjectMapper().writeValueAsString(pipe))
        finished(false)
        return processedPipe
    }

    fun prettyPrint(): String = jacksonObjectMapper().writeValueAsString(pipe)

}
