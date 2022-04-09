package com.panandafog.mt_server.controller;

import com.panandafog.mt_server.entity.AppUser;
import com.panandafog.mt_server.entity.UserDataDTO;
import com.panandafog.mt_server.entity.UserResponseDTO;
import com.panandafog.mt_server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  @Autowired
  private final ModelMapper modelMapper;
  @Autowired
  ApplicationEventPublisher eventPublisher;

  @PostMapping("/signin")
  public String login(@RequestParam String username, @RequestParam String password) {
    return userService.signin(username, password);
  }

  @PostMapping("/signup")
  public String signup(@RequestBody UserDataDTO user, HttpServletRequest request) {
    return userService.signup(modelMapper.map(user, AppUser.class), request);
  }

  @GetMapping("/resetpassword")
  public String resetPassword(@RequestParam String username, HttpServletRequest request) {
    return userService.resetPassword(username, request);
  }

  @GetMapping("/confirmsignup")
  public String confirmSignup(@RequestParam String token) {
    try {
      return userService.confirmSignup(token);
    } catch (Exception ex) {
      return ex.getMessage();
    }
  }

  @GetMapping("/confirmpasswordreset")
  public String confirmPasswordReset(
          @RequestParam String username,
          @RequestParam String newPassword,
          @RequestParam String token
  ) {
    try {
      return userService.confirmPasswordReset(username, newPassword, token);
    } catch (Exception ex) {
      return ex.getMessage();
    }
  }

  @DeleteMapping(value = "/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String delete(@PathVariable String username) {
    userService.delete(username);
    return username;
  }

  @GetMapping(value = "/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public UserResponseDTO search(@PathVariable String username) {
    return modelMapper.map(userService.search(username), UserResponseDTO.class);
  }

  @GetMapping(value = "/me")
  public UserResponseDTO whoami(HttpServletRequest req) {
    return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
  }

  @GetMapping("/refresh")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  public String refresh(HttpServletRequest req) {
    return userService.refresh(req.getRemoteUser());
  }

}
