package com.pv.dota3.cli.graphics;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by pv on 24/10/16.
 */
public class TextUtil {

    public void drawFile(String resourceName){
        InputStream i = null;
        try {
            Class cls = this.getClass();
            i = cls.getClassLoader().getResourceAsStream(resourceName);//new FileInputStream("C:/logs.txt");
            BufferedReader r = new BufferedReader(new InputStreamReader(i));

            // reads each line
            String l;
            while((l = r.readLine()) != null) {
                System.out.println(l);
            }
            i.close();
        } catch (Exception ex){
            //TODO handle IO
            ex.printStackTrace();
        }
    }
}
