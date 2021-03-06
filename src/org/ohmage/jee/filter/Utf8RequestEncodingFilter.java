/*******************************************************************************
 * Copyright 2012 The Regents of the University of California
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
package org.ohmage.jee.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Force UTF-8 character encoding on HTTP requests.
 * 
 * @author selsky
 */
public class Utf8RequestEncodingFilter implements Filter {
	/**
	 * Default no-arg constructor.
	 */
	public Utf8RequestEncodingFilter() {

	}

	/**
	 * Does nothing.
	 */
	public void destroy() {

	}

	/**
	 * Does nothing.
	 */
	public void init(FilterConfig config) throws ServletException {

	}

	/**
	 * Sets the character encoding on the request to be UTF-8.
	 */
	public void doFilter(
		final ServletRequest request,
		final ServletResponse response,
		final FilterChain chain) 
		throws ServletException, IOException {	

		// If no character encoding is present, default to UTF-8.
		if(request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}
		
		// Continue processing.
		chain.doFilter(request, response);
	}
}