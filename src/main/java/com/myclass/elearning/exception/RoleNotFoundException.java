package com.myclass.elearning.exception;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException() {
        super("Role not fould");
    }
}
