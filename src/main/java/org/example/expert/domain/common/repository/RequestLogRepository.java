package org.example.expert.domain.common.repository;

import org.example.expert.domain.common.entity.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {
}


