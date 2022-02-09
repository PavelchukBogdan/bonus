package com.pavelchuk.bonus.controller;


import com.pavelchuk.bonus.dto.CreateClient;
import com.pavelchuk.bonus.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api("Контроллер для работы с клиентом")
@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {


    private final ClientService clientService;


    @ApiOperation(value = "Создание нового клиента ")
    @ApiResponse(code = 200, message = "Успешное создание")
    @PostMapping("/create")
    public ResponseEntity<?> createClient(@RequestBody CreateClient dto) {
        clientService.createClient(dto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Удаление клиента")
    @ApiResponse(code = 200, message = "Успешное удаление")
    @PostMapping("/delete")
    public ResponseEntity<?> deleteClient(@RequestParam UUID uuid) {
        clientService.deleteClient(uuid);
        return ResponseEntity.ok().build();
    }

   @ApiOperation(value = "Блокировка клиента")
   @ApiResponse(code = 200, message = "Успешная блокировка клиента")
   @PostMapping("/blocked")
  public ResponseEntity<?> blockedClient(@RequestParam UUID uuid) {
      clientService.blockedById(uuid);
       return ResponseEntity.ok().build();
    }

}
