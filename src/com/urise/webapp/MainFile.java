package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

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

        scanDir(dir, 0);

        try (FileInputStream fis = new FileInputStream(filePath)){
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void scanDir(File dir, int numSpace) {
        File[] listFile = dir.listFiles();
        if (listFile != null) {
            char[] str = new char[numSpace];
            Arrays.fill(str, ' ');
            final String strSpace = new String(str);
            for (File name : dir.listFiles()) {
                if(name.isDirectory()) {
                    System.out.println(strSpace + "Dir : " + name.getName());
                    scanDir(name, numSpace + 1);
                } else {
                    System.out.println(strSpace + "File : " + name.getName());
                }
            }
        }

    }
}
