package HypertextTransferProtocol;

import HypertextTransferProtocol.HttpMethodCaller;

public class MainMethod {

    final static String PATH_GET_METHOD = "http://httpbin.org/";
    final static String PATH_POST_METHOD = "http://httpbin.org/post/";
    final static String GET = "GET";
    final static String POST = "POST";



    public static void main(String[] args) throws Exception {
        System.out.println("Ausgabe aus der main()-Methode");

        //SendHttpRequest.executePost(PATH_POST_METHOD,"organisation=HawLa name=li st");

        final byte[] urlParams = HttpMethodCaller.setUrlParameters("organisation", "HawLa", "name", "LS");

        //SendHttpRequest.sendPost();
        HttpMethodCaller.executePost("","");

        // SendHttpRequest.getServerResponses(PATH_POST_METHOD);


     //   final SendHttpRequest sendHttpRequest = SendHttpRequest.sendHttpRequest(PATH_POST_METHOD, POST, "organisation=HawLa name=li st");
        final SendHttpRequest sendHttpRequest = SendHttpRequest.sendHttpRequest(PATH_GET_METHOD, GET,  "");
        System.out.println("This is the response Code from SERVER POST" + sendHttpRequest.getServerResponseStatus());
        System.out.println("This is the response from SERVER POST" + sendHttpRequest.getResponseBody());


    }


}
