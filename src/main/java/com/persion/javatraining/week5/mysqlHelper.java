package com.persion.javatraining.week5;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.List;

public class mysqlHelper implements IsqlHelper{
    private static IsqlHelper insatance = new mysqlHelper();

    public IsqlHelper getInstance(){
        return insatance;
    }

    private mysqlHelper() {};

    // 数据库连接地址
    private static String URL = "jdbc:mysql://localhost:3306/myschool?characterEncoding=utf8&useSSL=true";

    // 数据库的用户名
    private static String UserName = "root";
    // 数据库的密码
    private static String Password = "1234";

    private Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // 加载驱动
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        try {

            HikariConfig hikariConfig = new HikariConfig();
            //设置url
            hikariConfig.setJdbcUrl(URL);
            //数据库帐号
            hikariConfig.setUsername(UserName);
            //数据库密码
            hikariConfig.setPassword(Password);

            hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
            hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
            hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            HikariDataSource ds = new HikariDataSource(hikariConfig);
            return ds.getConnection();

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    public PreparedStatement getPreparedStatement(String sql) {
        Connection conn = connect();
        if (conn == null) {
            return null;
        }
        PreparedStatement updateSales = null;
        try {
            updateSales = conn.prepareStatement(sql);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return updateSales;
    }

    //查询语句
    public ResultSet query(PreparedStatement ps) {
        if(ps == null){
            return null;
        }

        try {

            // 返回结果集
            ResultSet set = ps.executeQuery();

            return set;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(ps);
        }
        return null;
    }

    public void update(PreparedStatement ps){
        if(ps == null){
            return;
        }

        try {
            //执行sql语句
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //释放资源
            close(ps);
        }
    }

    public void add(PreparedStatement ps){
        update(ps);
    }

    public void delete(PreparedStatement ps){
        update(ps);
    }

    private void close(PreparedStatement ps) {
        try {
            ps.close();
        } catch (Exception e2) {

        }
    }


}
