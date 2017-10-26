import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import java.util.List;

@ManagedBean
@SessionScoped
@RolesAllowed("admin")
public class AdminController {

    public AdminController() {

    }




    @Inject
    private ContentDAO contentDAO;

    private Chapter chapter = new Chapter();


    public String logOut(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
        session.invalidate();
        return "/index.xhtml?faces-redirect=true";
    }

    public List<Chapter> findAllChapters(){
        return contentDAO.findAllChapters();
    }

    public void findChapter(int id){

        chapter=contentDAO.findChapter(id);

    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }


    public void clearField(){
        chapter.setTitle("");
        chapter.setContent("");
        chapter.setTags("");
    }
    public void deleteChapter(int id) {

        contentDAO.deleteChapter(id);
        chapter=new Chapter();
    }

    public void updateChapter(){
        contentDAO.updateChapter(chapter);
    }

    public void createChapter() {

        Chapter newChapter = new Chapter();
        newChapter.setTitle(chapter.getTitle());
        newChapter.setContent(chapter.getContent());
        newChapter.setTags(chapter.getTags());
      contentDAO.createChapter(newChapter);

    }


}

