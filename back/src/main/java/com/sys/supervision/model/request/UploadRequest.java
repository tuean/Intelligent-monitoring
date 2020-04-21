package com.sys.supervision.model.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadRequest {


    private String camera_id;

    private String task_id;

    private String image;



}
