package com.panandafog.mt_server.dummy;

import com.panandafog.mt_server.dummy.Dummy;
import org.springframework.data.repository.CrudRepository;

public interface DummiesRepository extends CrudRepository<Dummy, Long> {
}
