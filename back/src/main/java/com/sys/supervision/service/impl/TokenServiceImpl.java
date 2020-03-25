package com.sys.supervision.service.impl;

import com.sys.supervision.dao.AccountMapper;
import com.sys.supervision.service.ITokenService;
import com.sys.supervision.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class TokenServiceImpl implements ITokenService {

    private static Integer Default_Token_Expire = 3;

    @Autowired
    private AccountMapper accountMapper;



    @Override
    public void logout(String account, String token) {
        log.info("{} logout and his token is {}", account, token);
        accountMapper.updateTokenByAccount(account, null, null);
    }

    @Override
    public String generateToken(String account) {

        Integer expireDays = Default_Token_Expire;

        String token = UUID.randomUUID().toString();

        Date tokenExpire = DateUtil.addDays(expireDays);

        accountMapper.updateTokenByAccount(account, token, tokenExpire);

        log.info("{} generate a new token {}", account, token);

        return token;
    }
}
