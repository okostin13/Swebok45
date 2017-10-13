import oracle.toplink.essentials.config.TopLinkProperties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;



@ManagedBean(name = "dao")
@SessionScoped
public class DAO implements Serializable {


    private EntityManagerFactory emf;
    private EntityManager em;
    private String login="root";
    private String password="root";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text=" ";

    public DAO() {
    }

    @PostConstruct
    public void init(){
        Map properties =  new HashMap();
        properties.put(TopLinkProperties.JDBC_USER,login);
        properties.put(TopLinkProperties.JDBC_PASSWORD,password);
        emf= Persistence.createEntityManagerFactory("NewPersistenceUnit",properties);
        em=emf.createEntityManager();
    }

    @PreDestroy
    public void disconnect(){
        if (em.isOpen())
        em.close();
    }

    public  void openChapter(int number){

        Chapter chapter = em.find(Chapter.class,number);
        text= chapter.getContent();
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
