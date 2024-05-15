
import javax.persistence.*;
import java.io.Serializable;

@Entity
 @Table(name = "endereco")
 public class Enderecos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Estado", nullable = false, length = 2)
    private String estado;

    @Column(name = "Cidade", nullable = false, length = 100)
    private String cidade;

    @Column(name = "Logradouro", nullable = false, length = 100)
    private String Logradouro;

    @Column(name = "Numero", nullable = false)
    private Integer numero;

    @Column(name = "CEP", nullable = false, length = 8)

    private Long cep;

    @ManyToOne
    @JoinColumn(name= "id_pessoa")
    private Pessoa pessoa;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getLogradouro() {
        return Logradouro;
    }
    public void setLogradouro(String logradouro) {
        Logradouro = logradouro;
    }
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public Long getCep() {
        return cep;
    }
    public void setCep(Long cep) {
        this.cep = cep;
    }
    public Pessoa getPessoa() {
        return pessoa;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    public Enderecos(Long id, String estado, String cidade, String logradouro, Integer numero, Long cep, Pessoa pessoa) {
        super();
        this.id = id;
        this.estado = estado;
        this.cidade = cidade;
        Logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
        this.pessoa = pessoa;
    }


 }

