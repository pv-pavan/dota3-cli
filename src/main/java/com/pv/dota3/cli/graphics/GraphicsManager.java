package com.pv.dota3.cli.graphics;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by pv on 24/10/16.
 */
public class GraphicsManager {
    private static GraphicsManager ourInstance = new GraphicsManager();

    public static GraphicsManager getInstance() {
        return ourInstance;
    }

    private GraphicsManager() {
    }

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
