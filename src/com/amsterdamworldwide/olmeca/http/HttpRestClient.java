package com.amsterdamworldwide.olmeca.http;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

import android.util.Log;

import com.amsterdamworldwide.olmeca.model.IEHttpResponse;
import com.amsterdamworldwide.olmeca.util.Constants;
import com.amsterdamworldwide.olmeca.util.OlmecaPreferences;



public class HttpRestClient {
	
	 public static final String tag = "HttpRestClient"; 
	 
	 private static DefaultHttpClient defaultHttpClient = null;

		public static DefaultHttpClient getDefaultHttpClient() {
			//if (defaultHttpClient == null) {
				
					boolean trustAll = true;
					ClientConnectionManager clientConnectionManager;
				    HttpParams httpParameters ;
				    // ......
				    SchemeRegistry schemeRegistry = new SchemeRegistry();
				    schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
					   
				    schemeRegistry.register(new Scheme("https", (trustAll ? new FakeSocketFactory() : new EasySSLSocketFactory()), 443));
				    httpParameters = new BasicHttpParams();
				    
				    int timeoutConnection = 14000;
				 	HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);

				 	// Set the default socket timeout (SO_TIMEOUT) 
				 	// in milliseconds which is the timeout for waiting for data.
				 	//int timeoutSocket = 30*1000;
				 	//HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
				    //httpParameters.setParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS, 1);
				    //httpParameters.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE, new ConnPerRouteBean(1));
				    //httpParameters.setParameter(HttpProtocolParams.USE_EXPECT_CONTINUE, false);
				    //HttpProtocolParams.setUserAgent(httpParameters, "android-client-v1.0");
				    //HttpProtocolParams.setVersion(httpParameters, HttpVersion.HTTP_1_1);
				    //HttpProtocolParams.setContentCharset(httpParameters, "utf8");
				    clientConnectionManager = new ThreadSafeClientConnManager(httpParameters, schemeRegistry);
				    // and later do this
				    defaultHttpClient = new DefaultHttpClient(clientConnectionManager, httpParameters);
				    defaultHttpClient.setHttpRequestRetryHandler(new HttpRequestRetryHandler() {
						
						@Override
						public boolean retryRequest(IOException exception, int executionCount,
								HttpContext context) {
							
							Log.e("HttpRetryCount", "Count" + executionCount);
							
							if(executionCount > 3)
							  return false;
							else
								return true;
						}
					});
				    /*defaultHttpClient.setRedirectHandler(new RedirectHandler() {

						
						public URI getLocationURI(HttpResponse response,
								HttpContext context) throws ProtocolException {
							return null;
						}

						public boolean isRedirectRequested(HttpResponse response,
								HttpContext context) {
							// TODO Auto-generated method stub
							return true;
						}
					});*/
				    
				    
				
				
				/*HttpParams httpParameters = new BasicHttpParams();

			    // Set the timeout in milliseconds until a connection is established.
			 	int timeoutConnection = 3000;
			 	HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);

			 	// Set the default socket timeout (SO_TIMEOUT) 
			 	// in milliseconds which is the timeout for waiting for data.
			 	int timeoutSocket = 5000;
			 	HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

			 	httpParameters.setParameter(HttpProtocolParams.USE_EXPECT_CONTINUE, false);

				
				defaultHttpClient = new DefaultHttpClient(httpParameters);
				*/
				Log.e(tag, " ##### NEW INSTANCE FOR DEFAULT HTTP CLIENT #####");
			//} else {
				//Log.e(tag, " ##### REUSE FOR DEFAULT HTTP CLIENT #####");
			//}

