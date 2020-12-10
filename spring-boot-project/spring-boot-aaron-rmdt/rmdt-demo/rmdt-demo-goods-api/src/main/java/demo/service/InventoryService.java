package demo.service;

import annotation.Rmdt;
import demo.domain.Inventory;
import demo.vo.InventoryVo;

public interface InventoryService {


    @Rmdt(destination = "goods-decrease")
    boolean decrease(InventoryVo inventoryVo);

    Inventory getByProductId(Long productId);
}
