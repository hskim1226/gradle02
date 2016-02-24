package com.apexsoft.ysprj.applicants.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * Created by hanmomhanda on 16. 2. 24.
 */
@Controller
@RequestMapping(value = "/session")
public class SessionController {

    @RequestMapping("/showSession")
    @ResponseBody
    public String showSession(HttpServletRequest req, HttpServletResponse res) throws Exception {
//        res.getWriter().println("<h1>SHOW SESSION</h1>");
        HttpSession session = req.getSession();

        StringBuilder sb = new StringBuilder();
        sb.append("<h1>SHOW SESSION</h1>\n");
        sb.append("<hr/>\n");

        final Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attrName = attributeNames.nextElement();
            sb.append("<h4>" + attrName + " : " + session.getAttribute(attrName) + "</h4>\n");
        }
        return sb.toString();
    }
}
