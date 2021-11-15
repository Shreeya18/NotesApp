package com.androidp.notesapp;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class stickyNote {

    String getStick(Context context){
        File file = new File(context.getFilesDir().getPath()+ "/napp.txt");
        StringBuilder text = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line=br.readLine())!= null){
                text.append(line);
                text.append("\n");
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return text.toString();
    }

    void setStick(String texttosave, Context context){
        String text = texttosave;
        FileOutputStream fos = null;
        try {
            fos = context.getApplicationContext().openFileOutput("napp.txt",context.MODE_PRIVATE);
            fos.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

