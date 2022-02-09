package com.pavelchuk.bonus.service.impl;

import com.pavelchuk.bonus.dto.BalanceDetailing;
import com.pavelchuk.bonus.repository.BalanceRepository;
import com.pavelchuk.bonus.service.BalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;

    @Override
    public BigDecimal getBalance(UUID clientId, String code) {
        try {
            return balanceRepository.getClientBalance(clientId, code);
        } catch (EmptyResultDataAccessException ex) {
            log.error("Balance by id {} and code = {} is empty", clientId, code);
            return BigDecimal.ZERO;
        }
    }

    @Override
    public List<BalanceDetailing> getBalanceByMonth(UUID clientId, String currency, YearMonth period) {
        LocalDateTime firstDayOfMonth = period.atDay(1).atStartOfDay();
        LocalDateTime lastDayOfMonth = period.atEndOfMonth().plus(1, DAYS).atStartOfDay();
        return balanceRepository.getBalanceDetailing(clientId, currency, firstDayOfMonth, lastDayOfMonth);
    }

}
