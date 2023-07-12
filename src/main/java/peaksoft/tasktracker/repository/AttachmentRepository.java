package peaksoft.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.tasktracker.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment,Long> {

}
