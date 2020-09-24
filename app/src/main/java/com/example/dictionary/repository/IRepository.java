package com.example.dictionary.repository;

import android.content.Context;


import java.io.File;
import java.util.List;
import java.util.UUID;

public interface IRepository<E> {
    E get(UUID uuid);
    void update(E e);
    List<E> getLists(String search);
    void delete(E e);
    void insert(E e);
}
