package peaksoft.tasktracker.repository.jdbcTemplateService;

import peaksoft.tasktracker.dto.response.WorkSpaceInnerPageResponse;
import peaksoft.tasktracker.dto.response.WorkSpaceResponse;

import java.util.List;

public interface WorkSpaceJdbcTemplateService {


      List<WorkSpaceResponse> getAllWorkSpaces();
      WorkSpaceResponse getWorkSpaceById(Long workSpaceId);
      WorkSpaceInnerPageResponse getInnerPageResponse(Long workSpaceId);
}