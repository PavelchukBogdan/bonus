package com.pavelchuk.bonus.mapper;

import com.pavelchuk.bonus.dto.BonusCard;
import com.pavelchuk.bonus.dto.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;


public class BonusCardMapper implements RowMapper<BonusCard> {

    @Override
    public BonusCard mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        BonusCard bonusCard = new BonusCard();
        Timestamp deletedAt = resultSet.getTimestamp("deleted_at");
        Timestamp blockedAt = resultSet.getTimestamp("blocked_at");

        bonusCard.setId((UUID) resultSet.getObject("id"));
        bonusCard.setDeletedAt(deletedAt == null ? null : deletedAt.toLocalDateTime());
        bonusCard.setDeleted(resultSet.getBoolean("deleted"));
        bonusCard.setIdempotencyKey((UUID) resultSet.getObject("idempotency_key"));
        bonusCard.setBlocked(resultSet.getBoolean("blocked"));
        bonusCard.setBlockedAt(blockedAt == null ? null : blockedAt.toLocalDateTime());
        bonusCard.setCode(resultSet.getString("code"));
        bonusCard.setNumber(resultSet.getString("number"));

        return bonusCard;

    }
}
