package repository;

import domain.Loc;
import domain.Rezervare;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;

public class RepositoryRezervareBD extends Repository<Rezervare>{
    private Connection conn = null;

    private final String dbLocation;

    public RepositoryRezervareBD(String dbLocation){
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
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Rezervare(id String , locuri varchar(200));" );
            }
        }catch (SQLException e){
            System.err.println("[ERROR] createSchema : "+ e.getMessage());
        }
    }
    private void loadData(){
        try{
            try(PreparedStatement statement = conn.prepareStatement("SELECT * FROM Rezervare"); ResultSet rs =statement.executeQuery();) {
                while (rs.next()){
                    String s = rs.getString("locuri");
                    ArrayList<Loc> locuri = new ArrayList<>();
                    String[] tokens = s.split(";");
                    for(String t:tokens){
                        String[] loc = t.split(",");
                        Loc l = new Loc(loc[0],Double.parseDouble(loc[1]),Boolean.parseBoolean(loc[2]));
                        locuri.add(l);
                    }
                    Rezervare r = new Rezervare(rs.getString("id"),locuri);
                    this.elems.add(r);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addElem(Rezervare c){
        super.addElem(c);
        String s="";
        try{
            try(PreparedStatement stmt=conn.prepareStatement("INSERT INTO Rezervare VALUES (?,?)")){
                stmt.setString(1,c.getId());
                ArrayList<Loc> locuri = c.getLocuri();
                for(Loc l:locuri){
                    s+=l.getId()+","+l.getPret()+","+l.getStare()+";";
                }
                stmt.setString(2,s);
                stmt.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void removeElem(String id){
        super.removeElem(id);
        try {
            try(PreparedStatement stmt=conn.prepareStatement("DELETE FROM Rezervare WHERE id=?")){
                stmt.setString(1,id);
                stmt.executeUpdate();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateElem(Rezervare r1,Rezervare r2){
        super.updateElem(r1,r2);
        removeElem(r1.getId());
        addElem(r2);
    }

}
