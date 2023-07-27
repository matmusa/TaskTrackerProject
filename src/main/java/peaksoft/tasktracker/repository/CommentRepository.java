package peaksoft.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.tasktracker.dto.response.CommentResponse;
import peaksoft.tasktracker.entity.Comment;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    Optional<Comment>getCommentByUserIdAndId(Long userId,Long commentId);

}
