package com.sys.supervision.model;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerificationInfo {

    private String random;

    private String base64;

    private LocalDateTime expireAt;


}
