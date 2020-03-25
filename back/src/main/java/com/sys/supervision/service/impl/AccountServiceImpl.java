package com.sys.supervision.service.impl;

import com.sys.supervision.config.Constant;
import com.sys.supervision.dao.AccountMapper;
import com.sys.supervision.entity.db.Account;
import com.sys.supervision.enums.CommonStatus;
import com.sys.supervision.enums.RoleEnum;
import com.sys.supervision.helper.AccountHelper;
import com.sys.supervision.model.BaseListResponse;
import com.sys.supervision.model.request.AccountSearchRequest;
import com.sys.supervision.service.IAccountService;
import com.sys.supervision.service.ITokenService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private ITokenService tokenService;

    @Override
    public BaseListResponse<Account> getList(AccountSearchRequest request) {
        request.paramCheck();

        Integer total = accountMapper.countBySearch(request);
        if (total < 1) {
            return BaseListResponse.okWithList(new ArrayList(0), true, 0);
        }

        List<Account> list = accountMapper.selectBySearch(request);
        for (Account a : list) {
            a.setPwd(null);
            a.setToken(null);
            a.setRoles(RoleEnum.getValueByCode(a.getRole()));
            a.setStatusValue(CommonStatus.getValueByCode(a.getStatus()));
        }

        return BaseListResponse.okWithList(list, list.size() == request.getPageSize(), total);
    }

    @Override
    public void delete(Integer id) {
        if (id == null || id < 1) {
            throw new IllegalArgumentException(Constant.ParamErrorMessage);
        }
        accountMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void edit(Account account) {
        // 修改
        if (account.getId() != null) {
            if (account.getPwd() != null) {
                String token = tokenService.generateToken(account.getAccount());
                account.setToken(token);
            }
            accountMapper.updateByPrimaryKeySelective(account);
        }

        // 新增
        if (account.getId() == null) {
            if (account.getPwd() == null) throw new IllegalArgumentException("密码为空");
            String token = tokenService.generateToken(account.getAccount());
            account.setToken(token);
            account.setCreater(AccountHelper.get().getAccount());
            account.setCreateAt(new Date());
            account.setModifier(AccountHelper.get().getAccount());
            account.setModifyAt(new Date());
            accountMapper.insert(account);
        }
    }
}
