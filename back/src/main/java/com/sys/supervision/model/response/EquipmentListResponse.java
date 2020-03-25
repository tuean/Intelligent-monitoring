package com.sys.supervision.model.response;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentListResponse {

    private Long id;

    private String name;

    private String longitude;

    private String latitude;

    private Integer equipmentStatus;

    private String equipmentStatusValue;

    private String period;

    private String parameter;

    private JSONObject params;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createAt;


}
