package com.sparta.todo.controller;

import com.sparta.todo.dto.todoRequest.TodoCreateRequestDto;
import com.sparta.todo.dto.todoRequest.TodoDeleteRequestDto;
import com.sparta.todo.dto.todoRequest.TodoUpdateRequestDto;
import com.sparta.todo.dto.todoResponse.TodoResponseDto;
import com.sparta.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/todos")
    public TodoResponseDto createTodo(@Valid @RequestBody TodoCreateRequestDto todoCreateRequestDto){
        return todoService.createTodo(todoCreateRequestDto);
    }
    @GetMapping("/todos")
    public List<TodoResponseDto> getTodos(){
        return todoService.getTodos();
    }
    @GetMapping("/todos/{id}")
    public TodoResponseDto getTodo(@PathVariable Long id){
        return todoService.getTodo(id);
    }
    @PutMapping("/todos/{id}")
    public TodoResponseDto getTodo(@PathVariable Long id,@Valid @RequestBody TodoUpdateRequestDto todoUpdateRequestDto){
        return todoService.updateTodo(id, todoUpdateRequestDto);
    }
    @DeleteMapping("/todos/{id}")
    public Long getTodo(@PathVariable Long id,@Valid @RequestBody TodoDeleteRequestDto todoDeleteRequestDto){
        return todoService.deleteTodo(id, todoDeleteRequestDto);
    }
}
