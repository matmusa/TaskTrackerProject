package peaksoft.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.tasktracker.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
