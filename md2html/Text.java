package md2html;

import java.util.Map;

public class Text {

    private final Map<String, Integer> m = Map.of(
            "*", 0,
            "_", 1,
            "**", 2,
            "__", 3,
            "--", 4,
            "`", 5,
            "%", 6
    );
    private final String[] html = new String[]{"em", "em", "strong", "strong", "s", "code", "var"};
    private final String[] md = new String[]{"*", "_", "**", "__", "--", "`", "%"};

    private final StringBuilder inputText;

    public Text(StringBuilder inputText) {
        this.inputText = inputText;
    }

    private Integer TagPosition(String s) {
        if (m.containsKey(s)) {
            return m.get(s);
        }
        return m.getOrDefault(Character.toString(s.charAt(0)), null);
    }

    void toHtml(StringBuilder ans) {
        int[] n = new int[m.size()];
        for (int i = 0; i < inputText.length(); i++) {
            String s;
            if (i + 2 < inputText.length()) {
                s = inputText.substring(i, i + 2);
            } else {
                s = inputText.substring(i, inputText.length());
            }
            if (inputText.charAt(i) == '\\') {
                i++;
                continue;
            }
            Integer pos = TagPosition(s);
            if (pos != null) {
                n[pos]++;
            }
        }
        for (int j = 0; j < 7; j++) {
            if (n[j] % 2 == 1) {
                n[j]--;
            }
        }
        int[] pos = new int[m.size()];
        for (int i = 0; i < inputText.length(); i++) {
            char c = inputText.charAt(i);
            if (c == '<') {
                ans.append("&lt;");
                continue;
            } else if (c == '>') {
                ans.append("&gt;");
                continue;
            } else if (c == '&') {
                ans.append("&amp;");
                continue;
            } else if (c == '\\') {
                continue;
            }
            String s;
            if (i + 2 < inputText.length()) {
                s = inputText.substring(i, i + 2);
            } else {
                s = inputText.substring(i, inputText.length());
            }
            Integer p = TagPosition(s);
            if (p == null || n[p] == 0) {
                ans.append(c);
            } else {
                s = md[p];
                String t = html[p];
                int tagLength = s.length();
                String tmp;
                if ((n[p] - pos[p]) % 2 == 0) {
                    tmp = "<" + t + ">";
                } else {
                    tmp = "</" + t + ">";
                }
                ans.append(tmp);
                i += tagLength - 1;
                pos[p]++;
            }
        }
    }
}
