package com.modifyfgo.xfgo;

import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    private final static String filePath = "Android/data/com.modifyfgo.xfgo/files/";

    public static void saveFileToSDcard(String filename, String info) throws IOException {
        filename = filePath + filename;
        File file = new File(Environment.getExternalStorageDirectory(), filename);
        if(!file.exists()){
            file.createNewFile();
        }
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(info.getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getFileDataFromSdcard(String filename) {
        filename = filePath + filename;
        String result = null;
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        File file = new File(Environment.getExternalStorageDirectory(),
                filename);
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                int len = 0;
                byte[] buffer = new byte[1024];
                while ((len = fileInputStream.read(buffer)) != -1) {
                    arrayOutputStream.write(buffer, 0, len);
                }
                fileInputStream.close();
                result = new String(arrayOutputStream.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
