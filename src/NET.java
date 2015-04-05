import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ram on 4/4/2015.
 */
public class NET {
    public static String SERVER_URL = "http://nlp.stanford.edu:8080/parser/index.jsp";
    Map<String, String> POST_HEADERS;

    public NET() {
        POST_HEADERS = new LinkedHashMap<String, String>();
        init_headers();
    }

    private void init_headers() {
        POST_HEADERS.put("defaultQuery.0", "هذا الرجل هو سعيد.");
        POST_HEADERS.put("defaultQuery.1", "猴子喜欢吃香蕉。");
        POST_HEADERS.put("defaultQuery.2", "My dog also likes eating sausage.");
        POST_HEADERS.put("defaultQuery.3", "El reino canta muy bien.");
        POST_HEADERS.put("chineseParseButton", "剖析 (Parse)");
        POST_HEADERS.put("parserSelect", "English");
        POST_HEADERS.put("parse", "Parse");
    }

    public StringBuffer get(String query) throws Exception {
        URL url = new URL(SERVER_URL);
        POST_HEADERS.put("query", query);

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, String> param : POST_HEADERS.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection socket = (HttpURLConnection) url.openConnection();
        socket.setRequestMethod("POST");
        socket.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        socket.setRequestProperty("User-Agent", " User- Agent:Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
        socket.setRequestProperty("DNT", "1");
        socket.setRequestProperty("Referer", "http://nlp.stanford.edu:8080/parser/");
        socket.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        socket.setDoOutput(true);
        socket.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        StringBuffer buff = new StringBuffer();

        for (int c; (c = in.read()) >= 0; buff.append((char) c)) ;
        return buff;


    }
}