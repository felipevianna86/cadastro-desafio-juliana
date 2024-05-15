import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class EnderecoDAO extends EnderecoService{

    private EntityManager entityManager;

    public EnderecoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrarEndereco(Enderecos enderecos) {
        validarEndereco(enderecos);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(enderecos);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Erro ao cadastrar endereço", e);
        }
    }

    public Enderecos buscarPorId(Long id) {
        return entityManager.find(Enderecos.class, id);
    }

    public void deletar(Long id) {
        Enderecos endereco = buscarPorId(id);
        if (endereco != null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(endereco);
                entityManager.getTransaction().commit();
            } catch (PersistenceException e) {
                entityManager.getTransaction().rollback();
                throw new RuntimeException("Erro ao excluir endereço", e);
            }
        } else {
            throw new IllegalArgumentException("Endereço não encontrado com o Id fornecido");
        }
    }

    public void atualizar(Enderecos enderecos) {
        validarEndereco(enderecos);
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(enderecos);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Erro ao atualizar endereço", e);
        }
    }

    public List<Enderecos> listarPorEndereco(String cidade, String estado) {
        Query query = entityManager.createQuery(
                "SELECT e FROM Enderecos e WHERE e.cidade = :cidade AND e.estado = :estado", Enderecos.class);
        query.setParameter("cidade", cidade);
        query.setParameter("estado", estado);
        return query.getResultList();
    }

    private void validarEndereco(Enderecos enderecos) {
        if (enderecos == null || enderecos.getEstado() == null || enderecos.getEstado().isEmpty()
                || enderecos.getCidade() == null || enderecos.getCidade().isEmpty()
                || enderecos.getCep() == null || enderecos.getCep().equals(enderecos.getCep())
                || enderecos.getLogradouro() == null || enderecos.getLogradouro().isEmpty()) {
            throw new IllegalArgumentException("Todos os campos do endereço são obrigatórios");
        }
    }
}