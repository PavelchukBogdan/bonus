package com.pavelchuk.bonus.controller;


import com.pavelchuk.bonus.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;
import java.util.UUID;

@Api("Контроллер для работы с балансом")
@RestController
@RequestMapping("/balance")
@RequiredArgsConstructor
public class BalanceController {


    private final ClientService clientService;


    @ApiOperation(value = "Получение баланса")
    @ApiResponse(code = 200, message = "Успешное получение баланса")
    @GetMapping("/total")
    public ResponseEntity<?> getBalance(@RequestParam UUID clientId,
                                        @RequestParam String currency) {
        return ResponseEntity.ok(clientService.getBalance(clientId, currency));
    }

    @ApiOperation(value = "Детализация по бонусам за месяц")
    @ApiResponse(code = 200, message = "Успешное получение детализации за месяц")
    @GetMapping("/details")
    public ResponseEntity<?> getBalanceByPeriod(@RequestParam UUID clientId,
                                                @RequestParam String currency,
                                                @RequestParam YearMonth period) {
        return ResponseEntity.ok(clientService.getBalanceByMonth(clientId, currency, period));
    }
}
