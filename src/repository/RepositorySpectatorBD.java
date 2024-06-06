
package repository;

import domain.Spectator;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;

public class RepositorySpectatorBD extends Repository<Spectator>{
    private Connection conn = null;
    private final String dbLocation;

    public RepositorySpectatorBD(String dbLocation) {
        this.dbLocation = "jdbc:sqlite:"+dbLocation;
        openConnection();
        createSchema();
        loadData();
    }

    private void openConnection(){
        try{
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(dbLocation);
            if(conn == null || conn.isClosed())
                conn=ds.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void closeConnection(){
        try{
            if(conn!=null)conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    void createSchema(){
        try {
            try(final Statement stmt = conn.createStatement()){
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Spectator(adresa varchar(50), nume varchar(50), prenume varchar(50), parola varchar(50), admin boolean);" );
            }
        }catch (SQLException e){
            System.err.println("[ERROR] createSchema : "+ e.getMessage());
        }
    }

    private void loadData(){
        try{
            try(PreparedStatement statement = conn.prepareStatement("SELECT * FROM Spectator");ResultSet rs =statement.executeQuery();) {
                while (rs.next()){
                    Spectator t = new Spectator(rs.getString("adresa"),rs.getString("nume"),rs.getString("prenume"), rs.getString("parola"), rs.getBoolean("admin"));
                    this.elems.add(t);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void addElem(Spectator t)
    {
        super.addElem(t);
        try{
            try(PreparedStatement stmt=conn.prepareStatement("INSERT INTO Spectator VALUES (?,?,?,?,?)")){
                stmt.setString(1,t.getId());
                stmt.setString(2,t.getNume());
                stmt.setString(3,t.getPrenume());
                stmt.setString(4,t.getParola());
                stmt.setBoolean(5,t.getAdmin());
                stmt.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void removeElem(String id)
    {
        super.removeElem(id);
        try {
            try(PreparedStatement stmt=conn.prepareStatement("DELETE FROM Spectator WHERE adresa=?")){
                stmt.setString(1,id);
                stmt.executeUpdate();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Spectator> getAll()
    {
        ArrayList<Spectator> spectatori = new ArrayList<>();
        try {
            try(PreparedStatement stmt=conn.prepareStatement("SELECT * FROM Spectator");ResultSet rs =stmt.executeQuery();) {
                while (rs.next()){
                    Spectator t = new Spectator(rs.getString("adresa"),rs.getString("nume"), rs.getString("prenume"), rs.getString("parola"), rs.getBoolean("admin"));
                    spectatori.add(t);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return spectatori;
    }
    public void updateElem(Spectator t1 , Spectator t2) {
        super.updateElem(t1, t2);
        removeElem(t1.getId());
        addElem(t2);
    }
}
