package com.pavelchuk.bonus.mapper;

import com.pavelchuk.bonus.dto.BalanceDetailing;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BalanceDetailingMapper implements RowMapper<BalanceDetailing> {


    @Override
    public BalanceDetailing mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new BalanceDetailing(rs.getBigDecimal("amount"), rs.getTimestamp("created_at").toLocalDateTime());
    }
}
