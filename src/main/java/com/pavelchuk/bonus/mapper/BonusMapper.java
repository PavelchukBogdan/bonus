package com.pavelchuk.bonus.mapper;


import com.pavelchuk.bonus.dto.Bonus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class BonusMapper implements RowMapper<Bonus> {


    @Override
    public Bonus mapRow(ResultSet resultSet, int i) throws SQLException {
        Timestamp deletedAt = resultSet.getTimestamp("deleted_at");
        Bonus bonus = new Bonus();
        bonus.setId((UUID) resultSet.getObject("id"));
        bonus.setAmount(resultSet.getBigDecimal("amount"));
        bonus.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        bonus.setDeletedAt(deletedAt == null ? null : deletedAt.toLocalDateTime());
        bonus.setDeleted(resultSet.getBoolean("deleted"));
        bonus.setIdempotencyKey((UUID) resultSet.getObject("idempotency_key"));
        bonus.setBonusCardId(resultSet.getString("bonus_card_id"));
        return bonus;

    }
}
