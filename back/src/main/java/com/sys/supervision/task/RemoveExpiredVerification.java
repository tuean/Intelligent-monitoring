package com.sys.supervision.task;

import com.sys.supervision.helper.VerificationCache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务：
 *  清理内存内过期验证码信息
 */
@Component
public class RemoveExpiredVerification {


    // 1000 ms * 60 * 60 = 1分钟一次
    @Scheduled(initialDelay = 1000, fixedDelay = 1000 * 60 * 60)
    public void run() {
        VerificationCache.clean();
    }

}
