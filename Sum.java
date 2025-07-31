public class Sum {
	public static void main(String[] args) {
		int sum = 0;
		for(int i = 0; i < args.length; i++) {
			String s = args[i];
			String l = "0";
			int n = 0;
			for(int j = 0; j < s.length(); j++) {
				if (!Character.isWhitespace(s.charAt(j))) {
					l = s.substring(n, j + 1);
					if (j == s.length() - 1) {
						sum += Integer.parseInt(l);
					}
				}
				else {
					n = j + 1;
					sum += Integer.parseInt(l);
					l = "0";
				}
		    }
		}
		System.out.println(sum);
	}	
}