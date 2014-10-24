
public class StringTest {

	public static void main(String[] args) {
		String s1 = new StringBuilder("ja").append("va").toString();
		System.out.println(s1 == s1.intern());
		
		String s2 = new StringBuilder("micro").append("soft").toString();
		System.out.println(s2 == s2.intern());
	}
}
