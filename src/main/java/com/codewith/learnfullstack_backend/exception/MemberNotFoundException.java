package com.codewith.learnfullstack_backend.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(Long id) {
        super("Could not found the user with id " + id);
    }
}
