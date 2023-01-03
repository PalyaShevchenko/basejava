package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage{

    public abstract void clear();

    public abstract void update(Resume r);

    public abstract void save(Resume r);

    public abstract Resume get(String uuid);

    public abstract void delete(String uuid);

    public abstract Resume[] getAll();

    public abstract int size();

    protected abstract int getIndex(String uuid);

    protected final int isNotExist(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return index;
        }
    }

    protected final int isExist(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }
}
