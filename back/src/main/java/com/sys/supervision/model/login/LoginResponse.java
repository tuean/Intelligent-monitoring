package com.sys.supervision.model.login;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String account;

    private String name;

    private String token;

    private Integer role;

    private String roleName;
}
