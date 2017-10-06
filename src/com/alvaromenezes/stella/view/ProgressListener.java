package com.alvaromenezes.stella.view;

/**
 * @author Alvaro Menezes 27/05/2017
 */
public interface ProgressListener {

    void update(long bytesRead, long contentLength);

    void setDlgTitle(String title);

    void setAction(String action);

    void dispose();
}