package core.service;

import core.coordinator.RmdtTransactionCoordinator;
import org.rmdt.common.context.RmdtTransactionContext;

/**
 *@author luohaipeng
 */
public interface RmdtCoordinatorService {

    /**
     * 通过事务上下文对象各种状态，获取对应需要使用的事务协调者
     * @param remoteTransactionContext
     * @return
     */
    RmdtTransactionCoordinator getHandlerStrategy(RmdtTransactionContext remoteTransactionContext);
}
