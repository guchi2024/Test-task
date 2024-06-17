package com.sparta.todo;

import com.sparta.todo.dto.todoRequest.TodoUpdateRequestDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoUpdateRequestDtoTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidTodoUpdateRequestDto() {
        TodoUpdateRequestDto dto = new TodoUpdateRequestDto();
        dto.setTitle("Valid Title");
        dto.setContents("Valid Contents");
        dto.setEmail("valid@example.com");
        dto.setPassword("ValidPassword");

        Set<ConstraintViolation<TodoUpdateRequestDto>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty(), "There should be no constraint violations");
    }

    @Test
    public void testInvalidTitle() {
        TodoUpdateRequestDto dto = new TodoUpdateRequestDto();
        dto.setTitle(null); // Title is not allowed to be null
        dto.setContents("Valid Contents");
        dto.setEmail("valid@example.com");
        dto.setPassword("ValidPassword");

        Set<ConstraintViolation<TodoUpdateRequestDto>> violations = validator.validate(dto);

        assertEquals(1, violations.size(), "There should be one constraint violation");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("title")), "Title should not be null");
    }

    @Test
    public void testInvalidEmail() {
        TodoUpdateRequestDto dto = new TodoUpdateRequestDto();
        dto.setTitle("Valid Title");
        dto.setContents("Valid Contents");
        dto.setEmail("invalid-email"); // Invalid email format
        dto.setPassword("ValidPassword");

        Set<ConstraintViolation<TodoUpdateRequestDto>> violations = validator.validate(dto);

        assertEquals(1, violations.size(), "There should be one constraint violation");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")), "Email should be invalid");
    }

    @Test
    public void testNullPassword() {
        TodoUpdateRequestDto dto = new TodoUpdateRequestDto();
        dto.setTitle("Valid Title");
        dto.setContents("Valid Contents");
        dto.setEmail("valid@example.com");
        dto.setPassword(null); // Password is not allowed to be null

        Set<ConstraintViolation<TodoUpdateRequestDto>> violations = validator.validate(dto);

        assertEquals(1, violations.size(), "There should be one constraint violation");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("password")), "Password should not be null");
    }
}
