package com.application.auth.model.response;

import lombok.Value;

@Value
public class CreateUserResponse {
    Integer userId;
    String userName;
}
