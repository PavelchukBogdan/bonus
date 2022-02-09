package com.pavelchuk.bonus.service;

import com.pavelchuk.bonus.dto.CreateClient;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface ClientService {

    void deleteClient(UUID clientID);

   void blockedById(UUID uuid);

    void createClient(CreateClient client);



}
