import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
@Stateless
public class UserDAO {


    public UserDAO() {
    }

    @Inject
    EntityManager em;


    EncodeUtil encodeUtil = new EncodeUtil();
    SaltGenerator saltGenerator = new SaltGenerator();


    public boolean CheckUser(String login,String password){
     UserEntity user = em.find(UserEntity.class,login);
     String encodedPass = encodeUtil.base64encode(password+user.getSalt());
     if (encodedPass.equals(user.getPassword())){
         return true;
     }
     else{
         return false;
     }
    }
}
