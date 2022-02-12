package com.pavelchuk.bonus.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private Boolean blocked = false;

    @NotBlank
    private Boolean deleted = false;

    private LocalDateTime blockedAt;

    private LocalDateTime deletedAt;

    @NotBlank
    private UUID idempotencyKey;


}
