package com.example.tintory.configs;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * <p>Класс конфигурации Swagger.</p>
 * <p>Swagger - инструмент для документирования и
 * тестирования API.</p>
 *
 * @info - информация о API
 * @title - заголовок
 * @description - описание
 * @version - версия
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Tintory",
                description = "Генератор цветовых палитр",
                version = "0.0.1"
        )
)
public class OpenApiConfig {
    // Конфигурация Swagger

}