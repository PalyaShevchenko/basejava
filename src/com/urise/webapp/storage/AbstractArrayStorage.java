package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public final void update(Resume r) {
        storage[isNotExist(r.getUuid())] = r;
    }

    @Override
    public final void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        insertElement(r, isExist(r.getUuid()));
        size++;
    }

    @Override
    public final void delete(String uuid) {
        fillDeletedElement(isNotExist(uuid));
        storage[size - 1] = null;
        size--;
    }

    @Override
    public final Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public final int size() {
        return size;
    }

    @Override
    public final Resume get(String uuid) {
        return storage[isNotExist(uuid)];
    }

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillDeletedElement(int index);
}
