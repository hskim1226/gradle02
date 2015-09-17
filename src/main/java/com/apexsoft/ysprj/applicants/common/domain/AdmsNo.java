package com.apexsoft.ysprj.applicants.common.domain;

import org.osgi.service.component.annotations.Component;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by hanmomhanda on 15. 9. 17.
 */
@Component
public class AdmsNo {

    @Value("#{app['adms.general']")
    private String general;

    @Value("#{app['adms.foreign']")
    private String foreign;

    @Value("#{app['adms.early']")
    private String early;

    public String getGeneral() {
        return general;
    }

    public String getForeign() {
        return foreign;
    }

    public String getEarly() {
        return early;
    }
}
