import javax.persistence.*;

@Entity(name = "chapter")
@NamedQuery(name="findAllChapters", query = "select c from chapter c")
public class Chapter {

    public Chapter() {
    }

    public Chapter(String content) {
        this.content = content;
    }

    public Chapter(int id,String title,String content){
        this.id = id;
        this.title=title;
        this.content=content;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    private String title;

    private String tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
