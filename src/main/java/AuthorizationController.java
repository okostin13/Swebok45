import javax.annotation.security.RunAs;
import javax.ejb.SessionContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.Context;
import javax.validation.constraints.NotNull;

@ManagedBean
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
    private String login = "";

    @NotNull
    private String password = "";


    public String signIn() {

        if (userDAO.CheckUser(login, password)) {

            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.getSessionMap().put("user", login);

            return "admin/adminPage.xhtml?faces-redirect=true";
        } else {

            FacesContext ctx =  FacesContext.getCurrentInstance();
            ctx.addMessage("auth",new FacesMessage(FacesMessage.SEVERITY_WARN,"Неверное имя пользователя или пароль","invalid login or password"));
            return "authorization.xhtml";
        }
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
