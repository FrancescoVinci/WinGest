package db;

import javafx.util.Pair;
import model.Ricavi;
import model.RiepilogoRicavi;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RicaviDAO {

    public void setRicavi(String parrocchiaid, String date, String nome, String descrizione, double valorebanca, double valorecassa){
        String query = "INSERT INTO RICAVI (PARROCCHIAID, DATA ,NOME, DESCRIZIONE, VALOREBANCA, VALORECASSA) VALUES ( ?,?,?,?,?,? )";
        try{
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, parrocchiaid);
            st.setString(2, date);
            st.setString(3,nome);
            st.setString(4,descrizione);
            st.setDouble(5,valorebanca);
            st.setDouble(6,valorecassa);
            st.executeUpdate();
            conn.close();
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    public List<Ricavi> getRicaviPerParrocchie(String parrocchiaid){
        String query = "SELECT * FROM RICAVI WHERE PARROCCHIAID = ?";
        List<Ricavi> result = new ArrayList<>();
        try{
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, parrocchiaid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Ricavi c = new Ricavi(rs.getString("parrocchiaid"), rs.getInt("ricavoid"), rs.getString("data"), rs.getString("nome"), rs.getString("descrizione"), rs.getDouble("valorebanca"), rs.getDouble("valorecassa"));
                result.add(c);
            }

            conn.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Errore nella ricerca causato da un problema interno (getRicaviPerParrocchie)", "Error data base", JOptionPane.INFORMATION_MESSAGE);
            throw new RuntimeException(ex);
        }
        return result;
    }

    public List<Ricavi> getRicavi() {
        String query = "SELECT * FROM RICAVI";
        List<Ricavi> result = new ArrayList<Ricavi>();
        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Ricavi c = new Ricavi(rs.getString("parrocchiaid"), rs.getInt("ricavoid"), rs.getString("data"), rs.getString("nome"), rs.getString("descrizione"), rs.getDouble("valorebanca"), rs.getDouble("valorecassa"));
                result.add(c);
            }

            conn.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return result;
    }

    public List<RiepilogoRicavi> getRiepilogoRicaviPerParrocchie(String parrocchiaid){
        String query = "SELECT PARROCCHIAID, NOME, SUM(VALOREBANCA) AS VALOREBANCA, SUM(VALORECASSA) AS VALORECASSA FROM RICAVI WHERE PARROCCHIAID = ? GROUP BY NOME";
        List<RiepilogoRicavi> result = new ArrayList<>();
        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, parrocchiaid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                RiepilogoRicavi c = new RiepilogoRicavi(rs.getString("parrocchiaid"), rs.getString("nome"), rs.getDouble("valorebanca"), rs.getDouble("valorecassa"));
                result.add(c);
            }

            conn.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return result;
    }

    public void deletetRicavi(int id){
        String query = "DELETE FROM RICAVI WHERE RICAVOID = ? ";
        try{
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id);
            st.executeUpdate();
            conn.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Errore nella rimozione causato da un problema interno (deleteRicavi)", "Error data base", JOptionPane.INFORMATION_MESSAGE);
            throw new RuntimeException(ex);
        }
    }

    public void deletetRicaviPerParrocchiaId(String id){
        String query = "DELETE FROM RICAVI WHERE PARROCCHIAID = ? ";
        try{
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, id);
            st.executeUpdate();
            conn.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Errore nella rimozione causato da un problema interno (deletetRicaviPerParrocchiaID)", "Error data base", JOptionPane.INFORMATION_MESSAGE);
            throw new RuntimeException(ex);
        }
    }

    public List<Ricavi> getSelectedIformationRicavi(String parrocchiaid, String name){
        String query = "SELECT * FROM RICAVI WHERE NOME = ? AND PARROCCHIAID = ?";
        List<Ricavi> result = new ArrayList<>();
        try{
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, name);
            st.setString(2,parrocchiaid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Ricavi c = new Ricavi(rs.getString("parrocchiaid"), rs.getInt("ricavoid"), rs.getString("data"), rs.getString("nome"), rs.getString("descrizione"), rs.getDouble("valorebanca"), rs.getDouble("valorecassa"));
                result.add(c);
            }

            conn.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Errore nella ricerca causato da un problema interno (getSelectedIformationRicavi)", "Error data base", JOptionPane.INFORMATION_MESSAGE);
            throw new RuntimeException(ex);
        }
        return result;
    }
}
