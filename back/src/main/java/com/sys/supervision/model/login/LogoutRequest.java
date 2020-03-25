package com.sys.supervision.model.login;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogoutRequest {

    private String account;

}
