package com.sys.supervision.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduledTask {


    private String task_id;

    private String every;

    private String on;

}
