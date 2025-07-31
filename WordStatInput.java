import java.util.*;
import java.io.*;
public class WordStatInput {
	public static void main(String[] args) {
		String[] words = new String[1];
		int[] count = new int[1];
		int slova = 0;
		int k = 0;
		int p = 0;
		int l = 0;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "UTF-8"));
			try {
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8"));
				try {
					while (true) {
	                    String line = reader.readLine();
	                    if (line != null) {
	                    	line = line.toLowerCase();
	                    	int n = 0;
	                    	int m = 0;
	                    	String s = "";
	                    	for (int i = 0; i < line.length() - 1; i++) {
								if ((Character.getType(line.charAt(i)) == Character.DASH_PUNCTUATION) || 
								(Character.isLetter(line.charAt(i))) || 
								(line.charAt(i) == '\'')) {
									m++;
								}
								else {
									if (n != m) {
										s = line.substring(n, m);
										String[] words1;
										if (slova >= words.length) {
											words1 = Arrays.copyOf(words, words.length * 2);
											words = words1;
										}
										k = 0;
										for (int j = 0; j < slova; j++) {
											if (s.equals(words[j])) {
												k++;
												l = j;
												break;
											}
										}
										if (k == 0) {
											slova++;
											words[slova - 1] = s;
											int[] count1;
											if (p >= count.length) {
												count1 = Arrays.copyOf(count, count.length * 2);
												count = count1;
											}
											count[p] = 1;
											p++;
										}
										else {
											count[l] += 1;
										}
									}
									n = i + 1;
									m = i + 1;
								}
							}
							if (line.length() != 0) {
								if ((Character.getType(line.charAt(line.length() - 1)) == Character.DASH_PUNCTUATION) || 
								(Character.isLetter(line.charAt(line.length() - 1))) || 
								(line.charAt(line.length() - 1) == '\'')){
									m++;
								}
							}
							if (n != m) {
								s = line.substring(n, m);
								String[] words1;
								if (slova >= words.length) {
									words1 = Arrays.copyOf(words, words.length * 2);
									words = words1;
								}
								k = 0;
								for (int j = 0; j < slova; j++) {
									if (s.equals(words[j])) {
										k++;
										l = j;
										break;
									}
								}
								if (k == 0) {
									slova++;
									words[slova - 1] = s;
									int[] count1;
									if (p >= count.length) {
										count1 = Arrays.copyOf(count, count.length * 2);
										count = count1;
									}
									count[p] = 1;
									p++;
								}
								else {
									count[l] += 1;
								}
							}
	                    }
	                    else {
	                    	break;
	                    }
	                }
				} finally {
					for (int i = 0; i < slova; i++) {
						writer.write(words[i]);
						writer.write(" ");
						writer.write(count[i] + "\n");
					}
					writer.close();
				}
			} finally {
				reader.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println(1);
		} catch (UnsupportedEncodingException e) {
			System.out.println(2);
		} catch (IOException e) {
			System.out.println(3);
		}
	}
}