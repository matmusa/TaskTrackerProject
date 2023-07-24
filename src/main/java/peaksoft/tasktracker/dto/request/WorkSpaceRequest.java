package peaksoft.tasktracker.dto.request;

import java.util.List;

public record WorkSpaceRequest(
        String name,
        List<String> emails

) {
}
