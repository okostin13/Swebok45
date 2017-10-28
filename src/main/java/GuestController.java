import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class GuestController {
    public GuestController() {

    }

    @Inject
    private ContentDAO contentDAO;

    private Chapter chapter;



    public List<Chapter> findChapters(String section){
        return contentDAO.findChapters(section);
    }

    public List<String> findSections(){
        return contentDAO.findSections();
    }

    public void findChapter(int id){

       chapter=contentDAO.findChapter(id);

    }
    public List<String> getTags(){
        String tagsString="";
        List <String>  tags = new ArrayList<String>();
        if (chapter!=null&& chapter.getTags()!=null) {
            tagsString = chapter.getTags();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tagsString.length(); i++) {
                if (tagsString.charAt(i) != ' ') {
                    sb.append(tagsString.charAt(i));
                } else {
                    if (sb.charAt(0) != ' ')
                        tags.add(sb.toString());
                    sb = new StringBuilder();
                }
            }

            if (sb.charAt(0) != ' ')
                tags.add(sb.toString());
        }
     return tags;
}

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }


}
