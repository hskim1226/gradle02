package com.apexsoft.ysprj.user.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by hanmomhanda on 15. 2. 26.
 */
@Component
public class UserValidator implements Validator{

    @Autowired
    MessageResolver messageResolver;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if (user.getUserId() == null || user.getUserId().length() == 0) {
            errors.rejectValue("userId", "U331",
                    new Object[]{"아이디"}, messageResolver.getMessage("U332"));
        }
        if (user.getPswd() == null || user.getPswd().length() == 0) {
            errors.rejectValue("pswd", "U331",
                    new Object[]{"비밀 번호"}, messageResolver.getMessage("U332"));
        }
        if (user.getMailAddr() == null || user.getMailAddr().length() == 0) {
            errors.rejectValue("mailAddr", "U331",
                    new Object[]{"메일 주소"}, messageResolver.getMessage("U332"));
        }
        if (user.getPrefLang() == null || user.getPrefLang().length() == 0) {
            errors.rejectValue("prefLang", "U331",
                    new Object[]{"선호 언어"}, messageResolver.getMessage("U332"));
        }
        if (user.getMobiNum() == null || user.getMobiNum().length() == 0) {
            errors.rejectValue("mobiNum", "U331",
                    new Object[]{"휴대폰 번호"}, messageResolver.getMessage("U332"));
        }
        if (user.getName() == null || user.getName().length() == 0) {
            errors.rejectValue("name", "U331",
                    new Object[]{"이름"}, messageResolver.getMessage("U332"));
        }
        if (user.getGend() == null || user.getGend().length() == 0) {
            errors.rejectValue("gend", "U331",
                    new Object[]{"성별"}, messageResolver.getMessage("U332"));
        }
        if (user.getBornDay() == null || user.getBornDay().length() == 0) {
            errors.rejectValue("bornDay", "U331",
                    new Object[]{"생년월일"}, messageResolver.getMessage("U332"));
        }
    }
}
