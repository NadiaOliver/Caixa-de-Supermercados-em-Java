package DAO;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author nadia
 */

public class ConectaBanco {
    
    public static Connection conectabanco() throws ClassNotFoundException{
        
        try{
           Class.forName("org.postgresql.Driver");
           Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MercadosNLBD","postgres", "Kerb1000");
          // JOptionPane.showMessageDialog(null,"Conectado com sucesso ao banco de dados!");
           return con;
        }
        
        catch(SQLException error){
            JOptionPane.showMessageDialog(null,error);
            return null;
        }
    }
}
