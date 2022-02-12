package com.pavelchuk.bonus.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "bonus")
public class Bonus {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private BigDecimal amount;
    private Boolean deleted = false;
    private LocalDateTime deletedAt;
    private LocalDateTime createdAt;
    private UUID idempotencyKey;
    @ManyToOne
    @JoinColumn(name = "bonus_card_id", nullable = false)
    private BonusCard bonusCard;



}
