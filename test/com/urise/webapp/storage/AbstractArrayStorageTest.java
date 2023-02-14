package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("A" + (char) i));
            }
        } catch (Exception e) {
            fail("overflow раньше положеного");
        }
        storage.save(new Resume("overflow"));
    }

    public static class MainFile {
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
}