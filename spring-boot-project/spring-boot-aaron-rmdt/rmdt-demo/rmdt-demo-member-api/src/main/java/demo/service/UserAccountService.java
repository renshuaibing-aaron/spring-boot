package demo.service;

import demo.domain.UserAccount;
import demo.vo.UserAccountVo;
import org.rmdt.annotation.Rmdt;

public interface UserAccountService {


    @Rmdt(destination="member-payment")
    boolean payment(UserAccountVo userAccountVo);

    UserAccount getByUserId(Long userId);
}
