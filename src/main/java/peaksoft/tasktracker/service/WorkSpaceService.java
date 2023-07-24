package peaksoft.tasktracker.service;

import jakarta.mail.MessagingException;
import peaksoft.tasktracker.dto.request.WorkSpaceRequest;
import peaksoft.tasktracker.dto.response.SimpleResponse;
import peaksoft.tasktracker.dto.response.WorkSpaceInnerPageResponse;
import peaksoft.tasktracker.dto.response.WorkSpaceResponse;

import java.util.List;

public interface WorkSpaceService {


    List<WorkSpaceResponse>getAllWorkSpaces();
    SimpleResponse saveWorkSpace(WorkSpaceRequest workSpaceRequest) throws MessagingException;
    WorkSpaceResponse getWorkSpaceById(Long id);
    SimpleResponse updateWorkSpaceById(Long id,WorkSpaceRequest workSpaceRequest);
    SimpleResponse deleteWorkSpaceById(Long id);
    WorkSpaceInnerPageResponse getWorkSpaceInnerPage(Long workSpaceId);



}
