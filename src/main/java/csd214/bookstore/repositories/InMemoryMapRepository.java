package csd214.bookstore.repositories;

import csd214.bookstore.entities.ProductEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryMapRepository implements IRepository<ProductEntity> {
    private HashMap<Long, ProductEntity> db = new HashMap<>();
    private Long idCounter = 1L;

    @Override public ProductEntity save(ProductEntity e) {
        if (e.getId() == null) { e.setId(idCounter++); db.put(e.getId(),e); }
        return e;
    }

    @Override public ProductEntity findById(Long id) { return db.get(id); }
    @Override public List<ProductEntity> findAll() { return new ArrayList<ProductEntity>(db.values()); }
    @Override public void delete(Long id) { db.remove(id); }
    @Override public int deleteAll() { int size = db.size(); db.clear(); return size; }
    @Override public String getDataSourceType() { return "VOLATILE RAM (HashMap - Indexed Searh)"; }
    @Override public long count() { return db.size(); }
    @Override public void close() {}
}
