package com.pavelchuk.bonus.controller;

import com.pavelchuk.bonus.dto.CreateBonus;
import com.pavelchuk.bonus.service.BonusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api("Контроллер для работы с бонусами")
@RestController
@RequestMapping("/bonus")
public class BonusController {

    @Autowired
    private BonusService bonusService;

    @ApiOperation(value = "Создание нового бонуса")
    @ApiResponse(code = 200, message = "Успешное создание")
    @PostMapping("/create")
    public ResponseEntity<?> createBonus(@RequestBody CreateBonus bonus){
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
