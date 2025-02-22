package com.calendarapp.auth.validator;

import com.calendarapp.exception.EmailAlreadyExistsException;
import com.calendarapp.exception.EmailWrongPatternException;
import com.calendarapp.repository.UserRepository;
import com.calendarapp.auth.model.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationValidator {

    private final UserRepository userRepository;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    public void validate(RegisterRequest registerRequest) {
         validateUsernameUniqueness(registerRequest.getUsername());
         validateEmailUniqueness(registerRequest.getEmail());
         validateEmailPattern(registerRequest.getEmail());
    }

    private void validateUsernameUniqueness(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new EmailAlreadyExistsException("Username " + username + " is already in use");
        }
    }

    private void validateEmailUniqueness(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("Email " + email + " is already in use");
        }
    }

    private void validateEmailPattern(String email) {
        if (!email.matches(EMAIL_PATTERN)) {
            throw new EmailWrongPatternException("Email " + email + " is incorrect");
        }
    }
}
