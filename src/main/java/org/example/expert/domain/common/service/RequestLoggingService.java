package org.example.expert.domain.common.service;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.common.entity.RequestLog;
import org.example.expert.domain.common.repository.RequestLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RequestLoggingService {

    private final RequestLogRepository requestLogRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logManagerRegister(Long actorUserId, Long todoId, Long targetUserId, String message) {
        RequestLog log = new RequestLog(
                "MANAGER_REGISTER",
                actorUserId,
                todoId,
                targetUserId,
                message
        );
        requestLogRepository.save(log);
    }
}


