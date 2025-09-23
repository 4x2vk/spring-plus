package org.example.expert.domain.todo.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.comment.entity.QComment;
import org.example.expert.domain.manager.entity.QManager;
import org.example.expert.domain.todo.dto.response.TodoSearchResponse;
import org.example.expert.domain.todo.entity.QTodo;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Todo> findByIdWithUser(long todoId) {
        QTodo todo = QTodo.todo;
        Todo result = queryFactory
                .selectFrom(todo)
                .leftJoin(todo.user).fetchJoin()
                .where(todo.id.eq(todoId))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public Page<TodoSearchResponse> searchTodos(String keyword, String managerNickname, 
                                              LocalDateTime startDate, LocalDateTime endDate, 
                                              Pageable pageable) {
        QTodo todo = QTodo.todo;
        QManager manager = QManager.manager;
        QComment comment = QComment.comment;

        // 검색 조건 생성
        BooleanExpression conditions = createSearchConditions(todo, manager, keyword, managerNickname, startDate, endDate);

        // 전체 개수 조회
        Long totalCount = queryFactory
                .select(todo.countDistinct())
                .from(todo)
                .leftJoin(manager).on(manager.todo.eq(todo))
                .where(conditions)
                .fetchOne();

        // 검색 결과 조회 (Projections 사용)
        List<TodoSearchResponse> results = queryFactory
                .select(Projections.constructor(TodoSearchResponse.class,
                        todo.id,
                        todo.title,
                        manager.countDistinct(),
                        comment.countDistinct()
                ))
                .from(todo)
                .leftJoin(manager).on(manager.todo.eq(todo))
                .leftJoin(comment).on(comment.todo.eq(todo))
                .where(conditions)
                .groupBy(todo.id, todo.title)
                .orderBy(todo.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(results, pageable, totalCount != null ? totalCount : 0);
    }

    private BooleanExpression createSearchConditions(QTodo todo, QManager manager, 
                                                    String keyword, String managerNickname,
                                                    LocalDateTime startDate, LocalDateTime endDate) {
        BooleanExpression conditions = null;

        // 제목 키워드 검색
        if (keyword != null && !keyword.trim().isEmpty()) {
            conditions = todo.title.containsIgnoreCase(keyword.trim());
        }

        // 담당자 닉네임 검색
        if (managerNickname != null && !managerNickname.trim().isEmpty()) {
            BooleanExpression nicknameCondition = manager.user.nickname.containsIgnoreCase(managerNickname.trim());
            conditions = conditions != null ? conditions.and(nicknameCondition) : nicknameCondition;
        }

        // 생성일 범위 검색
        if (startDate != null) {
            BooleanExpression startCondition = todo.createdAt.goe(startDate);
            conditions = conditions != null ? conditions.and(startCondition) : startCondition;
        }

        if (endDate != null) {
            BooleanExpression endCondition = todo.createdAt.loe(endDate);
            conditions = conditions != null ? conditions.and(endCondition) : endCondition;
        }

        return conditions;
    }
}


