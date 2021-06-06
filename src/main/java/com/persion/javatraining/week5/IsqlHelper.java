package com.persion.javatraining.week5;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IsqlHelper {

    //增
    void add(PreparedStatement ps);

    //删
    void delete(PreparedStatement ps);

    //改
    void update(PreparedStatement ps);

    //查
    ResultSet query(PreparedStatement ps);

    PreparedStatement getPreparedStatement(String sql);
}
