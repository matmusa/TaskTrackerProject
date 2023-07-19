package peaksoft.tasktracker.dto.response;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder

public class UserProfile {

    private Long id;
    private String fullName;

    public UserProfile(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
}
