package com.assignment.entity;

import lombok.Getter;

@Getter
public class MyException extends RuntimeException {
    public MyException(String message) {
        super(message);
    }
}
