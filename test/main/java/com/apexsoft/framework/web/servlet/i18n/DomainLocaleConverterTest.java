package com.apexsoft.framework.web.servlet.i18n;

import com.apexsoft.framework.interceptor.DomainLocaleConverter;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: go2zo
 * Date: 2014-09-15
 * Time: 오후 9:13
 */
public class DomainLocaleConverterTest extends TestCase {

    private DomainLocaleConverter converter;

    private TestDomain domain;

    public void setUp() throws Exception {
        converter = new DomainLocaleConverter();

        domain = new TestDomain();
        domain.setName("김지호");
        domain.setCode("00001");
        domain.setNameXxen("Kim Jiho");
    }

    public void testConvert() throws Exception {
        converter.convert(domain, new Locale("en"));
        Assert.assertEquals(domain.getName(), domain.getNameXxen());
    }

    public class TestDomain {
        private String name;
        private String code;
        private String nameXxen;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getNameXxen() {
            return nameXxen;
        }

        public void setNameXxen(String nameXxen) {
            this.nameXxen = nameXxen;
        }
    }
}
