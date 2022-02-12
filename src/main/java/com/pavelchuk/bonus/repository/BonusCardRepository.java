package com.pavelchuk.bonus.repository;

import com.pavelchuk.bonus.entity.BonusCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface BonusCardRepository extends JpaRepository<BonusCard, UUID> {

    BonusCard findBonusCardByClientId(UUID clientId);

}
