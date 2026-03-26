package csd214.bookstore.services;

import csd214.bookstore.entities.DigitalMusicEntity;

import csd214.bookstore.repositories.IRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.NoSuchElementException;

public class DigitalMusicService {
    private final IRepository<DigitalMusicEntity> repository;

    public DigitalMusicService(IRepository<DigitalMusicEntity> repository) {
        this.repository = repository;
    }

    public int getDownloadCount(Long id) {
        DigitalMusicEntity e = repository.findById(id);
        if (e == null) throw new NoSuchElementException("Digital music not found: " + id);
        return e.getDownloads();
    }

    public int incrementDownloadCount(Long id) {
        DigitalMusicEntity e = repository.findById(id);
        if (e == null) throw new NoSuchElementException("Digital music not found: " + id);
        synchronized (e) {
            int next = e.getDownloads() + 1;
            e.setDownloads(next);
            repository.save(e);
            return next;
        }
    }
}