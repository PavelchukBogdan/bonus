package com.pavelchuk.bonus.repository;

import com.pavelchuk.bonus.dto.CreateClient;

import java.util.UUID;

public interface ClientRepository {



    boolean existsByIdempotencyKey(UUID idempotencyKey);

    int softDeleteById(UUID uuid);

    int blockedById(UUID uuid);

    void createClient(CreateClient client);

}


