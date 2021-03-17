package db;

import javafx.util.Pair;
import model.Costi;
import model.Ricavi;
import model.RiepilogoCosti;

//import java.sql.*;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CostiDAO {


    public void setCosti(String parrocchiaid, String date, String nome, String descrizione, double valorebanca, double valorecassa){
        String query = "INSERT INTO COSTI (PARROCCHIAID, DATA ,NOME, DESCRIZIONE, VALOREBANCA, VALORECASSA) VALUES ( ?,?,?,?,?,? )";
        try{
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1,parrocchiaid);
            st.setString(2, date);
            st.setString(3,nome);
            st.setString(4,descrizione);
            st.setDouble(5,valorebanca);
            st.setDouble(6,valorecassa);
            st.executeUpdate();
            conn.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Errore nell'inserimento causato da un problema interno", "Error data base", JOptionPane.INFORMATION_MESSAGE);
            throw new RuntimeException(ex);
        }
    }

    public List<Costi> getCostiPerParrocchia(String parrocchiaid){
        String query = "SELECT * FROM COSTI WHERE PARROCCHIAID = ?";
        List<Costi> result = new ArrayList<>();
        try{
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, parrocchiaid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Costi c = new Costi(rs.getString("parrocchiaid"), rs.getInt("costoid"), rs.getString("data"), rs.getString("nome"), rs.getString("descrizione"), rs.getDouble("valorebanca"), rs.getDouble("valorecassa"));
                result.add(c);
            }
            conn.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Errore nella ricerca causato da un problema interno", "Error data base", JOptionPane.INFORMATION_MESSAGE);
            throw new RuntimeException(ex);
        }
        return result;
    }

    public List<Costi> getCosti() {
        String query = "SELECT * FROM COSTI";
        List<Costi> result = new ArrayList<Costi>();
        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Costi c = new Costi(rs.getString("parrocchiaid"), rs.getInt("costoid"), rs.getString("data"), rs.getString("nome"), rs.getString("descrizione"), rs.getDouble("valorebanca"), rs.getDouble("valorecassa"));
                result.add(c);
            }
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore nella query causato da un problema interno", "Error data base", JOptionPane.INFORMATION_MESSAGE);
            throw new RuntimeException(ex);
        }

        return result;
    }

    public List<RiepilogoCosti> getRiepilogoCostiPerParrocchie(String parrocchiaid){
        String query = "SELECT PARROCCHIAID, NOME, SUM(VALOREBANCA) AS VALOREBANCA, SUM(VALORECASSA) AS VALORECASSA FROM COSTI WHERE PARROCCHIAID = ? GROUP BY NOME";
        List<RiepilogoCosti> result = new ArrayList<>();
        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1,parrocchiaid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                RiepilogoCosti c = new RiepilogoCosti(rs.getString("parrocchiaid"), rs.getString("nome"), rs.getDouble("valorebanca"), rs.getDouble("valorecassa"));
                result.add(c);
            }
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore nella query causato da un problema interno", "Error data base", JOptionPane.INFORMATION_MESSAGE);
            throw new RuntimeException(ex);
        }

        return result;
    }

    public void deletetCosti(int id){
        String query = "DELETE FROM COSTI WHERE COSTOID = ? ";
        try{
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id);
            st.executeUpdate();
            conn.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Errore nella rimozione causato da un problema interno", "Error data base", JOptionPane.INFORMATION_MESSAGE);
            throw new RuntimeException(ex);
        }
    }

    public void deletetCostiPerParrocchiaId(String id){
        String query = "DELETE FROM COSTI WHERE PARROCCHIAID = ? ";
        try{
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, id);
            st.executeUpdate();
            conn.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Errore nella rimozione causato da un problema interno (deletetCostiPerParrocchiaID)", "Error data base", JOptionPane.INFORMATION_MESSAGE);
            throw new RuntimeException(ex);
        }
    }

    public List<Costi> getSelectedIformationCosti(String parrocchiaid, String name){
        String query = "SELECT * FROM COSTI WHERE NOME = ? AND PARROCCHIAID = ?";
        List<Costi> result = new ArrayList<>();
        try{
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, name);
            st.setString(2,parrocchiaid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Costi c = new Costi(rs.getString("parrocchiaid"), rs.getInt("costoid"), rs.getString("data"), rs.getString("nome"), rs.getString("descrizione"), rs.getDouble("valorebanca"), rs.getDouble("valorecassa"));
                result.add(c);
            }
            conn.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Errore nella ricerca causato da un problema interno", "Error data base", JOptionPane.INFORMATION_MESSAGE);
            throw new RuntimeException(ex);
        }
        return result;
    }

}
