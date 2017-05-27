package com.alvaromenezes.stella.controller;

import com.alvaromenezes.controller.ProcessJSON;
import com.alvaromenezes.pojo.Attribute;
import com.alvaromenezes.pojo.Entity;
import com.alvaromenezes.pojo.ModelLayer;

/**
 * Created by alvaromenezes on 5/26/17.
 */
public class ModelCreator {


    private ModelLayer modelLayer;

    public ModelCreator(String json) {
        this.modelLayer = new ProcessJSON().getModelLayer(json);
    }


    public void create() {


        for (Entity e : modelLayer.entities) {

            System.out.println("Entity: " + e.name);

            for (Attribute a : e.attributes) {

                if (!a.isArray) {

                    System.out.println("       Atributo: " + a.dataType + "    " + a.name);
                } else {
                    System.out.println("       Atributo: array of " + a.dataType + "    " + a.name);
                }

            }

        }


    }


}
