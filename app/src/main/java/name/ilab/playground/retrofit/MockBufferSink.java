package name.ilab.playground.retrofit;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

/**
 * Created by cuijfboy on 16/8/29.
 */
public class MockBufferSink implements BufferedSink {

    @Override
    public Buffer buffer() {
        return null;
    }

    @Override
    public BufferedSink write(ByteString byteString) throws IOException {
        System.out.println("MockBufferSink.write");
        System.out.println("byteString = " + byteString);
        return null;
    }

    @Override
    public BufferedSink write(byte[] source) throws IOException {
        System.out.println("MockBufferSink.write");
        System.out.println("source = " + source);
        return null;
    }

    @Override
    public BufferedSink write(byte[] source, int offset, int byteCount) throws IOException {
        System.out.println("MockBufferSink.write");
        System.out.println("source = " + source);
        System.out.println("offset = " + offset);
        System.out.println("byteCount = " + byteCount);
        String contentString = new String(source);
        System.out.println("contentString = " + contentString);
        return this;
    }

    @Override
    public long writeAll(Source source) throws IOException {
        System.out.println("MockBufferSink.writeAll");
        return 0;
    }

    @Override
    public BufferedSink write(Source source, long byteCount) throws IOException {
        System.out.println("MockBufferSink.write");
        System.out.println("source = " + source);
        System.out.println("byteCount = " + byteCount);
        return null;
    }

    @Override
    public BufferedSink writeUtf8(String string) throws IOException {
        System.out.println("MockBufferSink.writeUtf8");
        return null;
    }

    @Override
    public BufferedSink writeUtf8(String string, int beginIndex, int endIndex) throws IOException {
        System.out.println("MockBufferSink.writeUtf8");
        return null;
    }

    @Override
    public BufferedSink writeUtf8CodePoint(int codePoint) throws IOException {
        System.out.println("MockBufferSink.writeUtf8CodePoint");
        return null;
    }

    @Override
    public BufferedSink writeString(String string, Charset charset) throws IOException {
        System.out.println("MockBufferSink.writeString");
        return null;
    }

    @Override
    public BufferedSink writeString(String string, int beginIndex, int endIndex, Charset charset) throws IOException {
        System.out.println("MockBufferSink.writeString");
        return null;
    }

    @Override
    public BufferedSink writeByte(int b) throws IOException {
        System.out.println("MockBufferSink.writeByte");
        return null;
    }

    @Override
    public BufferedSink writeShort(int s) throws IOException {
        System.out.println("MockBufferSink.writeShort");
        return null;
    }

    @Override
    public BufferedSink writeShortLe(int s) throws IOException {
        return null;
    }

    @Override
    public BufferedSink writeInt(int i) throws IOException {
        return null;
    }

    @Override
    public BufferedSink writeIntLe(int i) throws IOException {
        return null;
    }

    @Override
    public BufferedSink writeLong(long v) throws IOException {
        return null;
    }

    @Override
    public BufferedSink writeLongLe(long v) throws IOException {
        return null;
    }

    @Override
    public BufferedSink writeDecimalLong(long v) throws IOException {
        return null;
    }

    @Override
    public BufferedSink writeHexadecimalUnsignedLong(long v) throws IOException {
        return null;
    }

    @Override
    public void flush() throws IOException {

    }

    @Override
    public BufferedSink emit() throws IOException {
        return null;
    }

    @Override
    public BufferedSink emitCompleteSegments() throws IOException {
        return null;
    }

    @Override
    public OutputStream outputStream() {
        return null;
    }

    @Override
    public void write(Buffer source, long byteCount) throws IOException {

    }

    @Override
    public Timeout timeout() {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
