package com.apexsoft.ysprj.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hanmomhanda on 14. 8. 19.
 *
 * Controller단에서 아무거도 하지 않고 View로 포워딩만 해주는 컨트롤러 메서드 모음
 * 이 메서드들이 원래 컨트롤러 내에 있으면
 * 그 컨트롤러 내의 @ModelAttribute가 붙은 메서드들이 필요없이 자동 실행되어 낭비
 *
 */
@Controller
@RequestMapping("/")
public class SimpleForwardingController {

    @RequestMapping("application/agreement")
    public String checkAgreement() {
        return "application/agreement";
    }


}
