package ch.zhaw.regularLanguages.languages;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CharArrayWrapperTest {
	char[] testData1 = {'a','b','c'};
	char[] testData2 = {'c','b','a'};
	
	CharArrayWrapper caw1 = new CharArrayWrapper(testData1);
	CharArrayWrapper caw2 = new CharArrayWrapper(testData2);
	CharArrayWrapper caw1_1 = new CharArrayWrapper(testData1);
	
	@Test
	public void testGetData() {
		assertTrue(caw1.getData().equals(testData1));
	}

	@Test
	public void testEqualsObject1() {
		assertTrue(caw1.equals(caw1_1));
	}
	
	@Test
	public void testEqualsObject2(){
		assertTrue(!caw1.equals(caw2));
	}
	
	@Test
	public void testToString() {
		assertTrue(caw1.toString().equals("[a,b,c]"));
	}

}
