package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serialization.SerializationStrategy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class PathStorage extends AbstractStorage<Path> {
    private final SerializationStrategy serializationStrategy;
    private final Path directory;

    protected PathStorage(String dir, SerializationStrategy objectStreamSerializer) {
        this.serializationStrategy = objectStreamSerializer;
        Objects.requireNonNull(dir, "directory must not be null");
        directory = Paths.get(dir);
        if(!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }
    @Override
    protected void doUpdate(Resume resume, Path path) {
        try {
            serializationStrategy.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path writing error ", resume.getUuid(), e);
        }
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("File creation error " + path, path.getFileName().toString(),
                    e);
        }
        doUpdate(resume, path);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return serializationStrategy.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path reading error" + path.getFileName().toString(),
                    path.getFileName().toString(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Cannot deleted error ", path.getFileName().toString(), e);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return getSteamPaths().map(this::doGet).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        getSteamPaths().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getSteamPaths().count();
    }

    private Stream<Path> getSteamPaths() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Path reading error", null, e);
        }

    }
}
