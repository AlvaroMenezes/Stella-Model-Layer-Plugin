package com.alvaromenezes.stella.controller;

/**
 * 
 * @author Alvaro Menezes 27/05/2017
 *
 */
public interface ProgressListener {
	void update(long bytesRead, long contentLength);
}