package com.apexsoft.ysprj.applicants.application

import com.apexsoft.framework.common.vo.ExecutionContext
import com.apexsoft.ysprj.applicants.application.control.BasisController
import com.apexsoft.ysprj.applicants.application.domain.Application
import com.apexsoft.ysprj.applicants.application.domain.Basis
import com.apexsoft.ysprj.applicants.application.service.BasisService
import com.apexsoft.ysprj.applicants.application.service.BasisServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification;

/**
 * Created by hanmomhanda on 15. 4. 11.
 */
@ContextConfiguration(locations = ["classpath*:config/context-mvc.xml", "classpath*:/spring/context-*.xml"])
class BasisTest extends Specification {

    MockMvc mockMvc

    @Autowired
    BasisService basisService

    def setup() {
        def controller = new BasisController()
        basisService = Mock(BasisService.class)
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    def "basis 정보 생성 시 userId, modId, creId 세팅 정보 확인"() {

        given: "아이이 Abc555인 신청자가"
        String id = "Abc555"
        def application = new Application()
        application.setModId(id)
        application.setApplNo(null)
        def basis1 = new Basis()
        basis1.setApplication(application)

        when: "원서 정보 입력 후 신규 저장하면"
        // insert
        System.out.println(basisService == null)
        ExecutionContext ec1 = basisService.saveBasis(basis1)
        ExecutionContext ec2 = basisService.retrieveBasis(basis1)
        Basis basis2 = ((Map<String, Object>)ec2.getData()).get("basis")
        Application application2 = basis2.getApplication()

        then: "userId에는 id, creId에는 id, modId 에는 null이 입력된다."
        //  : userid == id && creId == id && modId == null
        application2.getUserId() == id
        application2.getCreDate() == id
        application2.getModId() == null
    }
}
