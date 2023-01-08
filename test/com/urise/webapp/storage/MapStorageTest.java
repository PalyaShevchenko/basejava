package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class MapStorageTest extends AbstractStorageTest{
    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    protected void assertArray(Resume[] resumes) {
        Arrays.sort(resumes);
        assertArrayEquals(ARRAY_EXPECTED, resumes);
    }
}
