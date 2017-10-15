import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.*;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

@Named
@Stateless
@DataSourceDefinition(
        className = "com.mysql.jdbc.Driver",
        name = "java:global/Swebok/swebok",
        user = "root",
        password = "1234",
        portNumber=3306,
        serverName="localhost",
        databaseName = "swebok",
        properties = ("create = true")
)
public class ContentController {

    public ContentController() {
    }

    @Inject
    private EntityManager em;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text=" ";

    public String selectAllChapters(){
        TypedQuery<Chapter> query =
     em.createQuery("select c from chapter c",Chapter.class);
        List<Chapter> list= query.getResultList();
        return list.get(0).getContent();
    }


    public void createChapter(){
        Chapter chapter=new Chapter();
        chapter.setId(18);
        chapter.setContent("test");
       // EntityTransaction tx =em.getTransaction();
       // tx.begin();
        em.persist(chapter);
      //  tx.commit();
    }

    public  String openChapter(int number){

        Chapter chapter = em.find(Chapter.class,number);
        text= chapter.getContent();
        return text;
    }


    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
