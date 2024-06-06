package repository;

import domain.Loc;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;

public class RepositoryLocBD extends Repository<Loc>{
    private Connection conn = null;
    private final String dbLocation;

    public RepositoryLocBD(String dbLocation) {
        this.dbLocation = "jdbc:sqlite:" + dbLocation;
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
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Loc(rand_numar varchar(50), pret double, stare boolean);" );
            }
        }catch (SQLException e){
            System.err.println("[ERROR] createSchema : "+ e.getMessage());
        }
    }

    private void loadData(){
        try{
            try(PreparedStatement statement = conn.prepareStatement("SELECT * FROM Loc"); ResultSet rs =statement.executeQuery();) {
                while (rs.next()){
                    Loc t = new Loc(rs.getString("rand_numar"),rs.getDouble("pret"),rs.getBoolean("stare"));
                    this.elems.add(t);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void addElem(Loc t)
    {
        super.addElem(t);
        try{
            try(PreparedStatement stmt=conn.prepareStatement("INSERT INTO Loc VALUES (?,?,?)")){
                stmt.setString(1,t.getId());
                stmt.setDouble(2,t.getPret());
                stmt.setBoolean(3,t.getStare());
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
            try(PreparedStatement stmt=conn.prepareStatement("DELETE FROM Loc WHERE rand_numar=?")){
                stmt.setString(1,id);
                stmt.executeUpdate();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Loc> getAll()
    {
        ArrayList<Loc> locuri = new ArrayList<>();
        try {
            try(PreparedStatement stmt=conn.prepareStatement("SELECT * FROM Loc");ResultSet rs =stmt.executeQuery();) {
                while (rs.next()){
                    Loc t = new Loc(rs.getString("rand_numar"),rs.getDouble("pret"), rs.getBoolean("stare"));
                    locuri.add(t);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return locuri;
    }
    public void updateElem(Loc t1 , Loc t2)
    {
        super.updateElem(t1,t2);
        removeElem(t1.getId());
        addElem(t2);
    }
}
