import java.lang.Character;
public class SumDoubleSpace {
	public static void main(String[] args) {
		Double sum = 0.0;
		for(String s : args) {
			int n = 0;
			int m = 0;
			for(int j = 0; j < s.length() - 1; j++) {
				if (Character.getType(s.charAt(j)) != Character.SPACE_SEPARATOR) {
					m += 1;
				}
				else {
					if (n != m) {
						sum += Double.parseDouble(s.substring(n, m));
					}
					n = j + 1;
					m = j + 1;
				}
		    }
		    if (Character.getType(s.charAt(s.length() - 1)) != Character.SPACE_SEPARATOR) {
				sum += Double.parseDouble(s.substring(n, s.length()));
			}
			else {
				if (n != m) {
					sum += Double.parseDouble(s.substring(n, m));
				}
			}
		}
		System.out.println(sum);
	}	
}