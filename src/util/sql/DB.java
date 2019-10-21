/**
 * Copyright(C),2018-2019,贵龙科技
 * FileName: DB
 * Author:   向贵龙
 * Date:     2019/5/14 16:55
 * Description: 数据库通道
 * History:
 * <author>     <time>      <version>       <desc>
 * 作者姓名     修改时间     版本号          描述
 **/
package util.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Mysql数据库操作的公共类
 * @author 向贵龙
 * @date 2019/4/1
 */
public class DB {
    private Connection conn = null;
    private Statement st = null;
    private ResultSet rs = null;
    private String MysqlName=null;//Mysql用户名
    private String MysqlPassword=null;//Mysql密码
    private String MysqlDatabase=null;//需要链接的数据库
    /**
     * 构造方法，调用此方法需要设置MySQL用户名（setMysqlName），密码（setMysqlPassword）以及数据库名（setMysqlDatabase）。
     */
    public DB() {
        super();
    }
    /**
     * 设置MySQL用户名
     * @param mysqlName
     */
    public void setMysqlName(String mysqlName) {
        MysqlName = mysqlName;
        if (MysqlName!=null&&MysqlPassword!=null&&MysqlDatabase!=null) {
            if (!getConnectionLow()) {
                getConnectionHigh();
            }
        }
    }
    /**
     * 设置MySQL密码
     * @param mysqlPassword
     */
    public void setMysqlPassword(String mysqlPassword) {
        MysqlPassword = mysqlPassword;
        if (MysqlName!=null&&MysqlPassword!=null&&MysqlDatabase!=null) {
            if (!getConnectionLow()) {
                getConnectionHigh();
            }
        }
    }
    /**
     * 设置MySQL数据库名
     * @param mysqlDatabase
     */
    public void setMysqlDatabase(String mysqlDatabase) {
        MysqlDatabase = mysqlDatabase;
        if (MysqlName!=null&&MysqlPassword!=null&&MysqlDatabase!=null) {
            if (!getConnectionLow()) {
                getConnectionHigh();
            }
        }
    }
    /**
     * 构造方法
     * @param mysqlName Mysql数据库名
     * @param mysqlPassword Mysql密码
     * @param mysqlDatabase Mysql数据库名
     */
    public DB(String mysqlName, String mysqlPassword, String mysqlDatabase) {
        super();
        MysqlName = mysqlName;
        MysqlPassword = mysqlPassword;
        MysqlDatabase = mysqlDatabase;
        if (MysqlName!=null&&MysqlPassword!=null&&MysqlDatabase!=null) {
            if (!getConnectionLow()) {
                getConnectionHigh();
            }
        }else {
            System.out.println("调用构造方法传递参数出错");
        }

    }
    /**
     * Mysql8.0以下链接方法
     */
    private boolean getConnectionLow() {
        final String DRIVERNAME = "com.mysql.jdbc.Driver";
        final String URL =
                "jdbc:mysql://localhost/"+MysqlDatabase+"?characterEncoding=UTF-8";
        final String LOGIN = MysqlName;
        final String PASSWORD = MysqlPassword;
        try {
            Class.forName(DRIVERNAME);
            conn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("链接成功");
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            //System.out.println("执行getConnectionLow（）方法出错");
            //System.out.println("Mysql版本非8.0以下版本或者链接失败，"
            //	+ "如果MySQL版本低于8.0，请检查你的你的用户名和密码以及数据库是否存在");
            return false;
        }
    }
    /**
     * Mysql8.0以上链接方法
     */
    private boolean getConnectionHigh() {
        final String DRIVERNAME = "com.mysql.cj.jdbc.Driver";
        final String URL =
                "jdbc:mysql://localhost:3306/"+MysqlDatabase+"?useUnicode=true&characterEncoding=UTF-8"
                        + "&useSSL=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=CONVERT_TO_NULL";
        final String LOGIN =MysqlName;
        final String PASSWORD = MysqlPassword;
        try {
            Class.forName(DRIVERNAME);
            conn=DriverManager.getConnection(URL, LOGIN, PASSWORD);
            //System.out.println("链接成功");
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("执行getConnectionDown（）方法出错");
            e.printStackTrace();
            return false;
        }

    }
    /**
     * 数据库查询
     * @param sql
     * @return
     */
    public ResultSet executeQuery(String sql) {
        try {
            if (conn == null) {
                System.out.println("调用构造方法错误");
            }
            if (st == null) {
                st = conn.createStatement();
            }
            rs = st.executeQuery(sql);
        }catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("执行executeQuery(String sql)方法错误："+sql);
            e.printStackTrace();
        }
        return rs;
    }
    /**
     * 数据库增加/删除/修改
     * @param sql
     * @return >0 执行成功
     */
    public int executeUpdate(String sql) {
        int ret;
        try {
            if (conn == null) {
                System.out.println("调用构造方法错误");
            }
            if (st == null) {
                st = conn.createStatement();
            }
            ret = st.executeUpdate(sql);
        }catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("执行executeQuery(String sql)方法错误："+sql);
            ret = -1;
        }
        return ret;
    }

    /**
     * 数据库关闭
     */
    public void close() {

        try {
            if (rs!=null) {
                rs.close();
            }
            if (st!=null) {
                st.close();
            }
            if (conn!=null) {
                conn.close();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("执行close方法错误");
            e.printStackTrace();
        }
    }
}