package net.microsoft.java.web.util;

import org.testng.annotations.Test;

import java.sql.*;

/**
 * @description:
 * @Date on 2022/4/27
 * @author: suche
 **/

public class MetaDataTest {


    public static void main(String[] args) {
        String sql = "select id,name,password,create_date,update_date from jdbc_user where id = ? and name = ?";
        try (
                Connection connection = JDBCUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
//             获取sql参数元数据
            ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
            int parameterCount = parameterMetaData.getParameterCount();

/*
            for (int i = 1; i <= parameterCount; i++) {
                // 列类型
                String columnType = parameterMetaData.getParameterTypeName(i);
                // 数据库类型对应的 Java类型
                String className = parameterMetaData.getParameterClassName(i);
                System.out.println("数据库的" + columnType + "对应Java类型是" + className);
            }*/


            // 获取结果集的元数据
            ResultSetMetaData resultSetMetaData = preparedStatement.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                // 获取列名
                String columnName = resultSetMetaData.getColumnName(i);
                // 获取列类型
                String columnType = resultSetMetaData.getColumnTypeName(i);
                // 获取对应的Java类型
                String columnClassName = resultSetMetaData.getColumnClassName(i);

                System.out.println("数据库的列名" + columnName + "数据库的类型" + columnType + "对用Java类型" + columnClassName);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    static boolean replaceColumn(String src) {
        String[] splitColumns = src.split("_");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < splitColumns.length; i++) {
            sb.append(splitColumns[i]);
        }
        src = sb.toString();
        System.out.println(src);
        return true;

    }


    @Test
    public void testResultSetGetString() {

        try (
                Connection connection = JDBCUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("select * from jdbc_user");
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Object id = resultSet.getObject("id");
                System.out.println(id);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
