package com.aaron.ren.tinyid.base.factory;

import com.aaron.ren.tinyid.base.generator.IdGenerator;

/**
 * @author du_imba
 */
public interface IdGeneratorFactory {
    /**
     * 根据bizType创建id生成器
     * @param bizType
     * @return
     */
    IdGenerator getIdGenerator(String bizType);
}
