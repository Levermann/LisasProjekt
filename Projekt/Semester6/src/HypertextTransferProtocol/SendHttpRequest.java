package HypertextTransferProtocol;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class SendHttpRequest {


    private static int serverResponseStatus;
    private static String responseBody;

    public SendHttpRequest(int serverResponseStatus, String responseBody) {
        this.serverResponseStatus = serverResponseStatus;
        this.responseBody = responseBody;
    }

    public static void sendPost() throws Exception {

        String url = "http://httpbin.org/post";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        //Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }


    public static String executePost(String targetURL, String urlParameters) {
        HttpURLConnection connection = null;

        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length",
                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                System.out.println("This is the Server Response " + line );
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }



    public static void getServerResponses(URLConnection con) throws IOException {
        InputStream in = con.getInputStream();
        Scanner s = new Scanner(in).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        String encoding = con.getContentEncoding();
        encoding = encoding == null ? "UTF-8" : encoding;
        String body = result;
        System.out.println("------------------------------------------------------------------------");
        System.out.println("This is the Server Response " +body + encoding);

    }

    public static void getServerResponsePost(URLConnection con) throws IOException {
        InputStream in = con.getInputStream();
        Scanner s = new Scanner(in).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        String encoding = con.getContentEncoding();
        encoding = encoding == null ? "UTF-8" : encoding;
        String body = result;
        System.out.println("------------------------------------------------------------------------");
        System.out.println("This is the Server Response " +body + encoding);

    }



    public static int getConnectionCode(String clientUrl) throws IOException {
        HttpURLConnection connection;
            //Create connection
            URL url = new URL(clientUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            return connection.getResponseCode();

    }



    public static int getConnectionCodeboth(String clientUrl, String requestMethod, String urlParameters) throws IOException {
        HttpURLConnection connection;
        //Create connection

        if (requestMethod.equals("GET")) {
            URL url = new URL(clientUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestMethod);
            connection.connect();

            return connection.getResponseCode();
        }else if(requestMethod.equals("POST")){
                URL url = new URL(clientUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(requestMethod);
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");

                connection.setRequestProperty("Content-Length",
                        Integer.toString(urlParameters.getBytes().length));
                connection.setRequestProperty("Content-Language", "en-US");

                connection.setUseCaches(false);
                connection.setDoOutput(true);

                //Send request
                DataOutputStream wr = new DataOutputStream (
                        connection.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.close();

            return connection.getResponseCode();



        }else throw new IllegalStateException("unknown requestMethod" + requestMethod);
    }



    public static SendHttpRequest sendHttpRequest(String clientUrl, String requestMethod, String urlParameters) throws IOException {
        HttpURLConnection connection;
        //Create connection
        URL url = new URL(clientUrl);
        final String response;
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(requestMethod);
        if (requestMethod.equals("GET")) {
            connection.connect();
            response= "haha";
            getServerResponses(connection);
            return new SendHttpRequest(connection.getResponseCode(), response);

        }else if(requestMethod.equals("POST")){
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length",
                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setRequestProperty("organisation", "HawLa");
            connection.setRequestProperty("name", "li st");
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response
            response = "getServerResponses(connection)";
            getServerResponsePost(connection);
            final int theCode= connection.getResponseCode();
            connection.disconnect();
            return new SendHttpRequest(theCode, response);

        }
        else throw new IllegalStateException("unknown Request-Method: " + requestMethod);
    }


    public int getServerResponseStatus() {
        return serverResponseStatus;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
