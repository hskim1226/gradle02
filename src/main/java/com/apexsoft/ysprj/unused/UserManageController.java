package com.apexsoft.ysprj.unused;

import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.ysprj.user.domain.Users;
import com.apexsoft.ysprj.applicants.common.service.UsersAccountService;
import com.apexsoft.ysprj.user.web.form.UserSearchForm;
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
//@Controller
@RequestMapping("/admin/userXXX")
@Deprecated
public class UserManageController {

    @Autowired
    private UsersAccountService usersAccountService;

    @RequestMapping(method= RequestMethod.GET)
    public String displayUserManageList(UserSearchForm userSearchForm, HttpServletRequest request){
        PageInfo<Users> usersVOPageInfo = usersAccountService.getUserPaginatedList(userSearchForm);

        request.setAttribute("users", usersVOPageInfo.getData());
        request.setAttribute("usersTotal", usersVOPageInfo.getTotalRowCount());

        return "user/list";
    }
}
