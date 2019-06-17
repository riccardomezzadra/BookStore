package com.model.data;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class ConnectionFactory {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {

            String resource = "config.xml";
            Reader reader = Resources.getResourceAsReader(resource);

            Properties properties = new Properties();

            //Others method for datasource , examples in mysql
//            properties.setProperty("username", PropertyReader.getValue(PropertyReader.DB_USERNAME));
//            properties.setProperty("password", PropertyReader.getValue(PropertyReader.DB_PASSWORD));
//            properties.setProperty("url", PropertyReader.getValue(PropertyReader.HOSTNAME));
//            properties.setProperty("dbName", PropertyReader.getValue(PropertyReader.DB_NAME));
//            properties.setProperty("dbPort", PropertyReader.getValue(PropertyReader.DB_PORT));
            /*
            properties.setProperty("username", "Pippo");
            properties.setProperty("password", "Pippa");
            properties.setProperty("url", "localhost");
            properties.setProperty("dbname", "dbName");
            properties.setProperty("dbPort", "3306");
*/


            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, properties);
            }

        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

}
