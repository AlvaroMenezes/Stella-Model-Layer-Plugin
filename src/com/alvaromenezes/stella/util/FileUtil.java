package com.alvaromenezes.stella.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by alvaromenezes on 5/18/17.
 */
public class FileUtil {

    public void createNewFile(String path, String name, String text) {

        try {
            File file = new File(path + "//" + name + ".java");
            if (file.exists())
                file.delete();

            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(text);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
