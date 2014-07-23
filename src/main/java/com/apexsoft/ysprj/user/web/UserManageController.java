package com.apexsoft.ysprj.user.web;

import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.ysprj.user.service.UsersService;
import com.apexsoft.ysprj.user.service.UsersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 7. 22.
 * Time: 오전 10:41
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/user")
public class UserManageController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(method= RequestMethod.GET)
    public String displayUserManageList(HttpServletRequest request){
        PageInfo<UsersVO> usersVOPageInfo = usersService.getUserPaginatedList();

        request.setAttribute("users", usersVOPageInfo.getData());
        request.setAttribute("usersTotal", usersVOPageInfo.getTotalRowCount());

        return "user/list";
    }
}
