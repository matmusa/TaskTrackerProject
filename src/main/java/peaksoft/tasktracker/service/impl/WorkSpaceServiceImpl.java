package peaksoft.tasktracker.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import peaksoft.tasktracker.config.JwtService;
import peaksoft.tasktracker.dto.request.WorkSpaceRequest;
import peaksoft.tasktracker.dto.response.SimpleResponse;
import peaksoft.tasktracker.dto.response.WorkSpaceInnerPageResponse;
import peaksoft.tasktracker.dto.response.WorkSpaceResponse;
import peaksoft.tasktracker.entity.User;
import peaksoft.tasktracker.entity.UserWorkSpaceRole;
import peaksoft.tasktracker.entity.WorkSpace;
import peaksoft.tasktracker.enums.Role;
import peaksoft.tasktracker.exceptions.AlreadyExistException;
import peaksoft.tasktracker.exceptions.NotFoundException;
import peaksoft.tasktracker.repository.UserRepository;
import peaksoft.tasktracker.repository.UserWorkSpaceRoleRepository;
import peaksoft.tasktracker.repository.WorkSpaceRepository;
import peaksoft.tasktracker.repository.jdbcTemplateService.WorkSpaceJdbcTemplateService;
import peaksoft.tasktracker.service.WorkSpaceService;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class WorkSpaceServiceImpl implements WorkSpaceService {
    private final WorkSpaceRepository workSpaceRepository;
    private final JwtService jwtService;
    private final UserWorkSpaceRoleRepository userWorkSpaceRoleRepository;
    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;
    private final WorkSpaceJdbcTemplateService workSpaceJdbcTemplateService;

    @Override
    public List<WorkSpaceResponse> getAllWorkSpaces() {
      return workSpaceJdbcTemplateService.getAllWorkSpaces();
    }
    @Override
    public SimpleResponse saveWorkSpace(WorkSpaceRequest request) throws MessagingException {
        User user = jwtService.getAuthentication();
        WorkSpace workspace = new WorkSpace();
        workspace.setName(request.name());
        workspace.setIsFavorite(false);
        workspace.setAdminId(user.getId());
        UserWorkSpaceRole userWorkSpace = new UserWorkSpaceRole();
        userWorkSpace.setUser(user);
        userWorkSpace.setWorkSpace(workspace);
        userWorkSpace.setRole(Role.ADMIN);
        user.setUserWorkSpaceRoles(List.of(userWorkSpace));
        workspace.setUserWorkSpaceRoles(List.of(userWorkSpace));
        user.setWorkSpaces(List.of(workspace));
        workspace.setUsers(List.of(user));
        workSpaceRepository.save(workspace);
        List<String> invitationEmails = request.emails();
        if (!invitationEmails.isEmpty() && !invitationEmails.get(0).isBlank()) {
            for (String email : invitationEmails) {
                if (!userRepository.existsUserByEmail(email)) {
                    String inviteLink = "http://localhost:8080/swagger-ui/index.html#/invite-registration-api/registerUser";
                    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                    helper.setSubject(" Welcome to my workspace");
                    helper.setFrom("abduvohobuulumatmusa@gmail.com");
                    helper.setTo(email);
                    helper.setText("/workspaceId/" + workspace.getId() + "   " + inviteLink);
                    javaMailSender.send(mimeMessage);
                    log.info(String.format("WorkSpace with name %s successfully saved!", workspace.getName()));
                    return SimpleResponse.builder()
                            .status(HttpStatus.OK)
                            .message(String.format("WorkSpace with name %s successfully saved!", workspace.getName()))
                            .build();
                }
            }
//        }else {
//            for (String email: request.emails()
//                 ) {
//                Optional<User> userOptional = Optional.ofNullable(userRepository.getUserByEmail(email)
//                        .orElseThrow(() -> new AlreadyExistException("User with email doesnt exist!")));
//                if (userOptional.isPresent()) {
//                    User user1 = userOptional.get();
//                    ;
//                    UserWorkSpaceRole workSpaceRole = new UserWorkSpaceRole();
//                    workSpaceRole.setRole(Role.MEMBER);
//                    workSpaceRole.setWorkSpace(workspace);
//                    workSpaceRole.setUser(user1);
//                    userWorkSpaceRoleRepository.save(workSpaceRole);
                }


        log.info("Email doesn't exist !");
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Email doesn't exist !")
                .build();
    }

    @Override
    public WorkSpaceResponse getWorkSpaceById(Long id) {
        return workSpaceJdbcTemplateService.getWorkSpaceById(id);
    }


    @Override
    public SimpleResponse updateWorkSpaceById(Long id, WorkSpaceRequest workSpaceRequest) {
        User user = jwtService.getAuthentication();
        WorkSpace workSpace = workSpaceRepository.getWorkSpaceByAdminIdAndId(user.getId(), id)
                .orElseThrow(() -> {
                    log.error("WorkSpace with id " + id + " not found ! ");
                    return new NotFoundException("WorkSpace with id " + id + " not found ! ");
                });
        workSpace.setName(workSpaceRequest.name());
        workSpaceRepository.save(workSpace);
        log.info(String.format("WorkSpace with id %s  successfully updated !", id));
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("WorkSpace with id %s  successfully updated !", id))
                .build();
    }

    @Override
    public SimpleResponse deleteWorkSpaceById(Long id) {
        User user = jwtService.getAuthentication();
        WorkSpace workSpace = workSpaceRepository.getWorkSpaceByAdminIdAndId(user.getId(), id)
                .orElseThrow(() -> {
                    log.error("WorkSpace with id " + id + " not found ! ");
                    return new NotFoundException("WorkSpace with id " + id + " not found ! ");
                });
        workSpaceRepository.delete(workSpace);
        log.info(String.format("WorkSpace with id %s  successfully deleted !", id));
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("WorkSpace with id %s  successfully deleted !", id))
                .build();
    }

    @Override
    public WorkSpaceInnerPageResponse getWorkSpaceInnerPage(Long workSpaceId) {
        return workSpaceJdbcTemplateService.getInnerPageResponse(workSpaceId);
    }
}

