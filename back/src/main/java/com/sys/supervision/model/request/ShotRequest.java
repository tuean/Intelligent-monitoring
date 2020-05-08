package com.sys.supervision.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShotRequest {

    private String devCode;

    private String every;

    private String on;

    private boolean shotImmediately;


}
