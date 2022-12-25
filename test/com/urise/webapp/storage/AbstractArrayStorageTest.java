package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_NOT_EXIST = "dummy";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Test
    public void clear() throws Exception{
        storage.clear();
        assertSize(0);
        final Resume[] resumes = {};
        assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    public void update() throws Exception{
        final Resume resume = new Resume(UUID_2);
        storage.update(resume);
        assertSame(resume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception{
        final Resume resume = new Resume(UUID_NOT_EXIST);
        storage.update(resume);
    }

    @Test
    public void save() throws Exception{
        final Resume RESUME_4 = new Resume("uuid4");
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception{
        storage.save(RESUME_3);
    }

    @Test(expected = StorageException.class)
    public void overflow() throws Exception{
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (Exception e) {
            fail("overflow раньше положеного");
        }
        storage.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception{
        storage.delete(RESUME_1.getUuid());
        assertSize(2);
        assertGet(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception{
        storage.delete(UUID_NOT_EXIST);
    }

    @Test
    public void getAll() throws Exception{
        Resume[] resumes = {RESUME_1, RESUME_2, RESUME_3};
        assertArrayEquals(resumes, storage.getAll());
        assertSize(3);
    }

    @Test
    public void size() throws Exception{
        assertSize(3);
    }

    @Test
    public void get() throws Exception{
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(UUID_NOT_EXIST);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}