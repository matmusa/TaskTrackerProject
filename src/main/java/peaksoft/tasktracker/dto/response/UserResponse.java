package peaksoft.tasktracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import peaksoft.tasktracker.enums.Role;

@NoArgsConstructor
@Builder
public class UserResponse{

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String image;


    public UserResponse(Long id, String firstName,String lastName ,String email, String password, String image) {
        this.id = id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email = email;
        this.password = password;
        this.image = image;
    }
}
