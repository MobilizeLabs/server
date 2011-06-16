/*******************************************************************************
 * Copyright 2011 The Regents of the University of California
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.ohmage.jee.servlet.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.ohmage.domain.ErrorResponse;
import org.ohmage.request.AwRequest;


/**
 * @author selsky
 */
public class TokenAuthResponseWriter extends AbstractResponseWriter {
	private static Logger _logger = Logger.getLogger(TokenAuthResponseWriter.class);
	
	public TokenAuthResponseWriter(ErrorResponse errorResponse) {
		super(errorResponse);
	}
	
	/**
	 * Generates the JSON response to the client. For successful responses, an array of campaign names is returned.
	 */
	@Override
	public void write(HttpServletRequest request, HttpServletResponse response, AwRequest awRequest) {
		Writer writer = null;
		
		try {
			// Prepare for sending the response to the client
			writer = new BufferedWriter(new OutputStreamWriter(getOutputStream(request, response)));
			String responseText = null;
			
			// Sets the HTTP headers to disable caching
			expireResponse(response);
			
			response.setContentType("application/json");
			
			// Build the appropriate response 
			if(! awRequest.isFailedRequest()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("result", "success");
				jsonObject.put("token", awRequest.getUserToken()); // this one line is the only difference with StatelessAuthResponseWriter
				responseText = jsonObject.toString();
				
			} else {
				
				if(null != awRequest.getFailedRequestErrorMessage()) {
					responseText = awRequest.getFailedRequestErrorMessage();
				} else {
					responseText = generalJsonErrorMessage();
				}
			}
			
			_logger.info("Generating token-based authentication response output.");
			writer.write(responseText);
		}
		
		catch(Exception e) { // catch Exception in order to avoid redundant catch block functionality (Java 7 will have 
			                 // comma-separated catch clauses) 
			
			_logger.error("An unrecoverable exception occurred while attempting to serialize JSON", e);
			
			try {
				
				writer.write(this.generalJsonErrorMessage());
				
			} catch (Exception ee) {
				
				_logger.error("Caught Exception when attempting to write to HTTP output stream", ee);
			}
			
		} finally {
			
			if(null != writer) {
				
				try {
					
					writer.flush();
					writer.close();
					writer = null;
					
				} catch (IOException ioe) {
					
					_logger.error("Caught IOException when attempting to free resources", ioe);
				}
			}
		}
	}
}