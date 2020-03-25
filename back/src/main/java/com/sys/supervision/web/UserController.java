package com.sys.supervision.web;

import com.sys.supervision.entity.db.Account;
import com.sys.supervision.model.BaseListResponse;
import com.sys.supervision.model.BaseResponse;
import com.sys.supervision.model.request.AccountSearchRequest;
import com.sys.supervision.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private IAccountService accountService;


    @RequestMapping(value = "/user/list", method = RequestMethod.POST, consumes = "application/json")
    public BaseListResponse<Account> getList(@RequestBody AccountSearchRequest request) {
        return accountService.getList(request);
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public BaseResponse delete(@RequestParam("id") Integer id) {
        accountService.delete(id);
        return BaseResponse.ok();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = "application/json")
    public BaseResponse edit(@RequestBody Account account) {
        accountService.edit(account);
        return BaseResponse.ok();
    }

}
