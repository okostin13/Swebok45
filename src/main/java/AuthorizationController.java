import javax.annotation.security.RunAs;
import javax.ejb.SessionContext;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

@Named
@SessionScoped
@RunAs("admin")
public class AuthorizationController {

    @Inject
    UserDAO userDAO;

    @Inject
    AdminController adminController;

    public AuthorizationController() {
    }

    @NotNull
    private String login="admin";

    @NotNull
    private String password ="admin";


    public String signIn() {

        if (userDAO.CheckUser(login, password)) {

          return  "adminPage.xhtml";
        }
        else return "index.html";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
