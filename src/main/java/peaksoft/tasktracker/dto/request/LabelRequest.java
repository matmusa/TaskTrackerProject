package peaksoft.tasktracker.dto.request;

public record LabelRequest(
        String labelName,
        String color
) {
}
