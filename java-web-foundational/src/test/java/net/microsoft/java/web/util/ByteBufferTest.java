package net.microsoft.java.web.util;

import org.testng.annotations.Test;

import java.nio.ByteBuffer;

/**
 * @description:测试Bytebuffer
 * @Date on 2022/5/10
 * @author: suche
 **/

public class ByteBufferTest {

    @Test
    public void testByteBufferPosition() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put((byte) 100);
        byteBuffer.put((byte) 101);
        final int position = byteBuffer.position();
        byteBuffer.flip();
        System.out.println(position);
    }
}
