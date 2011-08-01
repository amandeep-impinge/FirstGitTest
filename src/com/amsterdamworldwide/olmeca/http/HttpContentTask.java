/**
 * 
 */
package com.amsterdamworldwide.olmeca.http;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;

import com.amsterdamworldwide.olmeca.model.IEHttpResponse;

import android.os.AsyncTask;
import android.util.Log;

/**
 * @author amandeep
 *
 */
public class HttpContentTask extends AsyncTask<Void, Void, Void> {
	
	private boolean error = true;
	private HttpCallListener listener;
	private String httpUrl;
	private HttpMethodType httpMethodType;
	private IEHttpResponse response;
	private int pageId;
	private HttpEntity httpEntity;
	private String contentType = HttpContentType.APPLICATION_JSON;
	private String rememberToken = null;
	private HttpUriRequest uriRequest;
	
	
	// Default Constructor
	public HttpContentTask(HttpCallListener listener, int pageId){
		this.listener = listener;
		this.pageId = pageId;
		response = new IEHttpResponse();
	}
	
	public HttpContentTask(HttpUriRequest uriRequest){
		this.uriRequest = uriRequest;		
	}
	
	public HttpContentTask(String httpUrl, HttpCallListener listener, int pageId) {
		this.httpUrl = httpUrl;
		this.listener = listener;
		this.pageId = pageId;
		httpMethodType = HttpMethodType.HTTP_POST;
		response = new IEHttpResponse();
		Log.i("URL ", httpUrl);
		
		
	}
	
	public void setListener(HttpCallListener listener) {
		this.listener = listener;
	}
	
	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}
	
	
	
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
	public HttpUriRequest getUriRequest() {
		return uriRequest;
	}
	
	public void setUriRequest(HttpUriRequest uriRequest) {
		this.uriRequest = uriRequest;
	}
	
	/**
	 * @return the httpEntity
	 */
	public HttpEntity getHttpEntity() {
		return httpEntity;
	}
	
	/**
	 * @param httpEntity the httpEntity to set
	 */
	public void setHttpEntity(HttpEntity httpEntity) {
		this.httpEntity = httpEntity;
	}
	
	/**
	 * @return the httpMethodType
	 */
	public HttpMethodType getHttpMethodType() {
		return httpMethodType;
	}
	
	/**
	 * @param httpMethodType the httpMethodType to set
	 */
	public void setHttpMethodType(HttpMethodType httpMethodType) {
		this.httpMethodType = httpMethodType;
	}
	
	public String getRememberToken() {
		return rememberToken;
	}
	
	public void setRememberToken(String rememberToken) {
		this.rememberToken = rememberToken;
	}
	
	
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(Void result) {
		
		if(listener != null){
			if(error){
				listener.onError(response.getResponse(),pageId);
			}else{
				listener.onResponse(response, pageId);
			}
		}
		super.onPostExecute(result);
	}
	
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() {
		
		
		super.onPreExecute();
	}
	
	
	
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onCancelled()
	 */
	@Override
	protected void onCancelled() {
		
		super.onCancelled();
	}
	
	
	
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			// now, the handler will automatically bind to the
			// Looper that is attached to the current thread
			// You don't need to specify the Looper explicitly
			//handler = new Handler();

			try {
				// Call HttpConnection Using HttpClient
				//response = YicsaHttpRequest.postXmlData(servletName, postData);
			
				if(uriRequest != null){
					  
					
					response = HttpRestClient.executeHttpRequest(uriRequest);
					
				}else if(getHttpMethodType().equals(HttpMethodType.HTTP_POST)){
					
					response = HttpRestClient.connectPost(httpUrl,httpEntity,getContentType());	
					
				}else if (getHttpMethodType().equals(HttpMethodType.HTTP_GET)){
					
					response = HttpRestClient.connectGet(httpUrl,getContentType());
					
				}else if(getHttpMethodType().equals(HttpMethodType.FILE_POST)){
					response = HttpRestClient.connectFilePost(httpUrl,httpEntity);
						
				}else{
					response = HttpRestClient.connectPut(httpUrl,httpEntity,getContentType(),getRememberToken());
				}
				
				error = false;
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
				 error = true;
				 response.setResponse("Server might be down.\nPlease retry after some time.");
			}catch (FileNotFoundException e) {
				e.printStackTrace();
				error = true;
				response.setResponse("Server might be down.\nPlease retry after some time.");

			} catch (ConnectTimeoutException e) {
				e.printStackTrace();
				error = true;
				response.setResponse("Connection Timeout!.\nPlease check you internet connectivity or retry after some time.");

			}  catch(IOException e){
					e.printStackTrace();
					error = true;
					response.setResponse(e.getMessage());
				
			}catch (Exception e) {
				e.printStackTrace();
				error = true;
				response.setResponse("Please Retry.");
			}

			// After the following line the thread will start
			// running the message loop and will not normally
			// exit the loop unless a problem happens or you
			// quit() the looper (see below)
			//Looper.loop();

			

		} catch (Throwable e) {

			e.printStackTrace();
		}

		return null;
	}

}
