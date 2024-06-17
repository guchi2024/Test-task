package com.sparta.todo.dto.todoRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoDeleteRequestDto {
    @NotNull
    private String password;
}
