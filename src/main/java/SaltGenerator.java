import java.security.SecureRandom;

/**
 * Created by Oleg on 13.10.2017.
 */
public class SaltGenerator {
    SecureRandom secureRandom = new SecureRandom();

     public  String getSalt(){
         byte bytes[] = new byte[20];
         secureRandom.nextBytes(bytes);
         String str ="";
         for (byte c : bytes){
             str+=c;
         }
         return str;
     }
}
