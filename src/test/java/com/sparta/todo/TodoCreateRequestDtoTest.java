package com.sparta.todo;

import com.sparta.todo.dto.todoRequest.TodoCreateRequestDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoCreateRequestDtoTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenTitleIsNull_thenValidationFails() {
        TodoCreateRequestDto dto = new TodoCreateRequestDto();
        dto.setTitle(null);
        dto.setContents("Some contents");
        dto.setEmail("test@example.com");
        dto.setPassword("somepassword");

        Set<ConstraintViolation<TodoCreateRequestDto>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertEquals("must not be null", violations.iterator().next().getMessage());
    }

    @Test
    public void whenEmailIsInvalid_thenValidationFails() {
        TodoCreateRequestDto dto = new TodoCreateRequestDto();
        dto.setTitle("Valid Title");
        dto.setContents("Some contents");
        dto.setEmail("invalid-email");
        dto.setPassword("somepassword");

        Set<ConstraintViolation<TodoCreateRequestDto>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertEquals("must be a well-formed email address", violations.iterator().next().getMessage());
    }

    @Test
    public void whenAllFieldsAreValid_thenValidationSucceeds() {
        TodoCreateRequestDto dto = new TodoCreateRequestDto();
        dto.setTitle("Valid Title");
        dto.setContents("Some contents");
        dto.setEmail("test@example.com");
        dto.setPassword("somepassword");

        Set<ConstraintViolation<TodoCreateRequestDto>> violations = validator.validate(dto);

        assertEquals(0, violations.size());
    }

    // 추가로 필요한 테스트 케이스를 여기에 작성할 수 있습니다.
}
