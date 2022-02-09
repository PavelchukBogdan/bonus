package com.pavelchuk.bonus.repository.Impl;

import com.pavelchuk.bonus.dto.BalanceDetailing;
import com.pavelchuk.bonus.mapper.BalanceDetailingMapper;
import com.pavelchuk.bonus.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BalanceRepositoryImpl implements BalanceRepository {

    private final JdbcTemplate jdbcTemplate;
    private final BalanceDetailingMapper balanceDetailingMapper;

    @Override
    public BigDecimal getClientBalance(UUID clientID, String code) {
        String sql = "SELECT SUM(amount) AS balance FROM client cl JOIN bonus_card bc on cl.id = bc.client_id\n" +
                "JOIN bonus b on bc.id = b.bonus_card_id WHERE b.deleted = false AND client_id = ? and code = ? GROUP BY (cl.id, bc.code)";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class, clientID, code);
    }

    @Override
    public List<BalanceDetailing> getBalanceDetailing(UUID clientId,
                                                      String currency,
                                                      LocalDateTime startDate,
                                                      LocalDateTime endDate) {
        String sql = "SELECT b.amount, b.created_at FROM bonus b JOIN bonus_card bc ON bc.id = b.bonus_card_id JOIN client c " +
                "    ON bc.client_id = c.id WHERE client_id = ? AND code = ? and b.created_at >= ? AND b.created_at < ? " +
                "    AND b.deleted = false " +
                "ORDER BY created_at";
        return jdbcTemplate.query(sql, balanceDetailingMapper, clientId, currency, startDate, endDate);

    }

}
