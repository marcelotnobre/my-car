package com.marcelo.api.dto;

import com.marcelo.api.domain.User;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private String login;
    private String phone;
    private LocalDate creationDate;
    private LocalDateTime lastLongin;

    public static UserResponseDTO toUserResponseDTO(User user) {
        if (user == null) return null;
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setBirthday(user.getBirthday());
        dto.setLogin(user.getLogin());
        dto.setPhone(user.getPhone());
        dto.setCreationDate(user.getCreationDate());
        dto.setLastLongin(user.getLastLongin());
        return dto;
    }
}
