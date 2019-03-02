/**
 * 
 */
package com.project.ams.funerary.service.exception;

/**
 * @author vitor
 *
 */
public class ExistingServiceNameException extends RuntimeException {

	private static final long serialVersionUID = 1150495740588795292L;

	/**
	 * @param message
	 */
	public ExistingServiceNameException(String message) {
		super(message);
	}
}
