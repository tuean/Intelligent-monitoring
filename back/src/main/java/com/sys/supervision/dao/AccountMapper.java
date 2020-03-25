package com.sys.supervision.dao;

import com.sys.supervision.entity.db.Account;
import com.sys.supervision.model.request.AccountSearchRequest;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKeyWithBLOBs(Account record);

    int updateByPrimaryKey(Account record);

    Account selectByAccount(@Param("account") String account);

    int updateTokenByAccount(@Param("account") String account,
                             @Param("token") String token,
                             @Param("tokenExpire") Date tokenExpire);

    Account selectByToken(@Param("token") String token);

    int countBySearch(AccountSearchRequest request);
    List<Account> selectBySearch(AccountSearchRequest request);
}