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
    public void insertChampion(Champion champion){
        String sql = "INSERT INTO champion (name,role) VALUES (?,?)";

        if (existChampion(champion.getName())){
            return;
        }else {
            try{
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, champion.getName());
                preparedStatement.setString(2, champion.role);

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
    public void insertCounter(Champion champion){
        String sql = "INSERT INTO counter (name,counter1,counter2,counter3) VALUES (?,?,?,?)";

        if (existCounter(champion.name)){
            return;
        }else {
            try{
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, champion.name);
                preparedStatement.setString(2, champion.counter1);
                preparedStatement.setString(3, champion.counter2);
                preparedStatement.setString(4, champion.counter3);

                //Esegui Query
                int rowsInserted = preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.out.println("error inserting Counter");
                System.out.println(e.getMessage());
            }
        }
    }
    public void insertBuild(Champion champion){
        String sql1 = "INSERT INTO stats (name,winrate,pickrate,banrate,tier) VALUES (?,?,?,?,?)";
        String sql2 = "INSERT INTO build (name,path,keystone,slot1,slot2,slot3,secondarypath,secondary1,secondary2,shard1,shard2,shard3,summoner1,summoner2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        if (existBuild(champion.name)){}else{
            try{
                PreparedStatement preparedStatement = conn.prepareStatement(sql2);
                preparedStatement.setString(1, champion.name);
                preparedStatement.setString(2, champion.build.path);
                preparedStatement.setString(3, champion.build.keystone);
                preparedStatement.setString(4, champion.build.slot1);
                preparedStatement.setString(5, champion.build.slot2);
                preparedStatement.setString(6, champion.build.slot3);
                preparedStatement.setString(7, champion.build.secondarypath);
                preparedStatement.setString(8, champion.build.secondary1);
                preparedStatement.setString(9, champion.build.secondary2);
                preparedStatement.setString(10, champion.build.shard1);
                preparedStatement.setString(11, champion.build.shard2);
                preparedStatement.setString(12, champion.build.shard3);
                preparedStatement.setString(13, champion.build.summoner1);
                preparedStatement.setString(14, champion.build.summoner2);

                //Esegui Query
                int rowsInserted = preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.out.println("error inserting Build");
                System.out.println(e.getMessage());
            }
        }
        if (existStats(champion.name)){}else{
            try{
                PreparedStatement preparedStatement = conn.prepareStatement(sql1);
                preparedStatement.setString(1, champion.name);
                preparedStatement.setBigDecimal(2, champion.stats.winrate);
                preparedStatement.setBigDecimal(3, champion.stats.pickrate);
                preparedStatement.setBigDecimal(4, champion.stats.banrate);
                preparedStatement.setString(5, champion.stats.tier);

                //Esegui Query
                int rowsInserted = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("error inserting Stats");
                System.out.println(e.getMessage());
            }
        }





        //name,path,keystone,slot1,slot2,slot3,secondarypath,secondary1,secondary2,shard1,shard2,shard3,summoner1,summoner2


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
    private boolean existCounter(String name){
        if(name.contains("'")){
            name = name.replace("'","''");
        }
        String query = "select * " + " from counter" + " where name = '" + name + "'";

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
    private boolean existStats(String name){
        if(name.contains("'")){
            name = name.replace("'","''");
        }
        String query = "select * " + " from stats" + " where name = '" + name + "'";

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
    private boolean existBuild(String name){
        if(name.contains("'")){
            name = name.replace("'","''");
        }
        String query = "select * " + " from build" + " where name = '" + name + "'";

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

    public String getBuild(String name){
        StringBuilder responce = new StringBuilder();
        if(name.contains("'")){
            name = name.replace("'","''");
        }
        String query = "select * " + " from build" + " where name = '" + name + "'";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                responce.append("Rune Path :");
                responce.append(rs.getString("path"));
                responce.append("\nKeyStone :");
                responce.append(rs.getString("keystone"));
                responce.append("\nslot1 :");
                responce.append(rs.getString("slot1"));
                responce.append("\nslot2 :");
                responce.append(rs.getString("slot2"));
                responce.append("\nslot3 :");
                responce.append(rs.getString("slot3"));
                responce.append("\nSecondary Path :");
                responce.append(rs.getString("secondarypath"));
                responce.append("\nSecondary Slot1 :");
                responce.append(rs.getString("secondary1"));
                responce.append("\nSecondary Slot2 :");
                responce.append(rs.getString("secondary2"));

                responce.append("\nShard 1 : ");
                responce.append(rs.getString("shard1"));
                responce.append("\nShard 2 :");
                responce.append(rs.getString("shard2"));
                responce.append("\nShard 3 :");
                responce.append(rs.getString("shard3"));
                responce.append("\n");
                responce.append("\nSummoner 1 :");
                responce.append(rs.getString("summoner1"));
                responce.append("\nSummoner 2 :");
                responce.append(rs.getString("summoner2"));
            }


        }catch (SQLException e){
            System.out.println("Errore Query database, select ");
            System.out.println(e.getMessage());
        }
        return responce.toString();
    }
    public String getCounter(String name){
        StringBuilder responce = new StringBuilder();
        if(name.contains("'")){
            name = name.replace("'","''");
        }
        String query = "select * " + " from counter" + " where name = '" + name + "'";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                responce.append("Counter :");
                responce.append("\n-");
                responce.append(rs.getString("counter1"));
                responce.append("\n-");
                responce.append(rs.getString("counter2"));
                responce.append("\n-");
                responce.append(rs.getString("counter3"));
            }



        }catch (SQLException e){
            System.out.println("Errore Query database, select ");
            System.out.println(e.getMessage());
        }
        return responce.toString();

    }
    public String getStats(String name){
        StringBuilder responce = new StringBuilder();
        if(name.contains("'")){
            name = name.replace("'","''");
        }
        String query = "select * " + " from stats" + " where name = '" + name + "'";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                responce.append("Stats :");
                responce.append("\n-Tier :");
                responce.append(rs.getString("tier"));
                responce.append("\n-Win Rate :");
                responce.append(rs.getString("winrate"));
                responce.append("%\n-Pick Rate :");
                responce.append(rs.getString("pickrate"));
                responce.append("%\n-Ban Rate :");
                responce.append(rs.getString("banrate"));
                responce.append("%");
            }



        }catch (SQLException e){
            System.out.println("Errore Query database, select ");
            System.out.println(e.getMessage());
        }
        return responce.toString();
    }
    public String getTop(String name){
        StringBuilder responce = new StringBuilder();
        //select * from stats INNER JOIN champion ON stats.name = champion.name WHERE champion.role = "Top" ORDER BY stats.tier DESC LIMIT 5
        String query = "select * from stats INNER JOIN champion ON stats.name = champion.name where champion.role = \"" + name + "\" ORDER BY stats.tier DESC Limit 5" ;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            responce.append("Best ");
            responce.append(name);
            responce.append(" : ");
            while(rs.next()){
                responce.append("\n-");
                responce.append(rs.getString("name"));
            }
        }catch (SQLException e){
            System.out.println("Errore Query database, select ");
            System.out.println(e.getMessage());
        }
        return responce.toString();
    }
    public String getAllChampions(){
        StringBuilder responce = new StringBuilder();
        String query = "select * " + " from champion" ;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            responce.append("Champion List:");
            while(rs.next()){
                responce.append("\n-");
                responce.append(rs.getString("name"));
            }
        }catch (SQLException e){
            System.out.println("Errore Query database, select ");
            System.out.println(e.getMessage());
        }
        return responce.toString();
    }
}
