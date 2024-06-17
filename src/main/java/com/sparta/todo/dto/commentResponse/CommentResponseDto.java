package com.sparta.todo.dto.commentResponse;

import com.sparta.todo.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private String username;
    private String content;
    private LocalDateTime createAt;

    public CommentResponseDto(Comment saveComment) {
        this.username = saveComment.getUsername();
        this.content = saveComment.getContent();
        this.createAt = saveComment.getCreatedAt();
    }
}
