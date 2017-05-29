package com.alvaromenezes.stella.controller;


import com.alvaromenezes.stella.creator.ModelCreator;
import com.alvaromenezes.stella.util.Util;
import com.alvaromenezes.stella.view.ProgressListener;

import javax.swing.*;


/**
 * @author Alvaro Menezes 28/05/2017
 */
public class ProcessResponseTask extends SwingWorker<Void, Void> {

    private String json;
    private ProgressListener listener;

    public ProcessResponseTask(String json, ProgressListener listener) {
        this.json = json;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground() throws Exception {
        Util.sleep(2000);

        ModelCreator creator = new ModelCreator(json,listener);
        creator.create();

      //  listener.setAction("");

        return null;
    }

    @Override
    protected void done() {

        JOptionPane.showMessageDialog(null, "Done!", "Finish", JOptionPane.PLAIN_MESSAGE);
    }

}
