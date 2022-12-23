package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
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
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception{
        final Resume resume = new Resume(UUID_2);
        storage.update(resume);
        Assert.assertEquals(resume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception{
        final Resume resume = new Resume("dummy");
        storage.update(resume);
        Assert.assertEquals(resume, storage.get("dummy"));
    }

    @Test
    public void save() throws Exception{
        final Resume RESUME_4 = new Resume("uuid4");
        storage.save(RESUME_4);
        Assert.assertEquals(4, storage.size());
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
            Assert.fail("overflow раньше положеного");
        }
        storage.save(new Resume());
    }

    @Test
    public void delete() throws Exception{
        storage.delete(RESUME_1.getUuid());
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteExist() throws Exception{
        storage.delete("dummy");
    }

    @Test
    public void getAll() throws Exception{
        Resume[] resumes = {RESUME_1, RESUME_2, RESUME_3};
        Assert.assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    public void size() throws Exception{
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() throws Exception{
        Assert.assertEquals(RESUME_1, storage.get(UUID_1));
        Assert.assertEquals(RESUME_2, storage.get(UUID_2));
        Assert.assertEquals(RESUME_3, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}