			return defaultHttpClient;
		}

	 private static String convertStreamToString(InputStream is) {
	        /*
	         * To convert the InputStream to String we use the BufferedReader.readLine()
	         * method. We iterate until the BufferedReader return null which means
	         * there's no more data to read. Each line will appended to a StringBuilder
	         * and returned as String.
	         */
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	        StringBuilder sb = new StringBuilder();
	 
	        
	        String line = null;
	        try {
	            while ((line = reader.readLine()) != null) {
	                sb.append(line);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                is.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return sb.toString();
	    }
	 
	 
	 private static HttpRequestInterceptor preemptiveAuth = new HttpRequestInterceptor() {
		    public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
		        AuthState authState = (AuthState) context.getAttribute(ClientContext.TARGET_AUTH_STATE);
		        CredentialsProvider credsProvider = (CredentialsProvider) context.getAttribute( ClientContext.CREDS_PROVIDER);
		        HttpHost targetHost = (HttpHost) context.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
		        
		        if (authState.getAuthScheme() == null) {
		            AuthScope authScope = new AuthScope(targetHost.getHostName(), targetHost.getPort());
		            org.apache.http.auth.Credentials creds = credsProvider.getCredentials(authScope);
		            if (creds != null) {
		                authState.setAuthScheme(new BasicScheme());
		                authState.setCredentials(creds);
		            }
		        }
		    }    
		};
	 
		
		private static void addHttpAuthentication(DefaultHttpClient httpClient, String token){
			httpClient.addRequestInterceptor(preemptiveAuth, 0);
			httpClient.getCredentialsProvider().setCredentials(
                    new AuthScope(Constants.HOST, 80),
                    
                    new UsernamePasswordCredentials(Constants.REMEBER_TOKEN, token));
			//request.setHeader("Authorization", "basic "+accessToken);
			//request.setHeader("Host", "drg.mine.nu");
			Log.i(tag,"AccessToken Base Encoded = "+token);
		}
		
		
	 public static IEHttpResponse connectPut(String url,HttpEntity httpEntity, String contentType, String remeberToken) throws IOException
	    {
		 IEHttpResponse ieHttpResponse = null;
		 
		 try{
		 	Log.v(tag, "<<<<<()>>>>> HttpPut");
		 	
		    DefaultHttpClient httpclient = getDefaultHttpClient();
	           // Prepare a request object
	        HttpPut httpput = new HttpPut(url); 
	        httpput.addHeader(new BasicHeader("Content-type", contentType));
	        //httpget.setEntity(new UrlE)
	        if(httpEntity != null){
	        	httpput.setEntity(httpEntity);
	        	Log.v(tag, "<<<<<()>>>>> Content Length : " + httpEntity.getContentLength());
	        }
	        
	        if(remeberToken != null){
	        	//addHttpAuthentication(httpclient,remeberToken);
	        	httpput.addHeader(Constants.REMEBER_TOKEN,"Basic"+remeberToken);
	        }
	 
	        // Execute the request
	        HttpResponse response;
	       
	            response = httpclient.execute(httpput);
	            // Examine the response status
	            //Log.i(tag,"Http Response: " +  response.getStatusLine().toString());
	            ieHttpResponse = getReponse(response);
	 
		 }catch (Exception e) {
			 e.printStackTrace();
			throw new IOException("Error! Please try later.");
		}
	        
	        //Log.v(tag, "<<<<<(Response)>>>>> = " + data);
	        return ieHttpResponse;
	    }
	 
	 public static IEHttpResponse connectGet(String url, String contentType) throws IOException
	    {
			IEHttpResponse ieResponse = new IEHttpResponse();
		 try{
		 	Log.v(tag, "<<<<<()>>>>> connectGet");
			
		 	
	        HttpClient httpclient = getDefaultHttpClient();
	 
	        // Prepare a request object
	        HttpGet httpget = new HttpGet(url); 
	        httpget.setHeader(new BasicHeader("Content-type", contentType));
	        
	        
	 
	        // Execute the request
	        HttpResponse response;
	       
	            response = httpclient.execute(httpget);
	            ieResponse = getReponse(response);
	             
	            
	 
		 }catch (Exception e) {
			 e.printStackTrace();
			throw new IOException("Error! Please try later.");
		}
	        
	        //Log.v(tag, "<<<<<(Response)>>>>> = " + data);
	        return ieResponse;
	    }

	 
	 
	 public static IEHttpResponse connectFilePost(String url,  HttpEntity httpEntity) throws IOException 
	    {
		 	
		 IEHttpResponse ieHttpResponse = null;
		 Log.v(tag, "<<<<<()>>>>> connectPost");
			
		    HttpClient httpclient = getDefaultHttpClient();
	 
	        // Prepare a request object
	        HttpPost httppost = new HttpPost(url);
	        
	        BasicHeader[] headers = new BasicHeader[3];
	        headers[0] = new BasicHeader("Content-type", "contents = \"multipart/form-data; charset=utf-8; boundary=0xKhTmLbOuNdArY\"");
	        headers[1] = new BasicHeader("Accept-Encoding", "gzip");
	        
	        
	        
	       // httppost.setHeader(new BasicHeader("Content-type", "contents = \"multipart/form-data; charset=utf-8; boundary=0xKhTmLbOuNdArY\""));
	       // httppost.setHeader(new BasicHeader("Content-type", contentType));
	        
	        
	        if(httpEntity != null){
	           httppost.setEntity(httpEntity);
	           headers[2] = new BasicHeader("Content-Length", String.valueOf(httpEntity.getContentLength()) );
		        
	           Log.v(tag, "<<<<<()>>>>> Content Length : " + httpEntity.getContentLength());
	        }
	 
	        // Execute the request
	        HttpResponse response;
	       try{
	            response = httpclient.execute(httppost);
	            // Examine the response status
	            Log.e(tag,"Http Response: " +  response.getStatusLine().toString());
	            
	 
	            
	            ieHttpResponse = getReponse(response);
	            
	 
	       }catch (Exception e) {
	    	   e.printStackTrace();
	    	   throw new IOException(e.getMessage()); //"Error! Please try later."
	       }
	        
	        return ieHttpResponse;
	    }
	 
	 public static IEHttpResponse connectPost(String url,  HttpEntity httpEntity, String contentType) throws IOException 
	    {
		 	
		 	IEHttpResponse ieResponse =  new IEHttpResponse();
		 Log.v(tag, "<<<<<()>>>>> connectPost"+url);
			
		    HttpClient httpclient = getDefaultHttpClient();
	 
	        // Prepare a request object
	        HttpPost httppost = new HttpPost(url);
	        
	        httppost.setHeader(new BasicHeader("Content-type", contentType));
	        
	        
	        if(httpEntity != null){
	           httppost.setEntity(httpEntity);
	           Log.v(tag, "<<<<<()>>>>> Content Length : " + httpEntity.getContentLength());
	        }
	 
	        // Execute the request
	        
	        HttpResponse response;
	       try{
	            response = httpclient.execute(httppost);
	            ieResponse = getReponse(response);
	            
	       }catch (Exception e) {
	    	   e.printStackTrace();
	    	   throw new IOException("Error! Please try later."); //
	       }
	        
	       return ieResponse;
	    }
	 
	 
	 private static IEHttpResponse getReponse(HttpResponse response) throws IOException{
		 IEHttpResponse ieResponse = new IEHttpResponse();
		 HttpEntity entity = response.getEntity();
         // If the response does not enclose an url = Constants.HTTP_SERVER_ADDRESS + url;entity, there is no need
         // to worry about connection release
         ieResponse.setHttpStatusCode(response.getStatusLine().getStatusCode());
         Log.i(tag, response.getStatusLine().toString());
         switch (response.getStatusLine().getStatusCode()) {
        
         case HttpURLConnection.HTTP_CLIENT_TIMEOUT:
				throw new IOException("Timeout: Newtowk seems busy. Please try later.");
			case HttpURLConnection.HTTP_NOT_FOUND:
				throw new IOException("Server is busy. Please try latter");
			
			default:
         
				if (entity != null) {
				
	                // A Simple JSON Response Read
	                InputStream instream = entity.getContent();
	                ieResponse.setResponse(convertStreamToString(instream));
	                
	                try{
	                   instream.close();
	                }catch (Exception e) {
						e.printStackTrace();
					}
	            }
				break;
			
				
			}
         Log.i(tag, "<<>> " + ieResponse.getResponse());
         return ieResponse;
		 
	 }
	 
	 public static void addHeaders(HttpUriRequest httpUriRequest,OlmecaPreferences settings){
		 
		 httpUriRequest.setHeader(new BasicHeader("Content-type", HttpContentType.APPLICATION_JSON));
		 if(settings.getCurrentSessionToken() != null){
			 httpUriRequest.setHeader(new BasicHeader("Authorization", "RememberToken "+settings.getCurrentSessionToken()));
			 
			 httpUriRequest.setHeader(new BasicHeader("Accept", "*/*"));
			 Log.i("Token : ", ":: " + settings.getCurrentSessionToken() );
		 }else{
			 Log.i("Token : ", ":: is NULL" );
		 }
		 
	 }
	 
	 public static IEHttpResponse executeHttpRequest( HttpUriRequest uriRequest) throws IOException 
	    {
		 	
		 	IEHttpResponse ieResponse =  new IEHttpResponse();
		    Log.v(tag, "<<<<<()>>>>> connectPost"+uriRequest.getURI());
			
		    HttpClient httpclient = getDefaultHttpClient();
	        // Execute the request
	        HttpResponse response;
	       try{
	            response = httpclient.execute(uriRequest);
	            ieResponse = getReponse(response);
	            
	       }catch (Exception e) {
	    	   e.printStackTrace();
	    	   throw new IOException("Error! Please try later."); //
	       }
	        
	       return ieResponse;
	    }
	 

}
