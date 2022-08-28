package ru.vitstark.quizarius.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class PersonSignUpForm {
    @Email(message = "Неправильный формат электронной почты")
    private String email;

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    @Size(min = 2, max = 20, message = "Имя должно быть от 2 до 20 символов")
    private String username;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 5, max = 50, message = "Пароль должен быть от 5 до 50 символов")
    private String password;
}
