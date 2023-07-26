package peaksoft.tasktracker.dto.response;

import lombok.*;
import peaksoft.tasktracker.entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkSpaceResponse {

    private Long workSpaceId;
    private String workSpaceName;
    private Long adminId;
    private String adminFullName;
    private String adminImage;
    private Boolean isFavorite;

}
