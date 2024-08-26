package com.gberard.learning.infrastructure.repository.jpa;

import com.gberard.learning.domain.model.Card;
import com.gberard.learning.domain.port.output.CardRepository;
import com.gberard.learning.infrastructure.repository.jpa.model.CardEntity;
import org.springframework.stereotype.Repository;

@Repository
public class DBCardRepository extends DBRepository<Card, CardEntity> implements CardRepository {

    public DBCardRepository(JpaCardRepository repository) {
        super(repository, CardEntity::toEntity, CardEntity::toDomain);
    }

}
