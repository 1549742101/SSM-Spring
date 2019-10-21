/**
 * Copyright(C),2018-2019,贵龙科技
 * FileName: DataBase
 * Author:   向贵龙
 * Date:     2019/5/14 16:57
 * Description: 数据库操作类
 * History:
 * <author>     <time>      <version>       <desc>
 * 作者姓名     修改时间     版本号          描述
 **/
package util.sql;

public class DataBase extends DB{
    private String MysqlName="root";//Mysql用户名
    private String MysqlPassword="xgl020331";//Mysql密码
    private String MysqlDatabase="testdb";//需要链接的数据库
    public DataBase() {
        super();
        setMysqlDatabase(MysqlDatabase);
        setMysqlName(MysqlName);
        setMysqlPassword(MysqlPassword);
        // TODO 自动生成的构造函数存根
    }

}
