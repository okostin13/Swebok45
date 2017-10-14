import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * Created by Oleg on 13.10.2017.
 */
public class EncodeUtil {
     BASE64Encoder encoder = new BASE64Encoder();
    public   String base64encode(String text) {
        try {
            return encoder.encode(text.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
