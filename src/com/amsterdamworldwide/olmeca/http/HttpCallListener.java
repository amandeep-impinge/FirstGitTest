/**
 * 
 */
package com.amsterdamworldwide.olmeca.http;

import com.amsterdamworldwide.olmeca.model.IEHttpResponse;



/**
 * @author amandeep
 *
 */
public interface HttpCallListener {
	
	/**
	 * 
	 * onResponse function is called when HTTP CALL done successfully. 
	 * 
	 * @param response gives Response containing HTTP request Status and String Response
	 * @param pageId gives same PAGEID as with application call get started
	 */
	public void onResponse(IEHttpResponse response, int pageId);
	
	/**
	 * onError function is called when HTTP CALL get some error due to Server not found, Timeout Exception or some 
	 * IO exception occurs during call.
	 * 
	 * @param error provide Error Messsage
	 * @param pageId give same PAGEID as with application call got started
	 */
	public void onError(String error, int pageId);
	
	
}

