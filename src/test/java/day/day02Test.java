package day;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by hskim@apexsoft.co.kr on 2017-01-06.
 */
public class day02Test {
    @Test
    public void subtest() {
        day02 day =new day02();

        assertThat("3-1=2",day.sub(3,1),is(equalTo(2)));

    }
}
