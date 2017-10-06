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
        ModelCreator creator = new ModelCreator(json, listener);
        creator.create();

        return null;
    }

    @Override
    protected void done() {

        listener.dispose();
        StellaFormController.getProject().getBaseDir().refresh(false,true);
        JOptionPane.showMessageDialog(null, "Done!", "Finish", JOptionPane.PLAIN_MESSAGE);
    }

}
