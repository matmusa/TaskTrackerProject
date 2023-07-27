package peaksoft.tasktracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private Long id;
    private String comment;
    private Date createdDate;


}
