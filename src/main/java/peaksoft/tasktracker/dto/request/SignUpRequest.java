package peaksoft.tasktracker.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import peaksoft.tasktracker.enums.Role;

public record SignUpRequest(
        String firstName,
        String lastName,
        String email,
        String password,

        @Enumerated(EnumType.STRING)
        Role role
) {
}
