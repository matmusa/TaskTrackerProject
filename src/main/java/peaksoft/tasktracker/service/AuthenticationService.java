package peaksoft.tasktracker.service;

import peaksoft.tasktracker.dto.request.SignInRequest;
import peaksoft.tasktracker.dto.request.SignUpRequest;
import peaksoft.tasktracker.dto.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse signUp(SignUpRequest signUpRequest);

    AuthenticationResponse signIn(SignInRequest signInRequest);

}
