package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        if (size != 0) {
            Arrays.fill(storage, 0, size - 1, null);
            size = 0;
        }
    }

    public void save(Resume r) {
        if (findIndex(r.toString()) == -1) {
            if (size != 10000) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("ERROR Достигнут предел по количеству записей");
            }
        } else {
            System.out.println("ERROR " + r.toString() + " Такая запить уже есть");
        }
    }

    public void update(Resume r) {
        int index = findIndex(r.toString());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("ERROR Запись " + r.toString() + " для измененения не найдена.");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("ERROR Запись " + uuid + " не найдена.");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println(uuid + " удалена");
        } else {
            System.out.println("ERROR " + uuid + " не найдена");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
