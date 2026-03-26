package csd214.bookstore.services;

import csd214.bookstore.entities.DigitalMusicEntity;

import csd214.bookstore.entities.ProductEntity;
import csd214.bookstore.repositories.IRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.NoSuchElementException;

public class DigitalMusicService {
    private final IRepository<ProductEntity> repository;

    public DigitalMusicService(IRepository<ProductEntity> repository) {
        this.repository = repository;
    }

    public int getDownloadCount(Long id) {
        ProductEntity p = repository.findById(id);
        if (p == null) throw new NoSuchElementException("Product not found: " + id);
        if (!(p instanceof DigitalMusicEntity dm)) throw new IllegalArgumentException("Product is not digital music: " + id);
        return dm.getDownloads();
    }

    public void incrementDownloadCount(Long id) {
        ProductEntity p = repository.findById(id);
        if (p == null) throw new NoSuchElementException("Product not found: " + id);
        if (p instanceof DigitalMusicEntity dm) {
            int next = dm.getDownloads() + 1;
            dm.setDownloads(next);
            repository.save(dm);
        }
    }
}