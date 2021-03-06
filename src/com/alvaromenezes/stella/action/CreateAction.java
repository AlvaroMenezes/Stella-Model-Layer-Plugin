package com.alvaromenezes.stella.action;

import com.alvaromenezes.stella.controller.StellaFormController;
import com.alvaromenezes.stella.view.StellaForm;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import javax.swing.*;

/**
 * Created by alvaromenezes on 5/16/17.
 */
public class CreateAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {

        StellaFormController controller = new StellaFormController(e.getProject());

        JFrame frame = new JFrame("Stella Model Layer");
        StellaForm form = new StellaForm(controller);
        frame.setContentPane(form.panelMain);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
