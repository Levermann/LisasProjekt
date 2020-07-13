package HypertextTransferProtocol;


public class MainMethod {

    public static void main(String[] args) throws Exception {

        /*
        This Method sends a simply http GET Request to given ServerAdress
         */
        HttpMethodCaller.executeHttpGet("http://httpbin.org/");

        /*
        This is the call for http Post by given targetUrl and the urlParams mapped together to byte array
        It will print out the json formatted Response
         */
        final byte[] urlParams = HttpMethodCaller.setUrlParameters("organisation", "HawLa", "name", "LS");
        HttpMethodCaller.executeHttpPost("http://httpbin.org/post","urlParams //TODO Convert to String");

        /*
        This is the WORKING_VERSION for a call for http Post by given targetUrl and the urlParams
        It will print out the html formatted Response
         */
        HttpMethodCaller.executeHttpsPost("https://selfsolve.apple.com/wcResults.do","sn=C02G8416DRJM&cn=&locale=&caller=&num=12345");
    }
}
