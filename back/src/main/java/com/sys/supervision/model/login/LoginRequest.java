package com.sys.supervision.model.login;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private String account;

    private String pwd;

}
