package org.example.expert.domain.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "log")
@NoArgsConstructor
public class RequestLog extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String action;

    @Column
    private Long actorUserId;

    @Column
    private Long targetTodoId;

    @Column
    private Long targetUserId;

    @Column(length = 1000)
    private String message;

    public RequestLog(String action, Long actorUserId, Long targetTodoId, Long targetUserId, String message) {
        this.action = action;
        this.actorUserId = actorUserId;
        this.targetTodoId = targetTodoId;
        this.targetUserId = targetUserId;
        this.message = message;
    }
}


