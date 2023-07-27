package peaksoft.tasktracker.service;


import com.google.firebase.auth.FirebaseAuthException;
import jakarta.mail.MessagingException;
import peaksoft.tasktracker.dto.request.ResetPasswordRequest;
import peaksoft.tasktracker.dto.request.SignInRequest;
import peaksoft.tasktracker.dto.request.SignUpRequest;
import peaksoft.tasktracker.dto.response.AuthenticationResponse;
import peaksoft.tasktracker.dto.response.ResetPasswordResponse;
import peaksoft.tasktracker.dto.response.SimpleResponse;

public interface AuthenticationService {

    AuthenticationResponse signUp(SignUpRequest signUpRequest);

    AuthenticationResponse signIn(SignInRequest signInRequest);

    ResetPasswordResponse resetPassword(ResetPasswordRequest passwordRequest);

    SimpleResponse forgotPassword(String email, String link) throws MessagingException;

    AuthenticationResponse authWithGoogle(String tokenId) throws FirebaseAuthException;

}