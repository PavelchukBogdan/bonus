package com.pavelchuk.bonus.repository;


import com.pavelchuk.bonus.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    Client findByName(String name);

    @Query(nativeQuery = true, value = "SELECT SUM(amount) AS balance FROM client c JOIN bonus_card bc on c.id = bc.client_id JOIN bonus b on bc.id = b.bonus_card_id" +
            " WHERE b.deleted = false AND client_id = ? AND code = ? GROUP BY(c.id, bc.code)")
    BigDecimal getClientBalance(UUID uuid, String code);

}


