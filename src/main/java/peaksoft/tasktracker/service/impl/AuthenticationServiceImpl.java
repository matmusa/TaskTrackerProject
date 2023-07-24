package peaksoft.tasktracker.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.tasktracker.config.JwtService;
import peaksoft.tasktracker.dto.request.SignInRequest;
import peaksoft.tasktracker.dto.request.SignUpRequest;
import peaksoft.tasktracker.dto.response.AuthenticationResponse;
import peaksoft.tasktracker.entity.User;
import peaksoft.tasktracker.enums.Role;
import peaksoft.tasktracker.exceptions.AlreadyExistException;
import peaksoft.tasktracker.exceptions.BadCredentialException;
import peaksoft.tasktracker.repository.UserRepository;
import peaksoft.tasktracker.service.AuthenticationService;



@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.email())) {
            log.error(String.format("User with email: %s already exist!", signUpRequest.email()));
            throw new AlreadyExistException(String.format("User with email: %s already exist!", signUpRequest.email()));
        }
        User user = new User();
        user.setFirstName(signUpRequest.firstName());
        user.setLastName(signUpRequest.lastName());
        user.setEmail(signUpRequest.email());
        user.setPassword(passwordEncoder.encode(signUpRequest.password()));
        user.setImage("Default image");
        user.setRole(Role.ADMIN);
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        if (signInRequest.email().isBlank()) {

            throw new BadCredentialException("Email doesn't exist!");
        }
        User user = userRepository.getUserByEmail(signInRequest.email())
                .orElseThrow(() -> {
                    log.error(String.format("User with email : %s not found !", signInRequest.email()));
                    return new AlreadyExistException(String.format("User with email : %s not found !", signInRequest.email()));
                });

        if (!passwordEncoder.matches(signInRequest.password(), user.getPassword())) {
            log.error("Incorrect password !");
            throw new BadCredentialException("Incorrect password !");
        }

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .token(jwtToken)
                .build();

    }


}
