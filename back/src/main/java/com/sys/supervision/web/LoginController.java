package com.sys.supervision.web;


import com.sys.supervision.annotation.NoAccess;
import com.sys.supervision.entity.db.Account;
import com.sys.supervision.enums.RoleEnum;
import com.sys.supervision.helper.AccountHelper;
import com.sys.supervision.helper.VerificationCache;
import com.sys.supervision.model.BaseResponse;
import com.sys.supervision.model.VerificationInfo;
import com.sys.supervision.model.login.LoginRequest;
import com.sys.supervision.model.login.LoginResponse;
import com.sys.supervision.model.login.LogoutRequest;
import com.sys.supervision.service.ILoginService;
import com.sys.supervision.service.ITokenService;
import com.sys.supervision.utils.DrawImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * 登录相关接口
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private ILoginService loginService;


    /**
     * 获取验证码图片 base64格式
     *
     * @return
     */
    @NoAccess
    @RequestMapping(value = "/verification", method = RequestMethod.POST)
    public BaseResponse getVerificationCode() {
        VerificationInfo info = DrawImageUtil.getImg();
        String uuid = UUID.randomUUID().toString();
        VerificationCache.put(uuid, info);
        return BaseResponse.ok(info.getBase64());
    }


    /**
     * 校验token是否存在，具体校验在aop中
     *
     * @return
     */
    @RequestMapping(value = "/token/check", method = RequestMethod.GET)
    public BaseResponse checkToken() {
        return BaseResponse.ok();
    }


    @NoAccess
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public BaseResponse<LoginResponse> login(@RequestBody LoginRequest request) {

        LoginResponse result = loginService.login(request);

        return new BaseResponse<LoginResponse>().okWithBody(result);
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public BaseResponse logout() {
        Account logging = AccountHelper.get();
        LogoutRequest request = LogoutRequest.builder().account(logging.getAccount()).build();
        loginService.logout(request);

        return BaseResponse.ok();
    }



//    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
//    public BaseResponse getUserByToken(@RequestParam("token") String token) {
//        Account account = loginService.getUserByToken(token);
//        return new BaseResponse().okWithBody(account);
//    }


}
