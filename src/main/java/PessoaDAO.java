import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.PersistenceException;

@Entity
public class PessoaDAO extends PessoaService{

    private Connection connection;
    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public void cadastrarEndereco(Pessoa pessoa) throws PersistenceException {
        try {
            String sql = "INSERT INTO TB_PESSOA(ID, NOME, IDADE, SEXO, CPF, ENDERECO) " +
                    "VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement = connection.prepareStatement(sql);
            statement.setLong(1, pessoa.getId());
            statement.setString(2, pessoa.getNome());
            statement.setDate(3, Date.valueOf(pessoa.getIdade()));
            statement.setString(4, String.valueOf(pessoa.getSexo()));
            statement.setInt(5, pessoa.getCpf());
            statement.setString(6, pessoa.getEnderecos().toString());

            statement.execute();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e.getMessage(), e);
        }
    }

    public void atualizar(Pessoa pessoa) throws PersistenceException {
        try {
            String sql = "UPDATE TB_PESSOA " +
                    " ID = ?," +
                    " SET NOME = ?, " +
                    " IDADE = ?," +
                    " SEXO = ?," +
                    " CPF = ?," +
                    " ENDERECO = ? " +
                    " WHERE ID_PESSOA = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, pessoa.getId());
            statement.setString(2, pessoa.getNome());
            statement.setDate(3, Date.valueOf(pessoa.getIdade()));
            statement.setString(4, String.valueOf(pessoa.getSexo()));
            statement.setInt(5, pessoa.getCpf());
            statement.setString(6, pessoa.getEnderecos().toString());


            statement.execute();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e.getMessage(), e);
        }
    }

    public void deletar(Long idPessoa) throws PersistenceException {
        try {

            String sql = "DELETE FROM TB_PESSOA WHERE ID_PESSOA = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Math.toIntExact(idPessoa));

            statement.execute();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e.getMessage(), e);
        }
    }

    public List<Pessoa> listarPessoa() throws PersistenceException {
        List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "SELECT * FROM TB_PESSOA";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(resultSet.getLong("id_pessoa"));
                pessoa.setNome(resultSet.getString("nome"));
                pessoa.setCpf(Math.toIntExact(resultSet.getLong("cpf")));
                pessoa.setIdade(resultSet.getDate("idade").toLocalDate());
                pessoa.setEnderecos((List<Enderecos>) resultSet.getArray("endereco"));
                pessoa.setSexo(String.valueOf(resultSet.getString("sexo").charAt(2)));

                listaPessoas.add(pessoa);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e.getMessage(), e);
        }
        return listaPessoas;
    }

    public Pessoa buscarPorId(Integer idPessoa) throws PersistenceException {
        try {
            String sql = "SELECT * FROM TB_PESSOA WHERE ID_PESSOA = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, 1);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(resultSet.getLong("id_pessoa"));
                pessoa.setNome(resultSet.getString("nome"));
                pessoa.setCpf(Math.toIntExact(resultSet.getLong("cpf")));
                pessoa.setIdade(resultSet.getDate("idade").toLocalDate());
                pessoa.setEnderecos((List<Enderecos>) resultSet.getArray("endereco"));
                pessoa.setSexo(String.valueOf(resultSet.getString("sexo").charAt(2)));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e.getMessage(), e);
        }
        return null;
    }
}