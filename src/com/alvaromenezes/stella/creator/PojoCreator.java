package com.alvaromenezes.stella.creator;

import com.alvaromenezes.pojo.Attribute;
import com.alvaromenezes.pojo.Entity;
import com.alvaromenezes.pojo.ModelLayer;
import com.alvaromenezes.stella.controller.StellaFormController;
import com.alvaromenezes.stella.util.FileUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by alvaromenezes on 5/29/17.
 */
public class PojoCreator {


    private ModelLayer model;
    private String path;
    private String packageName;

    private final String BODY = "body";
    private final String NAME = "name";
    private final String CRLF = "\r\n";
    private final String TAB = "\t";


    public PojoCreator(ModelLayer model, String path, String packageName) {

        this.model = model;
        this.path = path;
        this.packageName = packageName;

    }


    public void process() {

        List<Map<String, String>> pojos = new ArrayList<Map<String, String>>();
        ;
        for (Entity entity : model.entities) {

            Map<String, String> pojo = new HashMap<String, String>();

            pojo.put(BODY, formatPojoString(entity));
            pojo.put(NAME, entity.name);
            pojos.add(pojo);
        }

        try {
            save(pojos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String formatPojoString(Entity entity) {

        StringBuilder builder = new StringBuilder();

        builder.append(String.format("package %s.pojo;%s%s", packageName, CRLF, CRLF));


        builder.append(getImports(entity));

        builder.append(String.format(getDefaultHeader()));


        builder.append(String.format("public class %s {%s%s", entity.name, CRLF, CRLF));
        // builder.append(String.format("public class %s extends DaoEntity {%s%s", entity.name, CRLF, CRLF));

        for (Attribute attribute : entity.attributes) {

            String type = attribute.dataType;
            if (attribute.isArray) {
                type = String.format("List<%s>", attribute.dataType);
            }

            builder.append(String.format("%spublic  %s %s;%s", TAB, type, attribute.name, CRLF));
        }

        builder.append(String.format("%s%spublic %s(){%s%s}%s%s", CRLF, TAB, entity.name, CRLF, TAB, CRLF, CRLF));


        builder.append(String.format("%spublic %s(%s){%s", TAB, entity.name, getConstructorParameters(entity), CRLF));

        for (Attribute atribute : entity.attributes) {
            builder.append(String.format("%s%sthis.%s = %s;%s", TAB, TAB, atribute.name, atribute.name, CRLF));
        }

        builder.append(String.format("%s}%s}%s", TAB, CRLF, CRLF));

        return builder.toString();
    }

    private String getImports(Entity entity) {

        for (Attribute attribute : entity.attributes) {

            if (attribute.isArray) {

                return String.format("import java.util.List;%s%s", CRLF, CRLF);
            }
        }
        return "";
    }


    private String getDefaultHeader() {

        String today = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        StringBuilder builder = new StringBuilder();
        builder.append("/**");
        builder.append(CRLF);
        builder.append("*    Created by Stella Model Layer Plugin on " + today);
        builder.append(CRLF);
        builder.append(" */");
        builder.append(CRLF);
        return builder.toString();
    }


    public String getConstructorParameters(Entity entity) {

        StringBuilder builder = new StringBuilder();

        for (Attribute attribute : entity.attributes) {
            String type = attribute.dataType;
            if (attribute.isArray) {
                type = String.format("List<%s>", attribute.dataType);
            }

            builder.append(String.format("%s %s,", type, attribute.name));
        }

        String s = builder.toString();
        if (!s.isEmpty()) {
            s = s.substring(0, s.length() - 1);
        }

        return s;
    }


    public void save(List<Map<String, String>> pojos) throws Exception {

        FileUtil util = new FileUtil();

        String defaultPath = util.getDefaultPath();
        File dir = new File(String.format("%s/pojo",defaultPath));
        if (!dir.exists())
            dir.mkdir();

        for (Map<String, String> pojo : pojos) {
             util.createNewFile(dir.getAbsolutePath(), pojo.get(NAME).toString(), pojo.get(BODY).toString());
        }

    }


}
