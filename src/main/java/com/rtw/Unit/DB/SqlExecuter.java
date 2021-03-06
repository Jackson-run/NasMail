package com.rtw.Unit.DB;

import java.sql.*;

/**
 * @author wangrun
 * @version 0.1
 */
public class SqlExecuter {
    private Statement stmt;
    private Connection conn;
    public PreparedStatement pst;
    private ResultSet rs;
    static DBXMLParse parse = new DBXMLParse();
    static ConBean bean = null;

    public SqlExecuter() {
        try {
            parse.init();
            bean = parse.getBean();
            String url = bean.getCon() + "&useSSL=false&characterEncoding=utf-8";
            String user = bean.getU_name().trim();
            String password = bean.getU_pass().trim();
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            System.out.println("数据库连接成功");
        } catch (ClassNotFoundException ee) {
            System.out.println("找不到驱动连接不上");
            ee.printStackTrace();
        } catch (SQLException sql) {
            System.out.println("驱动链接不成功");
            sql.getMessage();
        }
    }

    public synchronized PreparedStatement preProcess(String s) {
        try {
            pst = conn.prepareStatement(s);
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return pst;
    }

    //查询数据库
    public synchronized ResultSet selectSql(String s) {
        try {
            rs = stmt.executeQuery(s);
        } catch (SQLException r) {
            r.printStackTrace();
        }
        return rs;
    }

    //更新数据库
    public synchronized void update(String sql) {
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException se) {
            System.out.println("update exception" + se);
            se.printStackTrace();
        }

    }

    /**
     * 插入一条记录
     *
     * @param sql
     */
    public synchronized boolean insert(String sql) {
        boolean isSuccess = false;
        try {
            stmt.execute(sql);
            isSuccess = stmt.getUpdateCount() > 0 ? true : false;
        } catch (SQLException se) {
            System.out.println("insert exception" + se);
            //se.printStackTrace();
        }
        return isSuccess;
    }


    public synchronized void close() {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //System.out.println("se"+se);
            se.printStackTrace();
        }

    }

	public static void main(String[] args) {
        System.out.println(new SqlExecuter().insert("insert into MailAddress values(\"dasdsdas\",\"www.baidu.com\");"));
	}
}