package com.sys.supervision.helper;


import com.sys.supervision.entity.db.Account;

public class AccountHelper {

    private static ThreadLocal<Account> threadLocal = new ThreadLocal<>();


    public static void put(Account account) {
        threadLocal.set(account);
    }


    public static Account get() {
        return threadLocal.get();
    }

}
