package com.arjunsk.cognito.oauth.controller;

import com.arjunsk.cognito.oauth.dto.MessageDTO;
import java.security.Principal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

  @GetMapping("/message")
  public ResponseEntity<MessageDTO> message(Principal principal) {
    return ResponseEntity.ok(new MessageDTO("Admin message for " + principal.getName() ));
  }
}
