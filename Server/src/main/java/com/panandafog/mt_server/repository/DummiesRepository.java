package com.panandafog.mt_server.repository;

import com.panandafog.mt_server.entity.Dummy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DummiesRepository extends CrudRepository<Dummy, Long> {
}
