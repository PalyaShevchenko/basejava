package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import static org.junit.Assert.assertArrayEquals;

public class ListStorageTest extends AbstractStorageTest{
    public ListStorageTest() {
        super(new ListStorage());
    }

    @Override
    protected void assertArray(Resume[] resumes) {
        assertArrayEquals(ARRAY_EXPECTED, resumes);
    }
}