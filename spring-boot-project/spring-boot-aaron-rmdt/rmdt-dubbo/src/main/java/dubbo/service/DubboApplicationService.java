package dubbo.service;

import com.alibaba.dubbo.config.ApplicationConfig;
import core.service.RmdtApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *  @author luohaipeng
 */
@Service
public class DubboApplicationService implements RmdtApplicationService {

    /**
     * ApplicationConfig由Dubbo创建，创建后自动放到spring容器中
     */
    @Autowired
    private ApplicationConfig applicationConfig;

    @Override
    public String appName() {
        return Optional.ofNullable(applicationConfig).orElse(new ApplicationConfig("dubbo")).getName();
    }
}
