package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage{
    private final List<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void update(Resume r) {
        list.set(isNotExist(r.getUuid()), r);
    }

    @Override
    public void save(Resume r) {
        isExist(r.getUuid());
        list.add(r);
    }

    @Override
    public Resume get(String uuid) {
        return list.get(isNotExist(uuid));
    }

    @Override
    public void delete(String uuid) {
        list.remove(isNotExist(uuid));
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (uuid.equals(list.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
