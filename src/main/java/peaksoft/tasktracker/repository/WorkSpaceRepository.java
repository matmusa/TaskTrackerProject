package peaksoft.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.tasktracker.dto.request.WorkSpaceRequest;
import peaksoft.tasktracker.dto.response.SimpleResponse;
import peaksoft.tasktracker.dto.response.WorkSpaceResponse;
import peaksoft.tasktracker.entity.WorkSpace;

import java.util.List;
import java.util.Optional;

public interface WorkSpaceRepository extends JpaRepository<WorkSpace, Long> {

    Optional<WorkSpace> getWorkSpaceByAdminIdAndId(Long adminId, Long id);

}
