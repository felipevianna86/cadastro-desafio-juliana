import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class test {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("julianeandrade");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {
        System.out.printf("Rodando");

        //insert
        LocalDate data = LocalDate.of(2000,11,12);
        Pessoa pessoa = new Pessoa(1L, "Gugu", data, "F", 12345678);

        entityManager.getTransaction().begin();
        entityManager.persist(pessoa);
        entityManager.getTransaction().commit();
    }
}
