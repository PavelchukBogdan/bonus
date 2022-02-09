package com.pavelchuk.bonus.repository.Impl;

import com.pavelchuk.bonus.dto.Client;
import com.pavelchuk.bonus.dto.CreateClient;
import com.pavelchuk.bonus.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {


    private final JdbcTemplate jdbcTemplate;



    @Override
    public boolean existsByIdempotencyKey(UUID idempotencyKey) {
        return jdbcTemplate.queryForObject("SELECT exists (SELECT 1 FROM client WHERE idempotency_key = ?) ", Boolean.class, idempotencyKey);
    }

    @Override
    public int softDeleteById(UUID clientId) {
        String sql = "UPDATE client SET deleted = true, deleted_at = now() WHERE id = ?";
        return jdbcTemplate.update(sql, clientId);
    }

    @Override
    public int blockedById(UUID uuid) {
        String sql = "UPDATE client SET blocked = TRUE, blocked_at = now() WHERE id = ? ";
        return jdbcTemplate.update(sql, uuid);
    }

    @Override
    public void createClient(CreateClient client) {
        jdbcTemplate.update("INSERT INTO client (id, name, idempotency_key) VALUES (?,?,?)",
                UUID.randomUUID(),
                client.getName(),
                client.getIdempotencyKey());
    }


}
