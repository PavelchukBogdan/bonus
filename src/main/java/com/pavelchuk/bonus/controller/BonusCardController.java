package com.pavelchuk.bonus.controller;

import com.pavelchuk.bonus.dto.CreateBonusCard;
import com.pavelchuk.bonus.service.BonusCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api("Контроллер для работы с бнусными картами")
@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class BonusCardController {


    private final BonusCardService bonusCardService;

    @ApiOperation(value = "Создание новой карты")
    @ApiResponse(code = 200, message = "Успешное создание")
    @PostMapping("/create")
    public ResponseEntity<?> createBonusCard(@RequestBody CreateBonusCard bonusCard) {
        bonusCardService.createBonusCard(bonusCard);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Удаление бонусной карты")
    @ApiResponse(code = 200, message = "Успешное удаление")
    @PostMapping("/deleted")
    public ResponseEntity<?> deleteBonusCard(@RequestParam UUID id) {
        bonusCardService.deleteBonusCard(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Блокировка бонусной карты")
    @ApiResponse(code = 200, message = "Успешная блокировка карты")
    @PostMapping("/blocked")
    public ResponseEntity<?> blockedBonusCard(@RequestParam UUID uuid) {
        bonusCardService.blockedBonusCard(uuid);
        return ResponseEntity.ok().build();
    }

}
