package com.apexsoft.framework.recaptcha;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hanmomhanda on 14. 7. 28.
 */
public class RecaptchaServiceImpl implements RecaptchaService {

    public String verify(HttpServletRequest request) {

        String remoteAddr = request.getRemoteAddr();
        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
        reCaptcha.setPrivateKey("6LdClPcSAAAAAI4sVOUxiIEVUXUNJ0f7GcfLbK3A");

        String challenge = request.getParameter("recaptcha_challenge_field");
        String uresponse = request.getParameter("recaptcha_response_field");
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);

        if (reCaptchaResponse.isValid()) {
            // 성공 시 다음 화면 URL 등 부가 정보를 위해 JSON 반환 구현 가능
            return "Answer was entered correctly!";
        } else {
            // 실패 시 부가 정보를 위해 JSON 반환 구현 가능
            return "Answer is wrong";
        }
    }
}
