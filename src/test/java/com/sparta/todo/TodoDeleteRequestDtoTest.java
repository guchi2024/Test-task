package com.sparta.todo;
//package com.sparta.todo;


import com.sparta.todo.dto.todoRequest.TodoDeleteRequestDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoDeleteRequestDtoTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenPasswordIsNull_thenValidationFails() {
        TodoDeleteRequestDto dto = new TodoDeleteRequestDto();
        dto.setPassword(null);

        Set<ConstraintViolation<TodoDeleteRequestDto>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertEquals("must not be null", violations.iterator().next().getMessage());
    }

    @Test
    public void whenPasswordIsNotNull_thenValidationSucceeds() {
        TodoDeleteRequestDto dto = new TodoDeleteRequestDto();
        dto.setPassword("somepassword");

        Set<ConstraintViolation<TodoDeleteRequestDto>> violations = validator.validate(dto);

        assertEquals(0, violations.size());
    }
}
