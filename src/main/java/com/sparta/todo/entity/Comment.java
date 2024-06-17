package com.sparta.todo.entity;

import com.sparta.todo.dto.commentRequest.CommentCreateRequestDto;
import com.sparta.todo.dto.commentRequest.CommentUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "content", nullable = false, length = 30)
    private String content;
    @Column(name = "userName", nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    public Comment(CommentCreateRequestDto createRequestDto, Todo todo){
        this.username =  createRequestDto.getUsername();
        this.content = createRequestDto.getContent();
        this.todo = todo;
    }

    public void update(CommentUpdateRequestDto updateRequestDto){
        this.content = updateRequestDto.getContent();
    }

    public boolean isUserNameMatch(String userName){
        return Objects.equals(this.getUsername(),userName);
    }

}
