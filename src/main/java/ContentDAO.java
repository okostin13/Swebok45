import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.*;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.*;
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
public class ContentDAO {

    public ContentDAO() {
    }

    @Inject
    private EntityManager em;




    public List<Chapter> findChapters(String section){
        TypedQuery<Chapter> query =
     em.createQuery("select c from chapter c where c.section= :section order by c.title",Chapter.class);
        query.setParameter("section",section);
        return query.getResultList();

    }

    public List<String> findSections(){
        TypedQuery<String> query =
                em.createQuery("select distinct c.section from chapter c order by c.section",String.class);
        return query.getResultList();
    }

    public void deleteChapter(int id)
    {
        em.remove(findChapter(id));
    }

    public void updateChapter(Chapter chapter) {
      // Query query = em.createQuery("update chapter ch  set ch.title = :title, ch.content = :content, ch.tags=:tags where ch.id = :id");
      // query.setParameter("title",chapter.getTitle()).setParameter("content",chapter.getContent()).setParameter("id",chapter.getId()).setParameter("tags",chapter.getTags()).executeUpdate();
       em.merge(chapter);
    }

    public void createChapter(Chapter chapter){
        em.persist(chapter);
    }

    public  Chapter findChapter(int number){

        return  em.find(Chapter.class,number);

    }


    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
