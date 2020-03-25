package com.sys.supervision.service;

import com.sys.supervision.entity.db.Account;
import com.sys.supervision.model.login.LoginRequest;
import com.sys.supervision.model.login.LoginResponse;
import com.sys.supervision.model.login.LogoutRequest;

public interface ILoginService {

    LoginResponse login(LoginRequest request);

    void logout(LogoutRequest request);

    Account getUserByToken(String token);
}
