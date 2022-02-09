package com.pavelchuk.bonus.repository.Impl;

import com.pavelchuk.bonus.dto.CreateBonusCard;
import com.pavelchuk.bonus.repository.BonusCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class BonusCardRepositoryImpl implements BonusCardRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean existsByIdempotencyKey(UUID idempotencyKey) {
        return jdbcTemplate.queryForObject("SELECT exists (SELECT 1 FROM bonus_card WHERE idempotency_key = ?) ", Boolean.class, idempotencyKey);
    }

    @Override
    public int blockedClientCardByClientId(UUID uuid) {
        String sql = "UPDATE bonus_card SET blocked = TRUE, blocked_at = now() WHERE client_id = ?";
        return jdbcTemplate.update(sql, uuid);
    }

    @Override
    public int blockedClientCardId(UUID uuid) {
        String sql = "UPDATE bonus_card SET blocked = TRUE, blocked_at = now() WHERE id = ?";
        return jdbcTemplate.update(sql, uuid);

    }

    @Override
    public int deleteByClientId(UUID clientId) {
        String sql = "UPDATE bonus_card SET deleted_at = now(), deleted = true WHERE bonus_card.client_id = ?";
        return jdbcTemplate.update(sql, clientId);
    }

    @Override
    public int deleteById(UUID id) {
        String sql = "UPDATE bonus_card SET deleted_at = now(), deleted = true WHERE id = ?";
        return jdbcTemplate.update(sql, id);

    }

    @Override
    public void createBonusCard(CreateBonusCard bonusCard) {
        jdbcTemplate.update("INSERT INTO bonus_card (id, code, number, client_id , idempotency_key) VALUES (?,?,?,?,?)",
                UUID.randomUUID(),
                bonusCard.getCode(),
                bonusCard.getNumber(),
                bonusCard.getClientId(),
                bonusCard.getIdempotencyKey());
    }
}
