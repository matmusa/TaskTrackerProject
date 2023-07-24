package peaksoft.tasktracker.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.tasktracker.dto.request.SignInRequest;
import peaksoft.tasktracker.dto.request.SignUpRequest;
import peaksoft.tasktracker.dto.response.AuthenticationResponse;
import peaksoft.tasktracker.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication API",description = "All authentication endpoints")
public class AuthenticationApi {

    private final AuthenticationService authenticationService;
    @Operation(summary = "SingUp", description = "Users can register")
    @PostMapping("/signUp")
    public AuthenticationResponse signUp(@RequestBody @Valid SignUpRequest signUpRequest){
        return authenticationService.signUp(signUpRequest);
    }
    @Operation(summary = "SingIn", description = "Only register users can login")
    @PostMapping("/signIn")
    public  AuthenticationResponse signIn(@RequestBody SignInRequest signInRequest){
        return authenticationService.signIn(signInRequest);
    }


}
