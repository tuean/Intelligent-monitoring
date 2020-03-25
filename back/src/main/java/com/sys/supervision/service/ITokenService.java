package com.sys.supervision.service;

public interface ITokenService {

    void logout(String account, String token);

    String generateToken(String account);

}
