import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DataBaseProducer {
    @Produces
    @PersistenceContext(unitName = "swebokUnit")
    private EntityManager em;
}
