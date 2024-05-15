
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static EntityManagerFactory entityManagerFactory;
    public HibernateUtil() {
        init();
    }

    private void init() {
        try {
            if(null == entityManagerFactory) {
                entityManagerFactory = Persistence.createEntityManagerFactory("julianeandrade");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
