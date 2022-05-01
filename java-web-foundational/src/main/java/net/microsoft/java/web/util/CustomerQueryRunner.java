package net.microsoft.java.web.util;

import com.alibaba.druid.sql.visitor.functions.Char;
import net.microsoft.java.web.entity.User;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:改进DbUtils查询不到列名例如create_date数据
 * @Date on 2022/4/28
 * @author: suche
 **/

public class CustomerQueryRunner {

    private final String UNDER_LINE = "_";
    private DataSource dataSource;

    public CustomerQueryRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 数据的增删改
     *
     * @param sql
     * @param params
     * @return
     */
    public int update(String sql, Object... params) {
        if (null != sql) {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql)
            ) {
                ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
                //  获取parame元数据 ?
                int parameterCount = parameterMetaData.getParameterCount();
                for (int i = 1; i <= parameterCount; i++) {
                    preparedStatement.setObject(i, params[i - 1]);
                }
                int row = preparedStatement.executeUpdate();
                return row;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return -1;
    }

    /**
     * 查询结果是Long类型（定位给统计用户数量）
     *
     * @param sql
     * @param params
     * @return
     */
    public long queryForLong(String sql, Object... params) {

        if (null != sql && null != dataSource) {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ) {
                ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
                int parameterCount = parameterMetaData.getParameterCount();
                for (int i = 1; i <= parameterCount; i++) {
                    preparedStatement.setObject(i, params[i - 1]);
                }

                ResultSet resultSet = preparedStatement.executeQuery();


                long count = -1;
                while (resultSet.next()) {
                    count = resultSet.getLong(1);
                }
                return count;

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        return -1;
    }

    /**
     * 查询结果是T类型（定位给统计用户数量）
     *
     * @param sql
     * @param clazz  类型
     * @param params 参数
     * @param <T>
     * @return
     */
    public <T> T queryForType(String sql, Class<T> clazz, T... params) {

        if (null != dataSource && null != sql) {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ) {
                // 获取sql的元数据
                ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
                // 获取sql ? 有几个
                int parameterCount = parameterMetaData.getParameterCount();

                for (int i = 1; i <= parameterCount; i++) {
                    preparedStatement.setObject(i, params[i - 1]);
                }
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    T t = resultSet.getObject(1, clazz);
                    System.out.println("t.getClass() = " + t.getClass());
                    return t;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return null;
    }


    /**
     * 获取一个类型的记录
     *
     * @param sql
     * @param clazz  获取类型
     * @param params ?参数
     * @param <T>    泛型
     * @return
     */
    public <T> T queryForBean(String sql, Class<T> clazz, Object... params) {
        if (null != dataSource && null != sql) {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ) {
                // sql 元数据
                ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
                // 占位符的个数
                int parameterCount = parameterMetaData.getParameterCount();

                for (int i = 1; i <= parameterCount; i++) {
                    preparedStatement.setObject(i, params[i - 1]);
                }
                // 反射创建对象
                T instance = clazz.newInstance();
                // 反射获取方法
                Method[] methods = clazz.getMethods();
                ResultSetMetaData metaData = preparedStatement.getMetaData();
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    for (int i = 1; i <= metaData.getColumnCount(); i++) {
                        // 获取列名
                        String columnName = metaData.getColumnName(i);
                        for (Method method : methods) {
                            if (method.getName().startsWith("set")) {
                                // 吧set截取掉
                                String filedName = method.getName().substring(3);
                                // 吧第一个字符小写
                                char firstFiledNameUpper = filedName.charAt(0);
                                char firstFiledLowerCase = Character.toLowerCase(firstFiledNameUpper);
                                // 吧第一个字符从大写替换为小写,变为属性
                                String propName = filedName.replace(firstFiledNameUpper, firstFiledLowerCase);

                                // 列名和属性名进行比对
                                if (compareColumnNameFiledName(columnName, propName)) {
                                    Object value = resultSet.getObject(columnName);
                                    method.invoke(instance, value);
                                    break;
                                }
                            }
                        }

                    }
                    return instance;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @param sql    sql 语句
     * @param clazz  传入的实体类型
     * @param params sql参数
     * @param <T>
     * @return 返回实体类型的集合
     */
    public <T> List<T> queryForBeans(String sql, Class<T> clazz, Object... params) {
        if (null != sql && sql != "") {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ) {

                ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
                int parameterCount = parameterMetaData.getParameterCount();
                for (int i = 0; i < parameterCount; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }

                ResultSetMetaData resultMetaData = preparedStatement.getMetaData();
                ResultSet resultBeansSet = preparedStatement.executeQuery();

                List<T> listBeans = new ArrayList<>();

                while (resultBeansSet.next()) {
                    T instance = clazz.newInstance();

                    for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
                        String columnName = resultMetaData.getColumnName(i);
                        Method[] methods = clazz.getMethods();
                        for (Method method : methods) {
                            if (method.getName().startsWith("set")) {
                                String filedName = method.getName().substring(3);
                                char filedNameWithOutUpperCase = filedName.charAt(0);
                                char filedNameWithLowerCase = Character.toLowerCase(filedNameWithOutUpperCase);
                                String propName = filedName.replace(filedNameWithOutUpperCase, filedNameWithLowerCase);
                                if (compareColumnNameFiledName(columnName, propName)) {
                                    Object value = resultBeansSet.getObject(columnName);
                                    method.invoke(instance, value);
                                    break;
                                }
                            }
                        }
                    }
                    listBeans.add(instance);
                }
//                System.out.println(listBeans.size());

                return listBeans;
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return null;
    }

    private boolean compareColumnNameFiledName(String originColumnName, String propName) {
        int firstCharIndex = 0;
        // 如果包含下划线
        if (originColumnName.contains(UNDER_LINE)) {
            // 列名从下划线进行分开
            String[] splitOriginColumnName = originColumnName.split(UNDER_LINE);
            StringBuffer columnNameStringBuffer = new StringBuffer(splitOriginColumnName[firstCharIndex]);
            //把列名改成属性名
            // create_date_time => create date time
            for (int i = 1; i < splitOriginColumnName.length; i++) {

                char firstWordWithOutLowerCase = splitOriginColumnName[i].charAt(firstCharIndex);
                char firstWordWithOutUpperCase = Character.toUpperCase(firstWordWithOutLowerCase);
                String replaceColumn = splitOriginColumnName[i].replace(firstWordWithOutLowerCase, firstWordWithOutUpperCase);
                columnNameStringBuffer.append(replaceColumn);
            }
            String columnName = columnNameStringBuffer.toString();
            return columnName.equals(propName);
        } else if (!originColumnName.contains(UNDER_LINE) && originColumnName.equals(propName)) {
            return true;
        }
        return false;
    }


}
