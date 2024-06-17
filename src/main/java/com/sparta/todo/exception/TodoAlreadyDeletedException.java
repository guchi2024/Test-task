package com.sparta.todo.exception;

public class TodoAlreadyDeletedException extends RuntimeException {
    public TodoAlreadyDeletedException(String message) {
        super(message);
    }
}
