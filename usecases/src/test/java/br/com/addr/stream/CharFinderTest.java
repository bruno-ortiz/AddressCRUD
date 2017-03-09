package br.com.addr.stream;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CharFinderTest {

    @Test
    public void testCanFindChar() throws Exception {
        //Given
        Stream stream = new CharStream("aAbBABacafe");
        //When
        char c = CharFinder.firstChar(stream);
        //Then
        assertEquals('e', c);
    }

    @Test
    public void testCanFindFirst() throws Exception {
        //Given
        Stream stream = new CharStream("aAbiBABacafe");
        //When
        char c = CharFinder.firstChar(stream);
        //Then
        assertEquals('i', c);
    }

    @Test(expected = IllegalStateException.class)
    public void testCantFindChar() throws Exception {
        //Given
        Stream stream = new CharStream("aAbeBABacafe");
        //When
        CharFinder.firstChar(stream);
    }

    @Test(expected = IllegalStateException.class)
    public void testStreamIsTooSmall() throws Exception {
        //Given
        Stream stream = new CharStream("ab");
        //When
        CharFinder.firstChar(stream);
    }
}
