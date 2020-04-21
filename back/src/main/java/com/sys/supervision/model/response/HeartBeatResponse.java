package com.sys.supervision.model.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeartBeatResponse {

    private String shot;

    private String shot_id;

    private List<ScheduledTask> scheduled_task;

    private List<CancelTask> cancel_task;


}
