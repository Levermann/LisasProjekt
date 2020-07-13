package HypertextTransferProtocol;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

public class HttpMethodCaller {

    /*
    The following Methods is sending a http Get Request by given url
    */
    static void executeHttpGet(String targetUrl) throws IOException {
        URL obj = new URL(targetUrl);
        HttpURLConnection connection;
        //Create connection
        connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        //prints out the formatted html Response
        convertServerResponse(connection);
        System.out.println("HTTP Status Code : " + connection.getResponseCode());
    }

    /*
    This Method will Convert the Server Response into html format and print it out
     */
    private static void convertServerResponse(URLConnection con) throws IOException {
        InputStream in = con.getInputStream();
        Scanner s = new Scanner(in).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        String encoding = con.getContentEncoding();
        encoding = encoding == null ? "UTF-8" : encoding;
        String body = result;
        System.out.println("------------------------------------------------------------------------");
        System.out.println("This is the Server Response " +body + encoding);

    }

    public static void executeHttpsPost(String targetUrl, String urlParam) throws Exception {
        URL obj = new URL(targetUrl);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "de,en;q=0.5");

        //Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParam);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + targetUrl);
        System.out.println("Post parameters : " + urlParam);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) { response.append(inputLine); }
        in.close();

        //print result
        System.out.println(response.toString());
    }

    public static void executeHttpPost(String targetAdress, String urlParam) throws Exception {
        URL url = new URL(targetAdress);
        //Create connection
        HttpURLConnection connection;
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", Integer.toString(urlParam.getBytes().length));
        connection.setRequestProperty("Content-Language", "en-US");
        connection.setRequestProperty("organisation", "HawLa");
        connection.setRequestProperty("name", "li st");
        connection.setUseCaches(false);
        connection.setDoOutput(true);

        //Send request
        DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
        wr.writeBytes(urlParam);
        wr.close();

        //Get Response
        convertServerResponse(connection);
        System.out.println("HTTP Status Code : " + connection.getResponseCode());
        connection.disconnect();

    }

    public static byte[] setUrlParameters(String reqOrganization, String paramOrganization, String reqName, String paramName) throws UnsupportedEncodingException {
        Map<String,String> arguments = new HashMap<>();
        arguments.put(reqOrganization, paramOrganization);
        arguments.put(reqName, paramName);

        StringJoiner sj = new StringJoiner("&");
        for(Map.Entry<String,String> entry : arguments.entrySet())
            sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                    + URLEncoder.encode(entry.getValue(), "UTF-8"));
        return sj.toString().getBytes(StandardCharsets.UTF_8);
    }
}
