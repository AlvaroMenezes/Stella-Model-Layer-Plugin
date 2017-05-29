package com.alvaromenezes.stella.creator;

import com.alvaromenezes.controller.ProcessJSON;
import com.alvaromenezes.pojo.Attribute;
import com.alvaromenezes.pojo.Entity;
import com.alvaromenezes.pojo.ModelLayer;
import com.alvaromenezes.stella.util.Util;
import com.alvaromenezes.stella.view.ProgressListener;

/**
 * Created by alvaromenezes on 5/26/17.
 */
public class ModelCreator {


    private ModelLayer modelLayer;
    private ProgressListener listener;


    public ModelCreator(String json, ProgressListener listener) {
        this.listener=listener;
        this.listener.setDlgTitle("Parsing JSON");
        this.modelLayer = new ProcessJSON().getModelLayer(json);
    }


    public void create() {

        listener.setDlgTitle("Generating model Layer");
        Util.sleep(2000);


        new PojoCreator(modelLayer,"ss/sss/sss/","com.alva.dfdf").process();


    }


}
