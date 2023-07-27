package peaksoft.tasktracker.repository.jdbcTemplateService;

import peaksoft.tasktracker.dto.response.CommentResponse;

import java.util.List;

public interface CommentJdbcTemplateService {
    List<CommentResponse> getAllComments(Long userId);
    CommentResponse getCommentById(Long userId, Long commentId);

}
