package com.pavelchuk.bonus.repository;


import com.pavelchuk.bonus.entity.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, UUID> {

    Bonus findByIdempotencyKey(UUID uuid);

    @Query(nativeQuery = true, value = "SELECT * FROM bonus b JOIN bonus_card bc on b.bonus_card_id = bc.id JOIN client c on bc.client_id = c.id " +
            "WHERE client_id = ? AND code = ? AND b.created_at >= ? AND b.created_at < ? AND b.deleted = false ORDER BY created_at")
    List<Bonus> getBalanceByMonth(UUID clientId, String currency, LocalDateTime firstDayOfMonth, LocalDateTime firstDayOfNextMonth);
}
