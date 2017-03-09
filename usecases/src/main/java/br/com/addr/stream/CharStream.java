package br.com.addr.stream;

public class CharStream implements Stream {

    private final char[] stream;
    private int streamIdx = 0;

    public CharStream(String value) {
        this.stream = value.toCharArray();
    }

    @Override
    public char getNext() {
        if (!hasNext()) {
            throw new ArrayIndexOutOfBoundsException("Stream doest not have any more values");
        }
        return stream[streamIdx++];
    }

    @Override
    public boolean hasNext() {
        return streamIdx < stream.length;
    }
}
