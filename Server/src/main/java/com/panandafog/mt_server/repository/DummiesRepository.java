package com.panandafog.mt_server.repository;

import com.panandafog.mt_server.entity.Dummy;
import org.springframework.data.repository.CrudRepository;

public interface DummiesRepository extends CrudRepository<Dummy, Long> {
}
