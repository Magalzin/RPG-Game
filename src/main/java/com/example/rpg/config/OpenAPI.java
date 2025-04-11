package com.example.rpg.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Api de RPG",
        version = "V1",
        description = "Uma API para um trabalho da faculdade!"
    )
)

public class OpenAPI {}

