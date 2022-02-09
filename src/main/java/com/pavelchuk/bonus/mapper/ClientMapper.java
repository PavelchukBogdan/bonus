package com.pavelchuk.bonus.mapper;

import com.pavelchuk.bonus.dto.BonusCard;
import com.pavelchuk.bonus.dto.Client;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Component
public class ClientMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {

        Client client = new Client();
        Timestamp deletedAt = rs.getTimestamp("deleted_at");
        client.setId((UUID) rs.getObject("id"));
        client.setName(rs.getString("name"));
        client.setBlocked(rs.getBoolean("blocked"));
        client.setBlockedAt(rs.getTimestamp("blocked_at").toLocalDateTime());
        client.setDeleted(rs.getBoolean("deleted"));
        client.setDeletedAt(deletedAt == null ? null : deletedAt.toLocalDateTime());
        client.setIdempotencyKey((UUID) rs.getObject("idempotencyKey"));
        return client;


    }
}
