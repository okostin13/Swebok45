import javax.annotation.security.RolesAllowed;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@SessionScoped
@RolesAllowed("admin")
public class AdminController {

    public AdminController() {

    }

    private static final String adminPage="administration.xhtml";

    @Inject
    private ContentDAO contentDAO;

    private Chapter chapter;

    public String getAdminPage(){
        return adminPage;
    }

    public List<Chapter> findAllChapters(){
        return contentDAO.findAllChapters();
    }

    public Chapter findChapter(int id){
        chapter=contentDAO.findChapter(id);
        return chapter;
    }


    public void deleteChapter(Chapter chapter) {
        contentDAO.deleteChapter(chapter);
    }

    public void updateChapter(Chapter chapter)
    {
        contentDAO.updateChapter(chapter);
    }

    public void createChapter(Chapter chapter) {

      contentDAO.createChapter(chapter);

    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}

