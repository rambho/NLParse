/**
 * Created by ram on 4/4/2015.
 */
public class Token {

    public static enum Symbol {
        OPEN_PAREN("("),
        CLOSE_PAREN(")"),
        WORD(""),
        EOS("");

        String value;

        Symbol(String s) {
            set(s);
        }

        public void set(String s) {
            this.value = s;
        }

        Symbol() {
        }

    }
}
