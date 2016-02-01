package repl;

import org.junit.Test;

import java.net.URLEncoder;

/**
 * Created by hanmomhanda on 16. 2. 1.
 */
public class REPL {

    @Test
    public void urlEncodeTest() throws Exception {
        String input = "$$$";
        String urlEncoded = URLEncoder.encode(input, "UTF-8");
        System.out.println(urlEncoded);
        String convertSpecialCharacter = urlEncoded.replace("%24", "$");
        System.out.println(convertSpecialCharacter);
    }

}
