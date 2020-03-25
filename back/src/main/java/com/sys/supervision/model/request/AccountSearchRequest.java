package com.sys.supervision.model.request;
import com.sys.supervision.model.BaseListRequest;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountSearchRequest extends BaseListRequest {

    private String account;

}
