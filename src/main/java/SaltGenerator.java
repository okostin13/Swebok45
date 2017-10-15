import javax.ejb.Stateless;
import javax.inject.Named;
import java.security.SecureRandom;

/**
 * Created by Oleg on 13.10.2017.
 */
@Named
@Stateless
public class SaltGenerator {
    SecureRandom secureRandom = new SecureRandom();
     EncodeUtil encodeUtil = new EncodeUtil();
     public  String getSalt(){
         byte bytes[] = new byte[20];
         secureRandom.nextBytes(bytes);
         String str ="";
         for (byte c : bytes){
             str+=(char)c;
         }
      // return  encodeUtil.base64encode("admin"+"8438-6-795-67-43-6103-33-92-33-73-51110-17-8-11810-102");
         return str;
     }
}
