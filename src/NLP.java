import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ram on 4/5/2015.
 */
public class NLP {
    static String patterns[] = {
            "",
            "(<pre id=.parse. class=.spacingFree.>(.*?)<.pre>)",
            "(<pre class=.spacingFree.>.(.*?)<.pre>)",
            "(d<.h3>.*?Free.>(.*?)<.pre>)",
            "" };

    public enum TYPE {
        TAGGED_SENTENCE("TAGGED_SENTENCE"),
        PART_OF_SPEECH_TAGGING("PART_OF_SPEECH_TAGGING"),
        TYPE_DEPENDENCY("TYPE_DEPENDENCY"),
        TYPE_DEPENDENCY_COLLAPSED("TYPE_DEPENDENCY_COLLAPSED"),
        FLATTENED_PARSE_TREE("FLATTENED_PARSE_TREE");

        private final String value;

        private TYPE(String s) {
            this.value = s;
        }
    }

    private POS_PARSER parser;
    private NET net;
    private HashMap<TYPE, String> data;

    public NLP() {
        parser = new POS_PARSER();
        net = new NET();
        data = new HashMap<TYPE, String>();
    }

    public void printAll() {
        TYPE[] v = TYPE.values();
        for (int i = 0; i < v.length; i++) {
            System.out.println(v[i] + ":");
            System.out.println(data.get(v[i]));
            System.out.println();
        }

    }

    public void process(String sentence) throws Exception {
        StringBuffer results = net.get(sentence);
        TYPE[] v = TYPE.values();
        assert (patterns.length == v.length);
        for (int i = 0; i < patterns.length; i++) {
            String rx = patterns[i];
            if (rx != "") {
                Pattern p = Pattern.compile(rx, Pattern.DOTALL);
                Matcher m = p.matcher(results);
                if (m.find() && m.groupCount() > 1)
                    data.put(v[i], m.group(2));

            } else
                data.put(v[i], "");
        }
        parser.parse(data.get(TYPE.PART_OF_SPEECH_TAGGING));
        data.put(TYPE.FLATTENED_PARSE_TREE, parser.getParseTree());

    }
}
