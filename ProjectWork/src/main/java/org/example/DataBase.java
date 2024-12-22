package org.example;

import java.sql.*;

public class DataBase {

    String jdbcUrl;
    String username;
    String password;
    Connection conn;
    public DataBase() {
        this.jdbcUrl = "jdbc:mysql://localhost:3306/velkozbot";  // Replace 'my_database' with your actual database name
        this.username = "root";  // Root user
        this.password = "";
        try{
            this.conn = DriverManager.getConnection(jdbcUrl, username , password );
        }catch (SQLException e){
            System.out.println("Errore Connession Database");
            System.out.println(e.getMessage());
        }

    }
    public DataBase(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        try{
            this.conn = DriverManager.getConnection(jdbcUrl, username , password );
        }catch (SQLException e){
            System.out.println("Errore Connession Database");
            System.out.println(e.getMessage());
        }
    }


    //Inserisce nelle tabelle
    public void insertChampion(String name){
        String sql = "INSERT INTO champion (name) VALUES (?)";

        if (existChampion(name)){
            return;
        }else {
            try{
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, name);

                //Esegui Query
                int rowsInserted = preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.out.println("error inserting champion");
                System.out.println(e.getMessage());
            }
        }
    }
    public void insertRune(String name, String position){
        String sql = "INSERT INTO rune (name, posizione) VALUES (?,?)";

        if (existRune(name)){
            return;
        }else {
            try{
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, position);

                //Esegui Query
                int rowsInserted = preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.out.println("error inserting champion");
                System.out.println(e.getMessage());
            }
        }
    }
    public void insertSummoner(String name){
        String sql = "INSERT INTO summonerspell (name) VALUES (?)";

        if (existSummoner(name)){
            return;
        }else {
            try{
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, name);

                //Esegui Query
                int rowsInserted = preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.out.println("error inserting champion");
                System.out.println(e.getMessage());
            }
        }
    }

    //Controlla se il contenuti sono già presenti.
    private boolean existChampion(String champion){
        if(champion.contains("'")){
            champion = champion.replace("'","''");
        }
        String query = "select * " + " from champion" + " where name = '" + champion + "'";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch (SQLException e){
            System.out.println("Errore Query database, select ");
            System.out.println(e.getMessage());
            return false;
        }
    }
    private boolean existRune(String name){
        if(name.contains("'")){
            name = name.replace("'","''");
        }
        String query = "select * " + " from rune" + " where name = '" + name + "'";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch (SQLException e){
            System.out.println("Errore Query database, select ");
            System.out.println(e.getMessage());
            return false;
        }
    }
    private boolean existSummoner(String name){
        if(name.contains("'")){
            name = name.replace("'","''");
        }
        String query = "select * " + " from summonerspell" + " where name = '" + name + "'";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch (SQLException e){
            System.out.println("Errore Query database, select ");
            System.out.println(e.getMessage());
            return false;
        }
    }


}
