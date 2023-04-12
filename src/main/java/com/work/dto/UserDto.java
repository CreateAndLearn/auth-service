package com.work.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class UserDto {

  private UUID id;
  @JsonProperty(value = "first_name")
  private String firstName;
  @JsonProperty(value = "last_name")
  private String lastName;
  @JsonProperty(value = "email_id")
  private String email;
  @JsonProperty(value = "phone_no")
  private String phoneNo;
  @JsonProperty(value = "password")
  private String password;

}
