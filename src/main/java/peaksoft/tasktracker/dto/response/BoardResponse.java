package peaksoft.tasktracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponse {
    private Long boardId;
    private String boardTitle;
    private String boardBackGround;
    private Boolean isFavorite;

}
