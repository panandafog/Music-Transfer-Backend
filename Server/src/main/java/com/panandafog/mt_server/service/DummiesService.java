package com.panandafog.mt_server.service;

import com.panandafog.mt_server.entity.Dummy;
import com.panandafog.mt_server.repository.DummiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DummiesService {
    @Autowired
    private DummiesRepository repository;

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void save(Dummy dummy) {
        repository.save(dummy);
    }

    public List<Dummy> getAll() {
        List<Dummy> tmp = new ArrayList<>();
        repository.findAll().forEach(tmp::add);
        return tmp;
    }
}