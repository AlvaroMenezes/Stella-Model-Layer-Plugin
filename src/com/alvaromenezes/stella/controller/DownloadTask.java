package com.alvaromenezes.stella.controller;

import com.alvaromenezes.stella.communication.HttpClient;
import com.alvaromenezes.stella.util.Util;
import com.alvaromenezes.stella.view.ProgressListener;

import javax.swing.*;


/**
 * @author Alvaro Menezes 28/05/2017
 */
public class DownloadTask extends SwingWorker<String, Object> {

    private String url;
    private ProgressListener listener;

    public DownloadTask(String url, ProgressListener listener) {
        this.url = url;
        this.listener = listener;

    }

    @Override
    protected String doInBackground() throws Exception {

        Util.sleep(2000);
        HttpClient client = new HttpClient(url, listener);
        return client.run();
    }

    @Override
    protected void done() {

        try {
            new ProcessResponseTask(get(), listener).execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE);
        }


    }

}
