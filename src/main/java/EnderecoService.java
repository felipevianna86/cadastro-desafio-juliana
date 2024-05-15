import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;

public class EnderecoService {

    @PersistenceContext
    private EntityManager entityManager;

    public void cadastrarEndereco(Enderecos enderecos) {
        validarEndereco(enderecos);
        EntityManager enti = HibernateUtil.getEntityManager();
        try {
            enti.getTransaction().begin();
            enti.persist(enderecos);
            enti.getTransaction().commit();
        }catch(PersistenceException e) {
            enti.getTransaction().rollback();
            throw new RuntimeException("Erro ao cadastrar endereço");
        }
    }
    private void validarEndereco(Enderecos enderecos) {
        Optional.ofNullable(enderecos.getEstado()).filter(estado -> ! estado.isEmpty())
                .orElseThrow(()-> new IllegalArgumentException("O Estado do endereço é obrigatorio"));
        Optional.ofNullable(enderecos.getCidade()).filter(cidade -> ! cidade.isEmpty())
                .orElseThrow(()-> new IllegalArgumentException("A cidade do endereço é obrigatória"));
        Optional.ofNullable(enderecos.getCep()).filter(cep -> cep != cep)
                .orElseThrow(()-> new IllegalArgumentException("O cep doo endereço é obrigatório"));
        Optional.ofNullable(enderecos.getLogradouro()).filter(logradouro -> ! logradouro.isEmpty())
                .orElseThrow(()-> new IllegalArgumentException("O logradouro do endereço é obrigatório"));
    }


    public Enderecos buscarEndereco(Long id) {
        try {
            return entityManager.find(Enderecos.class, id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Endereço não encontrado com o Id fornecido");
        }
    }


    public List<Enderecos> listarEndereco(){
        return entityManager.createQuery("SELEC FROM ENDERECO e", Enderecos.class).getResultList();
    }


    public void atualizaEndereco(Enderecos enderecos) {
        validarEndereco(enderecos);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(enderecos);
            entityManager.getTransaction().commit();
        }catch(PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Erro ao cadastrar endereço"+ e.getMessage());
        }
    }

    public void excluirEndereco(Long id) {
        Enderecos enderecos = buscarEndereco(id);

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(enderecos);
            entityManager.getTransaction().commit();
        }catch(PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Erro ao cadastrar endereço" + e.getMessage());
        }

    }
}
