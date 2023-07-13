package peaksoft.tasktracker.dto.response;

import lombok.Builder;
import peaksoft.tasktracker.enums.Role;
@Builder
public record AuthenticationResponse(
        String token,
        String email,
        Role role

) {
}
