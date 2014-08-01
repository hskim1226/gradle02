package com.apexsoft.framework.birt;

import java.util.List;

/**
 * Created by Administrator on 2014-08-01.
 */
public interface BirtService {
    List<ApplicationInfo> getApplications();
    ApplicationInfo getApplication(String id);
}
