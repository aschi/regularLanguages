package ch.zhaw.regularLanguages.languages;

import java.util.Arrays;

public class CharArrayWrapper{
	private char[] data;

	public CharArrayWrapper(char[] data) {
		this.data = data;
	}
	
	public char[] getData(){
		return data;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof CharArrayWrapper)) {
			return false;
		}
		return Arrays.equals(data, ((CharArrayWrapper) other).data);
	}

	@Override
	public String toString(){
		StringBuffer rv = new StringBuffer("[");
		for(char c : data){
			rv.append(c);
			rv.append(',');
		}
		rv.deleteCharAt(rv.length()-1);
		rv.append("]");
		return rv.toString();
	}
	
	@Override
	public int hashCode() {
		return Arrays.hashCode(data);
	}
}
