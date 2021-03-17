package db;

import model.Parrochie;
import model.Ricavi;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParrochieDAO {

    public void addParrochie(String nome){
        String query = "INSERT INTO PARROCCHIE (PARROCCHIAID) VALUES ( ? )";
        try{
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, nome);

            st.executeUpdate();
            conn.close();
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    public void deleteParrocchie(String nome){
        String query = "DELETE FROM PARROCCHIE WHERE PARROCCHIAID = ? ";
        try{
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, nome);
            st.executeUpdate();
            conn.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Errore nella rimozione causato da un problema interno (deleteParrochie)", "Error data base", JOptionPane.INFORMATION_MESSAGE);
            throw new RuntimeException(ex);
        }
    }

    public List<Parrochie> getParrochie(){
        String query = "SELECT * FROM PARROCCHIE";
        List<Parrochie> result = new ArrayList<Parrochie>();
        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Parrochie c = new Parrochie(rs.getString("parrocchiaid"));
                result.add(c);
            }

            conn.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return result;
    }

}
