package peaksoft.tasktracker.service;

import peaksoft.tasktracker.dto.request.UserRequest;
import peaksoft.tasktracker.dto.request.UserRequestImage;
import peaksoft.tasktracker.dto.response.SimpleResponse;
import peaksoft.tasktracker.dto.response.UserProfile;
import peaksoft.tasktracker.dto.response.UserResponse;
import peaksoft.tasktracker.entity.User;
import peaksoft.tasktracker.entity.WorkSpace;

import java.util.List;

public interface UserService {

    SimpleResponse userUpdating(Long id,UserRequest userRequest);

    SimpleResponse updatingImage(Long id, UserRequestImage image);

    UserResponse getByIdUser(Long id);

    List<WorkSpace> userGetAllWorkSpace();

    SimpleResponse deleteProfileUser(Long id);

}
