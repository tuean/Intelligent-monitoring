package com.sys.supervision.helper;

import com.sys.supervision.model.VerificationInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class VerificationCache {

    private static Map<String, VerificationInfo> cache = new HashMap<>(1024);


    public static void put(String uuid, VerificationInfo verificationInfo) {
        cache.put(uuid, verificationInfo);
    }


    public static boolean check(String uuid, String random) {
        if (StringUtils.isBlank(uuid)) return false;
        VerificationInfo info = cache.get(uuid);
        if (info == null) return false;
        boolean result = uuid.trim().equals(info.getRandom()) &&
                LocalDateTime.now().isBefore(info.getExpireAt());
        cache.put(uuid, null);
        return result;
    }


    public static void clean() {
        for (Map.Entry<String, VerificationInfo> entry : cache.entrySet()) {
            VerificationInfo info = entry.getValue();
            if (LocalDateTime.now().isAfter(info.getExpireAt())) {
                cache.put(info.getRandom(), null);
            }
        }
    }



}
