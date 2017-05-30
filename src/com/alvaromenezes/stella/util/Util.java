package com.alvaromenezes.stella.util;

/**
 * Created by alvaromenezes on 5/29/17.
 */
public class Util {


    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
