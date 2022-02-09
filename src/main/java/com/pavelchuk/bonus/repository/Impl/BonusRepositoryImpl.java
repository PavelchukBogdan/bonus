package com.pavelchuk.bonus.repository.Impl;


import com.pavelchuk.bonus.dto.CreateBonus;
import com.pavelchuk.bonus.repository.BonusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;


@Repository
public class BonusRepositoryImpl implements BonusRepository {

    @Autowired
    private  JdbcTemplate jdbcTemplate;

    public BonusRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    public boolean existsByIdempotencyKey(UUID idempotencyKey) {
        return jdbcTemplate.queryForObject("SELECT exists (SELECT 1 FROM bonus WHERE idempotency_key = ?) ", Boolean.class, idempotencyKey);
    }

    @Override
    public int softDeleteById(UUID id) {
       return jdbcTemplate.update("UPDATE bonus SET deleted = TRUE, deleted_at = now() WHERE id = ?", id);

    }

    @Override
    public void createBonus(CreateBonus bonus) {
        jdbcTemplate.update("INSERT INTO bonus (id, bonus_card_id, amount, idempotency_key, created_at) VALUES (?,?,?,?, ?)",
                UUID.randomUUID(),
                bonus.getBonusCardId(),
                bonus.getAmount(),
                bonus.getIdempotencyKey(),
                LocalDateTime.now());
    }


}
