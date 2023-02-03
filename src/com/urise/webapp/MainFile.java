package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String filePath = "F:\\Pasha Java\\job1\\basejava\\.gitignore";
        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File(".\\src\\com\\urise");
        System.out.println(dir.isDirectory());
        System.out.println();

        scanDir(dir);

        try (FileInputStream fis = new FileInputStream(filePath)){
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void scanDir(File dir) {
        File[] listFile = dir.listFiles();
        if (listFile != null) {
            for (File name : dir.listFiles()) {
                System.out.println(name);
                if(name.isDirectory()) {
                    scanDir(name);
                }
            }
        }

    }
}
