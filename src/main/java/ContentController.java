import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Named
@Stateless
@DataSourceDefinition(
        className = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
        name = "java:global/Swebok/swebok",
        user = "root",
        password = "1234",
        portNumber=3306,
        serverName="localhost",
        databaseName = "swebok"

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



    public  void openChapter(int number){

        Chapter chapter = em.find(Chapter.class,number);
        text= chapter.getContent();
    }


    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
