package io.piveau.pipe

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PipeTest {

    @Test
    fun pipeTest() {
        val pipe = loadPipe("pipe.json")!!
        validatePipe(pipe)
    }
}
