package com.sparta.todo.entity;

import com.sparta.todo.dto.todoRequest.TodoCreateRequestDto;
import com.sparta.todo.dto.todoRequest.TodoUpdateRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Table(name = "todo")
@NoArgsConstructor
public class Todo extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false, length = 50)
    private String title;
    @Column(name = "contents", nullable = true, length = 100)
    private String contents;
    @Email
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TodoStatus status;

    @OneToMany(mappedBy = "todo" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comment = new ArrayList<>();

    public Todo(TodoCreateRequestDto todoCreateRequestDto){
        this.title = todoCreateRequestDto.getTitle();
        this.contents = todoCreateRequestDto.getContents();
        this.email = todoCreateRequestDto.getEmail();
        this.password = todoCreateRequestDto.getPassword();
        this.status = TodoStatus.ACTIVE;
    }
    public void update(TodoUpdateRequestDto todoUpdateRequestDto){
        //Dirty Checking
        this.contents = (todoUpdateRequestDto.getContents());
        this.title = (todoUpdateRequestDto.getTitle());
        this.email = (todoUpdateRequestDto.getEmail());
    }
    public void delete(){
        this.status = TodoStatus.DELETED;
    }
    public boolean isPasswordMatch(String password){
        return Objects.equals(this.getPassword(),password);
    }
    public boolean isDeleted() {
        return this.status == TodoStatus.DELETED;
    }
}
