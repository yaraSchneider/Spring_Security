package com.example.security.security.dto;

import org.springframework.web.bind.annotation.RequestBody;


public record Login(@RequestBody String username, String password) {
}
