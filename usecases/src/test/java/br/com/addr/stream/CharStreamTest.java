package br.com.addr.stream;

import org.junit.Test;

import static org.junit.Assert.*;

public class CharStreamTest {


    @Test
    public void testStreamHasNext() throws Exception {
        //Given
        Stream stream = new CharStream("a");
        //When
        boolean hasNext = stream.hasNext();
        char next = stream.getNext();
        //then
        assertTrue(hasNext);
        assertEquals('a', next);
    }

    @Test
    public void testStreamDoesNotHaveNext() throws Exception {
        //Given
        Stream stream = new CharStream("a");
        //When
        char next = stream.getNext();
        boolean hasNext = stream.hasNext();
        //then
        assertFalse(hasNext);
        assertEquals('a', next);
    }


    @Test
    public void testStreamCanGetNext() throws Exception {
        //Given
        Stream stream = new CharStream("ab");
        //When
        boolean hasNext = stream.hasNext();
        char next = stream.getNext();
        //then
        assertTrue(hasNext);
        assertEquals('a', next);

        //When
        hasNext = stream.hasNext();
        next = stream.getNext();
        //Then
        assertTrue(hasNext);
        assertEquals('b', next);

        //When
        hasNext = stream.hasNext();
        //Then
        assertFalse(hasNext);
    }
}
