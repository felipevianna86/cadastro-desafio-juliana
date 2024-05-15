import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;



public class ConexaoUtil {

    private static ResourceBundle config;

    private ConexaoUtil() {
        config = ResourceBundle.getBundle("config");
    }


    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static ConexaoUtil conexaoUtil;
    public static ConexaoUtil getInstance(){
        if(conexaoUtil == null){
            conexaoUtil = new ConexaoUtil();
        }
        return conexaoUtil;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName(config.getString("br.com.julianeandradepostgresql.Driver"));
        return DriverManager.getConnection(config.getString("br.com.julianeandrade.url.conexao"),
                config.getString("br.edu.devmedia.bd.usuario"), config.getString("br.edu.devmedia.bd.senha"));
    }



}