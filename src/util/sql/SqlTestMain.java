package util.sql;

import java.sql.ResultSet;

public class SqlTestMain {
    public static void main(String[] args) {
        DataBase db = new DataBase();
        ResultSet rs = db.executeQuery("select * from user");
        try {
            while (rs.next()){
                System.out.println(rs.getString("name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
