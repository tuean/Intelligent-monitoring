package com.sys.supervision.service;

import com.sys.supervision.entity.db.Account;
import com.sys.supervision.model.BaseListResponse;
import com.sys.supervision.model.request.AccountSearchRequest;

public interface IAccountService {

    BaseListResponse<Account> getList(AccountSearchRequest request);

    void delete(Integer id);

    void edit(Account account);

}
