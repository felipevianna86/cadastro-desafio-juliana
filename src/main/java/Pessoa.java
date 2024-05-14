import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Pessoa")
public class Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "idade", nullable = false)
    private LocalDate idade;

    @Column(name = "sexo", nullable = false, length = 2)
    private String sexo;

    @Column(name = "CPF", nullable = false, length = 11)
    private Integer cpf;
    private List<Enderecos> enderecos = new ArrayList<Enderecos>();


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public LocalDate getIdade() {
        return idade;
    }
    public void setIdade(LocalDate idade) {
        this.idade = idade;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public Integer getCpf() {
        return cpf;
    }
    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }
    public List<Enderecos> getEnderecos() {
        return enderecos;
    }
    public void setEnderecos(List<Enderecos> enderecos) {
        this.enderecos = enderecos;
    }
    public Pessoa(Long id, String nome, LocalDate idade, String sexo, Integer cpf, List<Enderecos> enderecos) {
        super();
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.enderecos = enderecos;
    }
    public Pessoa(Long id, String nome, LocalDate idade, String sexo, Integer cpf) {
        super();
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.cpf = cpf;

    }


}