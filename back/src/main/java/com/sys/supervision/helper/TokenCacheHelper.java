package com.sys.supervision.helper;

import com.sys.supervision.entity.db.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class TokenCacheHelper {

    private static ThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(1);

    private static int INIT_SIZE = 1024;

    private static final Map<String, Date> cacheExpire = new HashMap<>(INIT_SIZE);
    private static final Map<String, Account> cacheAccount = new HashMap<>(INIT_SIZE);

    public static void put(String token, Account account, Date tokenExpire) {
        cacheExpire.put(token, tokenExpire);
        cacheAccount.put(token, account);
    }

    public static void remove(String token) {
        cacheExpire.remove(token);
        cacheAccount.remove(token);
    }

    /**
     * check token expire time
     *
     * @param token
     * @return
     */
    public static Account valid(String token) {
        if (StringUtils.isBlank(token)) return null;
        Date expire = cacheExpire.get(token);
        if (expire == null) return null;
        Date date = new Date();
        boolean validFlag = expire.after(date);
        if (!validFlag) {
            remove(token);
        }
        return cacheAccount.get(token);
    }

    public static synchronized void clearAll() {
        cacheAccount.clear();
        cacheExpire.clear();
    }

    private static void clearExpired() {
        Date now = new Date();
        int success = 0;
        for (Map.Entry<String, Date> entry : cacheExpire.entrySet()) {
            Date expire = entry.getValue();
            if (now.after(expire)) {
                remove(entry.getKey());
                success++;
            }
        }
        log.info("expired size: {}", success);
    }


    private class task implements Runnable {

        @Override
        public void run() {
            clearExpired();
        }
    }

}
