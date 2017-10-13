import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Oleg on 13.10.2017.
 */
@Entity
@Table(name="userList")
public class UserEntity {
    @Id
    private String login;
}
