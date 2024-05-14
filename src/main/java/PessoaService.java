import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import jakarta.persistence.PersistenceException;
import net.bytebuddy.asm.Advice.Enter;

import static java.util.Optional.*;

public class PessoaService {
    @PersistenceContext

    private EntityManager entityManager;

    public void cadastrarPessoa(Pessoa pessoa) {
        validarPessoa(pessoa);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(pessoa);
            entityManager.getTransaction().commit();
        }catch(PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Erro ao cadastrar cpf" + e.getMessage());
        }
    }

    private void validarPessoa(Pessoa pessoa) {
        Optional.ofNullable(pessoa.getCpf())
                .orElseThrow(() -> new IllegalArgumentException("O cpf é obrigatório"));
        Optional.ofNullable(pessoa.getNome()).filter(nome -> ! nome.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("O nome é obrigatório"));
    }

    public Pessoa buscarPessoa(Long id) {
        try {
            return entityManager.find(Pessoa.class, id);
        }catch(NoResultException e) {
            throw new RuntimeException("Pessoa não encontrada");
        }
    }


    public void atualizarPessoa(Pessoa pessoa) {
        validarPessoa(pessoa);
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(pessoa);
            entityManager.getTransaction().commit();
        }catch (javax.persistence.PersistenceException e) {
            throw new RuntimeException("Erro ao atualizar" + e.getMessage());
        }
    }


    public List<Pessoa> listarPessoa(){
        return entityManager.createQuery("SELECT FROM Pessoa", Pessoa.class).getResultList();
    }


    public void excluirPessoa(Long id) {
        Pessoa pessoa = buscarPessoa(id);
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(pessoa);
            entityManager.getTransaction().commit();
        }catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Erro ao excluir pessoa" + e.getMessage());
        }
    }
}
