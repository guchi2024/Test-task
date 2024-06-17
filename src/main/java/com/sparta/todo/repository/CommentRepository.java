package com.sparta.todo.repository;

import com.sparta.todo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    //쿼리메소드는 엔티티필드명으로 작성.
    Optional<Comment> findByTodoIdAndId(Long todoId, Long id);
}
