package com.gberard.learning.infrastructure.repository.jpa;

import com.gberard.learning.domain.model.Box;
import com.gberard.learning.domain.port.output.BoxRepository;
import com.gberard.learning.infrastructure.repository.jpa.model.BoxEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DBBoxRepository extends DBRepository<Box, BoxEntity> implements BoxRepository {

    private final JpaBoxRepository repository;

    public DBBoxRepository(JpaBoxRepository repository) {
        super(repository, BoxEntity::toEntity, BoxEntity::toDomain);
        this.repository = repository;
    }

    @Override
    public Optional<Box> findByPosition(int position) {
        return repository.findByPosition(position).map(BoxEntity::toDomain);
    }

}
