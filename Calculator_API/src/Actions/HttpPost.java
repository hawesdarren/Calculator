package Actions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.testng.Assert;

import Tools.Settings;

public class HttpPost {

	public static HttpsURLConnection sendPost(String jsonBody) throws Exception
	{
		URL url = new URL(Settings.calculatorEndpoint);
		
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] 
        {new X509TrustManager() 
	        {
	                public java.security.cert.X509Certificate[] getAcceptedIssuers() 
	                {
	                    return null;
	                }
	                
					@Override
					public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
							throws CertificateException {
						// TODO Auto-generated method stub
						
					}
					@Override
					public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
							throws CertificateException {
						// TODO Auto-generated method stub
						
					}
	        }
        };
 
        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
 
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
 
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		
		//Set Proxy
		//Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 9000));
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(Settings.proxy, Settings.port));
				
		//Open connection
		HttpsURLConnection con = (HttpsURLConnection)url.openConnection(proxy);
		
		//Set POST request
		con.setRequestMethod("POST");
		
		//Set Request headers
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("x-functions-key", Settings.authKey);
		
		con.setDoOutput(true);
		
		//Write to POST
		try(OutputStream os = con.getOutputStream())
		{
			byte[] input = jsonBody.getBytes("utf-8");
			os.write(input, 0, input.length);
		}
		
	
		return con;
	}
	
	public static Integer GetResponseCode(HttpsURLConnection con)  {
		//Set default value to -1 = Error
		int resCode = -1;
		
		try {
			resCode =  con.getResponseCode();	
			
		}
		catch(Exception e) {
			Assert.assertTrue(false, "Could not connect to the server");
		}
		//Convert to Integer
		Integer iResCode = new Integer(resCode);
		return iResCode;
		
	}
	
	public static void VerifyResponseCodeLessThan400(HttpsURLConnection con)  {
		//Set default value to -1 = Error
		int resCode = -1;
		
		try {
			resCode =  con.getResponseCode();	
			
		}
		catch(Exception e) {
			Assert.assertTrue(false, "Could not connect to the server");
		}
		Assert.assertTrue(resCode < 400, "Response code was not < 400");
		//Assert.assertEquals(resCode, 200, "REsponse code was not valid");
		
	}
	
	public static String GetResponse(HttpsURLConnection con) {
		//Read the response
		StringBuilder response = new StringBuilder();
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))){
					String responseLine = null;
					while((responseLine = br.readLine()) != null) {
								response.append(responseLine.trim());
					}
		}
		catch(Exception e) {

		}	
				
		return response.toString();
	}
}
