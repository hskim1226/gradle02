package com.apexsoft.ysprj.user.test;

import java.util.List;

/**
 * Created by hanmomhanda on 14. 8. 20.
 */
public interface TestService {

    public Application retrieveApplication();

    public JoinedApplication retrieveJoinedApplication();

    public List<EntireApplication> retrieveEntireApplication(String applNo);

}
