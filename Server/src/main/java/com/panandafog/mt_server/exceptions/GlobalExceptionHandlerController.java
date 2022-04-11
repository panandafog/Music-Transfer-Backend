package com.panandafog.mt_server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

  @ExceptionHandler(CustomException.class)
  public void handleCustomException(HttpServletResponse res, CustomException ex) throws IOException {
    sendResult(res, ex, ex.getHttpStatus());
  }

  @ExceptionHandler(AccessDeniedException.class)
  public void handleAccessDeniedException(HttpServletResponse res, AccessDeniedException ex) throws IOException {
    sendResult(res, ex, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(Exception.class)
  public void handleException(HttpServletResponse res, Exception ex) throws IOException {
    sendResult(res, ex, HttpStatus.BAD_REQUEST);
  }

  private void sendResult(HttpServletResponse res, Exception exception, HttpStatus httpStatus) throws IOException {
    System.out.println("Exception: " + exception.getMessage());
    res.sendError(httpStatus.value(), exception.getMessage());
  }
}
