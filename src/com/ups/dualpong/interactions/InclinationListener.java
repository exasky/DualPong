/**
 * 
 */
package com.ups.dualpong.interactions;

/**
 * @author SERIN Kevin
 *
 */
public interface InclinationListener {
	/**
	 * Called when the device have a left inclination
	 * @param value
	 */
	void inclineLeft(int value);
	
	/**
	 * Called when the device have a right inclination
	 * @param value
	 */
	void inclineRight(int value);
}
