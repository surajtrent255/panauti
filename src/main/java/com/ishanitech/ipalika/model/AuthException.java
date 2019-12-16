/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Aug 23, 2019
 */
package com.ishanitech.ipalika.model;
import org.springframework.security.core.AuthenticationException;

public class AuthException extends AuthenticationException {
	private static final long serialVersionUID = 1L;

	/**
	 * @param msg
	 */
	public AuthException(String msg) {
		super(msg);
	}

}
