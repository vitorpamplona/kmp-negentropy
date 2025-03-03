/**
 * Copyright (c) 2024 Vitor Pamplona
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.vitorpamplona.negentropy.message

import kotlin.test.Test
import kotlin.test.assertEquals

class VarIntTest {
    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun testEncodeVarInt() {
        assertEquals("00", encodeVarInt(0).toHexString())
        assertEquals("01", encodeVarInt(1).toHexString())
        assertEquals("02", encodeVarInt(2).toHexString())
        assertEquals("7f", encodeVarInt(127).toHexString())
        assertEquals("8100", encodeVarInt(128).toHexString())
        assertEquals("817f", encodeVarInt(255).toHexString())
        assertEquals("8200", encodeVarInt(256).toHexString())
        assertEquals("83ff7f", encodeVarInt(65535).toHexString())
        assertEquals("848000", encodeVarInt(65536).toHexString())
        assertEquals("848001", encodeVarInt(65537).toHexString())
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun testDecodeVarInt() {
        assertEquals(0, MessageConsumer("00".hexToByteArray()).decodeVarInt())
        assertEquals(1, MessageConsumer("01".hexToByteArray()).decodeVarInt())
        assertEquals(2, MessageConsumer("02".hexToByteArray()).decodeVarInt())
        assertEquals(127, MessageConsumer("7f".hexToByteArray()).decodeVarInt())
        assertEquals(128, MessageConsumer("8100".hexToByteArray()).decodeVarInt())
        assertEquals(255, MessageConsumer("817f".hexToByteArray()).decodeVarInt())
        assertEquals(256, MessageConsumer("8200".hexToByteArray()).decodeVarInt())
        assertEquals(65535, MessageConsumer("83ff7f".hexToByteArray()).decodeVarInt())
        assertEquals(65536, MessageConsumer("848000".hexToByteArray()).decodeVarInt())
        assertEquals(65537, MessageConsumer("848001".hexToByteArray()).decodeVarInt())
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun testDecodeVarIntByteArray() {
        assertEquals(0, decodeVarInt("00".hexToByteArray()))
        assertEquals(1, decodeVarInt("01".hexToByteArray()))
        assertEquals(2, decodeVarInt("02".hexToByteArray()))
        assertEquals(127, decodeVarInt("7f".hexToByteArray()))
        assertEquals(128, decodeVarInt("8100".hexToByteArray()))
        assertEquals(255, decodeVarInt("817f".hexToByteArray()))
        assertEquals(256, decodeVarInt("8200".hexToByteArray()))
        assertEquals(65535, decodeVarInt("83ff7f".hexToByteArray()))
        assertEquals(65536, decodeVarInt("848000".hexToByteArray()))
        assertEquals(65537, decodeVarInt("848001".hexToByteArray()))
    }
}
