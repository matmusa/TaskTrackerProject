package peaksoft.tasktracker.dto.request;

public record ResetPasswordRequest(Long userId,
                                   String newPassword,
                                   String repeatPassword) {
}
