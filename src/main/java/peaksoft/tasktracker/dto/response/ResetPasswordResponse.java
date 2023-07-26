package peaksoft.tasktracker.dto.response;

import lombok.Builder;
import peaksoft.tasktracker.enums.Role;

@Builder
public record ResetPasswordResponse(Long userId,
                                    String firstName,
                                    String lastName,
                                    String email,
                                    Role role,
                                    String jwtToken,
                                    String message) {
}