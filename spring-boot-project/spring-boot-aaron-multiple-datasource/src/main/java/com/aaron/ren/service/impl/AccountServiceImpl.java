package com.aaron.ren.service.impl;

import com.aaron.ren.dao.AccountDao;
import com.aaron.ren.service.AccountService;
import com.baomidou.dynamic.datasource.annotation.DS;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AccountServiceImpl implements AccountService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountDao accountDao;

    private AtomicInteger nums =new AtomicInteger(0);

    @DS(value = "account-ds")
    @Transactional(propagation = Propagation.REQUIRES_NEW) // 开启新事物
    public void reduceBalance(Long userId, Integer price) throws Exception {
        logger.info("[reduceBalance] 当前 XID: {}", RootContext.getXID());
		int i = nums.incrementAndGet();
		if(i==1){
			Thread.sleep(3000);
		}else if(i==2){
			Thread.sleep(2000);
		}else if(i==3){
			Thread.sleep(1000);
		}

        // 检查余额
        checkBalance(userId, price);

        logger.info("[reduceBalance] 开始扣减用户 {} 余额,线程是{}", userId,Thread.currentThread().getId());
        // 扣除余额
        int updateCount = accountDao.reduceBalance(price);
        // 扣除成功
        if (updateCount == 0) {
            logger.warn("[reduceBalance] 扣除用户 {} 余额失败", userId);
            throw new Exception("余额不足");
        }
        logger.info("[reduceBalance] 扣除用户 {} 余额成功", userId);
    }

    private void checkBalance(Long userId, Integer price) throws Exception {
        logger.info("[checkBalance] 检查用户 {} 余额", userId);
        Integer balance = accountDao.getBalance(userId);
        if (balance < price) {
            logger.warn("[checkBalance] 用户 {} 余额不足，当前余额:{}", userId, balance);
            throw new Exception("余额不足");
        }
    }

}
