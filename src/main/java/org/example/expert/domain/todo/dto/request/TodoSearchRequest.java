package org.example.expert.domain.todo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoSearchRequest {
    private String keyword;
    private String managerNickname;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int page;
    private int size;
}
