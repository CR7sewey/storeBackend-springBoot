package com.mike.store.services;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IGeneralService<T> {

    public T getById(@PathVariable Long id);
    public List<T> getAll();
    public T insert(@RequestBody T obj);
    public T update(@PathVariable Long id, @RequestBody T obj);
    public T delete(@PathVariable Long id);

}
