package com.arjunsk.cognito.oauth.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO  implements Serializable {
  private static final long serialVersionId = 0L;
  String message;
}
