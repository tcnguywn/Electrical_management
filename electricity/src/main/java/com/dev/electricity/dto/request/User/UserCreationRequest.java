package com.dev.electricity.dto.request.User;


import com.dev.electricity.validator.DobConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    String username;
    String password;
    String fullName;

    @DobConstraint(min = 18, message = "INVALID_DOB")
            @NotNull
    LocalDate dob;
}
