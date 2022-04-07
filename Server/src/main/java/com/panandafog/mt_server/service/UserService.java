package com.panandafog.mt_server.service;

import com.panandafog.mt_server.account.verification.OnPasswordResetEvent;
import com.panandafog.mt_server.account.verification.OnRegistrationCompleteEvent;
import com.panandafog.mt_server.entity.AppUser;
import com.panandafog.mt_server.entity.PasswordResetToken;
import com.panandafog.mt_server.entity.VerificationToken;
import com.panandafog.mt_server.exceptions.CustomException;
import com.panandafog.mt_server.repository.PasswordResetTokenRepository;
import com.panandafog.mt_server.repository.UserRepository;
import com.panandafog.mt_server.repository.VerificationTokenRepository;
import com.panandafog.mt_server.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final ApplicationEventPublisher eventPublisher;
  private final VerificationTokenRepository verificationTokenRepository;
  private final PasswordResetTokenRepository passwordResetTokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationManager authenticationManager;

  public String signin(String username, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getAppUserRoles());
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public String signup(AppUser appUser, HttpServletRequest request) {
    System.out.println("signing up start");
    System.out.println(appUser.getUsername());
    if (!userRepository.existsByUsername(appUser.getUsername())) {
      System.out.println("New user detected");

      appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
      userRepository.save(appUser);

      String appUrl = "/users";

      System.out.println("appUrl for new user:");
      System.out.println("\"" + appUrl + "\"");
      System.out.println("Publishing event...");

      try {
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(appUser, request.getLocale(), appUrl));
      }  catch (NoSuchMessageException ex) {
        System.out.println("NoSuchMessageException: " + ex.getMessage());
      }
      System.out.println("Publish complete");

//      return jwtTokenProvider.createToken(appUser.getUsername(), appUser.getAppUserRoles());
      System.out.println("Successful signup");

      return "Successful";
    } else {
      System.out.println("Username is already in use");
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public String resetPassword(String username, HttpServletRequest request) {
    if (userRepository.existsByUsername(username)) {
      AppUser appUser = userRepository.findByUsername(username);
      System.out.println("Password reset");

      String appUrl = "/users";

      System.out.println("appUrl for new user:");
      System.out.println("\"" + appUrl + "\"");
      System.out.println("Publishing event...");

      try {
        eventPublisher.publishEvent(new OnPasswordResetEvent(appUser, request.getLocale(), appUrl));
      }  catch (NoSuchMessageException ex) {
        System.out.println("NoSuchMessageException: " + ex.getMessage());
      }
      System.out.println("Password reset publish complete");

//      return jwtTokenProvider.createToken(appUser.getUsername(), appUser.getAppUserRoles());
      return "Successful";
    } else {
      System.out.println("Username does not registered");
      throw new CustomException("Username does not registered", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public String confirmSignup(String token) {
    VerificationToken verificationToken = getVerificationToken(token);
    if (verificationToken == null) {
      System.out.println("Invalid token");
      throw new CustomException("Invalid token", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    AppUser appUser = verificationToken.getUser();
    Calendar cal = Calendar.getInstance();
    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
      System.out.println("Token expired");
      throw new CustomException("Token expired", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    appUser.setEnabled(true);
    userRepository.save(appUser);
    verificationTokenRepository.deleteAllByUserId(appUser.getId());
    return "Successful";
  }

  public String confirmPasswordReset(String username, String newPassword, String token) {
    PasswordResetToken verificationToken = getPasswordResetToken(token);
    if (verificationToken == null) {
      System.out.println("Invalid token");
      throw new CustomException("Invalid token", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    AppUser appUser = verificationToken.getUser();
    if (!Objects.equals(appUser.getUsername(), username)) {
      throw new CustomException("Token from another account", HttpStatus.UNPROCESSABLE_ENTITY);
    }
    Calendar cal = Calendar.getInstance();
    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
      System.out.println("Token expired");
      throw new CustomException("Token expired", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    appUser.setPassword(passwordEncoder.encode(newPassword));
    appUser.setEnabled(true);
    userRepository.save(appUser);
    passwordResetTokenRepository.deleteAllByUserId(appUser.getId());
    verificationTokenRepository.deleteAllByUserId(appUser.getId());
    return "Successful";
  }

  public void delete(String username) {
    userRepository.deleteByUsername(username);
  }

  public AppUser search(String username) {
    AppUser appUser = userRepository.findByUsername(username);
    if (appUser == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return appUser;
  }

  public AppUser whoami(HttpServletRequest req) {
    return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }

  public String refresh(String username) {
    return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getAppUserRoles());
  }

  public VerificationToken getVerificationToken(String token) {
    return verificationTokenRepository.findByToken(token);
  }

  public void createVerificationToken(AppUser user, String token) {
    VerificationToken myToken = new VerificationToken(token, user);
    verificationTokenRepository.save(myToken);
  }

  public PasswordResetToken getPasswordResetToken(String token) {
    return passwordResetTokenRepository.findByToken(token);
  }

  public void createPasswordResetToken(AppUser user, String token) {
    PasswordResetToken myToken = new PasswordResetToken(token, user);
    passwordResetTokenRepository.save(myToken);
  }
}
