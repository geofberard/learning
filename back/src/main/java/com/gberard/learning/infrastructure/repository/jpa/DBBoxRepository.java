package com.gberard.learning.infrastructure.repository.jpa;

import com.gberard.learning.domain.model.Box;
import com.gberard.learning.domain.port.output.BoxRepository;
import com.gberard.learning.infrastructure.repository.jpa.model.BoxEntity;
import org.springframework.stereotype.Repository;

@Repository
public class DBBoxRepository extends DBRepository<Box, BoxEntity> implements BoxRepository {

    public DBBoxRepository(JpaBoxRepository repository) {
        super(repository, BoxEntity::toEntity, BoxEntity::toDomain);
    }

}
