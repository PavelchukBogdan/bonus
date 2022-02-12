package com.pavelchuk.bonus.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "bonus_card")
public class BonusCard {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String code;
    private String number;
    private Boolean deleted = false;
    private Boolean blocked = false;
    private LocalDateTime blockedAt;
    private LocalDateTime deletedAt;
    private UUID idempotencyKey;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

}
