package com.sys.supervision.service.impl;

import com.sys.supervision.dao.AccountMapper;
import com.sys.supervision.entity.db.Account;
import com.sys.supervision.enums.AccountStatus;
import com.sys.supervision.enums.RoleEnum;
import com.sys.supervision.model.login.LoginRequest;
import com.sys.supervision.model.login.LoginResponse;
import com.sys.supervision.model.login.LogoutRequest;
import com.sys.supervision.service.ILoginService;
import com.sys.supervision.service.ITokenService;
import com.sys.supervision.utils.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static com.sys.supervision.config.Constant.LoginErrorMessage;


@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private ITokenService tokenService;

    @Override
    public LoginResponse login(LoginRequest request) {
        String pwd = request.getPwd();
        String account = request.getAccount();
        Assert.notNull(pwd, LoginErrorMessage);
        Assert.notNull(account, LoginErrorMessage);

        String encryptedPwd = EncryptUtil.encryptPwd(pwd);

        Account accountFromDB = accountMapper.selectByAccount(account);
        if (accountFromDB == null) {
            throw new RuntimeException(LoginErrorMessage);
        }

        if (!encryptedPwd.equals(accountFromDB.getPwd().trim())) {
            throw new RuntimeException(LoginErrorMessage);
        }

        if (!AccountStatus.Normal.getCode().equals(accountFromDB.getStatus())) {
            String showValue = AccountStatus.getShowValueByCode(accountFromDB.getStatus());
            throw new RuntimeException(showValue);
        }

        tokenService.logout(account, accountFromDB.getToken());

        return LoginResponse.builder()
                .name(accountFromDB.getName())
                .account(account)
                .token(tokenService.generateToken(account))
                .role(accountFromDB.getRole())
                .roleName(RoleEnum.getByCode(accountFromDB.getRole()).name())
                .build();
    }

    @Override
    public void logout(LogoutRequest request) {

        tokenService.logout(request.getAccount(), null); // todo add token
    }

    @Override
    public Account getUserByToken(String token) {

        return accountMapper.selectByToken(token);
    }
}
