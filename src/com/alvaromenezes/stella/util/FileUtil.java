package com.alvaromenezes.stella.util;

import com.alvaromenezes.stella.controller.StellaFormController;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by alvaromenezes on 5/18/17.
 */
public class FileUtil {


    public boolean hasFile(String path) {

        return Files.exists(Paths.get(path));

    }

    public void createNewFile(String path, String name, String text) throws Exception {

        try {
            File file = new File(path + "//" + name + ".java");
            if (file.exists())
                file.delete();

            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(text);
            output.close();
        } catch (IOException e) {
            throw e;
        }
    }

    public String readFile(String path) throws Exception {

        File file = new File(path);

        int len;
        char[] chr = new char[4096];
        final StringBuffer buffer = new StringBuffer();
        FileReader reader = null;
        try {
            reader = new FileReader(file);

            while ((len = reader.read(chr)) > 0) {
                buffer.append(chr, 0, len);
            }

            reader.close();
        } catch (FileNotFoundException e) {

            throw e;
        } catch (IOException e) {
            throw e;
        }
        return buffer.toString().trim();
    }


    public String getDefaultPath(){

        VirtualFile baseDir = StellaFormController.getProject().getBaseDir();

        VirtualFile  dir = baseDir.findChild("src");

        if(dir!=null){
         //Not is a default Android project!
            return dir.getCanonicalPath();
        }

        String androidDirNames [] = {"app","src","main","java"};

        for (String name : androidDirNames) {

            if(dir.findChild(name).exists()){
                dir = dir.findChild(name);
            }
        }

        return dir.getCanonicalPath();
    }

}
