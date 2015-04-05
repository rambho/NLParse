import java.util.LinkedList;

/**
 * Created by ram on 4/4/2015.
 */

public class POS_PARSER {
    private int INDEX = 0;
    private String statement = "";
    private String err = "";
    private LinkedList<String> POS_LIST = new LinkedList<String>();
    private LinkedList<String> WORD_LIST = new LinkedList<String>();
    private LinkedList<String> PARSE_LIST = new LinkedList<String>();


    private boolean validRange() {
        return INDEX < statement.length();
    }

    private Token.Symbol GetNextToken() {
        Token.Symbol ret;
        String sx;
        while (validRange() && (sx = Character.toString(statement.charAt(INDEX))).matches("[\\r\\n\\s]"))
            INDEX++;
        if (!validRange())
            ret = Token.Symbol.EOS;
        else if (statement.charAt(INDEX) == '(')
            ret = Token.Symbol.OPEN_PAREN;
        else if (statement.charAt(INDEX) == ')')
            ret = Token.Symbol.CLOSE_PAREN;
        else {
            StringBuilder s = new StringBuilder();
            while (validRange() && (sx = Character.toString(statement.charAt(INDEX))).matches("[a-zA-Z'\\?\\-\\.!;,]"))
                s.append(statement.charAt(INDEX++));
            Token.Symbol.WORD.set(s.toString());
            ret = Token.Symbol.WORD;
        }
        if (ret != Token.Symbol.WORD)
            INDEX++;
        return ret;

    }

    private void reset() {
        POS_LIST.clear();
        WORD_LIST.clear();
        PARSE_LIST.clear();
        err = "";
        INDEX = 0;
    }

    public void parse(String s) throws Exception {
        statement = s;
        reset();
        parseStatement();
        if (err.length() > 0)
            throw new Exception(err);

    }

    public void printWordTree() {
        for (String s : WORD_LIST)
            System.out.print(s + " -> ");
        System.out.println("EOS");
    }

    public String getParseTree() {
        return PARSE_LIST.toString().replaceAll(",", "");
    }

    private boolean validateGrammar(Token.Symbol given, Token.Symbol expect) {
        if (given != expect) {
            err = "Invalid Grammar - PARSE ERROR at charPos: " + INDEX;
            return false;
        }
        return true;

    }

    private void parseStatement() {
        Token.Symbol token = GetNextToken();
        //System.out.println(token.value);
        if (token == Token.Symbol.OPEN_PAREN) {
            PARSE_LIST.add("[");
            parsePOS();

        } else if (token == Token.Symbol.CLOSE_PAREN) {
            PARSE_LIST.add("]");
            parseStatement();
        }
    }


    private void parsePOS() {
        Token.Symbol token = GetNextToken();
        //System.out.println(token.value);
        if (validateGrammar(token, Token.Symbol.WORD)) {
            POS_LIST.add(token.value);
            PARSE_LIST.add(token.value + ": ");
            parseOBJ();

        }
    }

    private void parseOBJ() {
        Token.Symbol token = GetNextToken();
        //System.out.println(token.value);
        if (token == Token.Symbol.OPEN_PAREN) {
            parsePOS();
            PARSE_LIST.add("[");
        } else if (validateGrammar(token, Token.Symbol.WORD)) {
            WORD_LIST.add(token.value);
            PARSE_LIST.add("<" + token.value + ">");
            parseStatement();
        }

    }
}
