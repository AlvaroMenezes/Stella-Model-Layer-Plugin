package com.alvaromenezes.stella.controller;

import com.alvaromenezes.stella.model.Entity;
import com.alvaromenezes.stella.model.ModelLayer;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by alvaromenezes on 5/18/17.
 */
public class JsonParser {

    private String json;

    public  JsonParser(String json){
        this.json = json;
    }

    public ModelLayer getModelLayer(InputStream is){

        //List<Entity> entities = new ArrayList<Entity>();


        //Map<String, Object> javaRootMapObject = new Gson().fromJson("", Map.class);

        //javaRootMapObject.



       // com.google.gson.JsonParser  parser = new com.google.gson.JsonParser();

       // JsonElement element=  parser.parse("");


        //JsonObject object = element.getAsJsonObject();

        //object.get




        return  null;
    }




}
