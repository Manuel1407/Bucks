package com.decagon.reward_your_teacher.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass //TO ENABLE THE ABSTRACT CLASS FIELDS SHOW ON THE DB
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public AbstractEntity(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
