package peaksoft.tasktracker.service.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.tasktracker.config.JwtService;
import peaksoft.tasktracker.dto.request.UserRequest;
import peaksoft.tasktracker.dto.request.UserRequestImage;
import peaksoft.tasktracker.dto.response.SimpleResponse;

import peaksoft.tasktracker.dto.response.UserResponse;
import peaksoft.tasktracker.entity.User;
import peaksoft.tasktracker.entity.WorkSpace;
import peaksoft.tasktracker.enums.Role;
import peaksoft.tasktracker.exceptions.NotFoundException;
import peaksoft.tasktracker.repository.UserRepository;

import peaksoft.tasktracker.repository.WorkSpaceRepository;
import peaksoft.tasktracker.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final WorkSpaceRepository workSpaceRepository;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public SimpleResponse userUpdating(Long id, UserRequest userRequest) {

        User user = jwtService.getAuthentication();
        User users = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found id :" + id));
        users.setFirstName(userRequest.firstName());
        users.setLastName(userRequest.lastName());
        users.setEmail(userRequest.email());
        if (userRequest.password().equals(userRequest.repeatPassword())) {
            users.setPassword(passwordEncoder.encode(userRequest.password()));
        }
        if (user.getRole().equals(Role.ADMIN)) {
            userRepository.save(users);
            return SimpleResponse.builder().message("Ok").status(HttpStatus.OK).build();
        } else if (users.equals(user)) {
            userRepository.save(users);
        }
        return SimpleResponse.builder().message("Updated").status(HttpStatus.OK).build();
    }

    @Override
    public SimpleResponse updatingImage(Long id, UserRequestImage image) {

        User user = jwtService.getAuthentication();
        User users = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found id: " + id));
        users.setImage(image.image());
        if (user.getImage().equals(users.getImage())) {
            userRepository.save(users);
        }
        return SimpleResponse.builder().message("Save entity").status(HttpStatus.OK).build();
    }

    @Override
    public UserResponse getByIdUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("With id not found : " + id));
        return UserResponse.builder().id(user.getId()).firstName(user.getFirstName()).lastName(user.getLastName()).email(user.getEmail()).password(user.getPassword()).image(user.getImage()).build();
    }

    @Override
    public List<WorkSpace> userGetAllWorkSpace() {

        User user = jwtService.getAuthentication();
        List<WorkSpace> workSpaces = new ArrayList<>();
        for (WorkSpace w :workSpaceRepository.findAll() ) {
            for (User u : w.getUsers()) {
                if (u.equals(user)) {
                    workSpaces.add(w);
                }
            }
        }
        return workSpaces;
    }

    @Override
    public SimpleResponse deleteProfileUser(Long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("With id not found: " + id));
        userRepository.delete(user);
        return SimpleResponse.builder().message("Successfully deleted").status(HttpStatus.OK).build();
    }
}
