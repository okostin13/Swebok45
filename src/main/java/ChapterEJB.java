import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
@Stateless
public class ChapterEJB {
    @Inject
    EntityManager em;
    @EJB
    private ContentController contentController;
    private Chapter chapter;

    public ChapterEJB() {
    }

    public  void openChapter(int number){

        Chapter chapter = em.find(Chapter.class,number);
        contentController.setText(chapter.getContent());
    }

    public void CreateChapter(){
        chapter=new Chapter();
        chapter.setId(15);
        chapter.setContent("remote");
        chapter.setTitle("new");
         em.persist(chapter);
    }
}
