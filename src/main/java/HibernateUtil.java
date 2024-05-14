
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private EntityManagerFactory factory;

    public HibernateUtil() {
        init();
    }

    private void init() {
        try {
            if(null == factory) {
                factory = Persistence.createEntityManagerFactory("julianeandrade");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
