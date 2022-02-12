package com.pavelchuk.bonus.controller;

import com.pavelchuk.bonus.dto.CreateBonusDto;
import com.pavelchuk.bonus.service.BonusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api("Контроллер для работы с бонусами")
@RestController
@RequiredArgsConstructor
@RequestMapping("/bonus")
public class BonusController {


    private final BonusService bonusService;

    @ApiOperation(value = "Создание нового бонуса")
    @ApiResponse(code = 200, message = "Успешное создание")
    @PostMapping("/create")
    public ResponseEntity<?> createBonus(@Validated @RequestBody CreateBonusDto bonus){
        bonusService.createBonus(bonus);
        return ResponseEntity.ok().build();
    }


    @ApiOperation(value = "Удаление бонуса")
    @ApiResponse(code = 200, message = "Успешное удаление")
    @PostMapping("delete")
    public ResponseEntity<?> deleteBonus(@RequestParam UUID uuid) {
        bonusService.deleteBonus(uuid);
        return ResponseEntity.ok().build();
    }
}
