/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

import com.inkubator.webcore.util.ServiceWebUtil;
import java.sql.Connection;
import java.sql.SQLException;
import org.hibernate.internal.SessionFactoryImpl;

/**
 *
 * @author Deni
 */
public class ServiceWebUtilCon extends ServiceWebUtil{
    
     public static Connection getConnection() throws SQLException {
        return getApplicationContext().getBean("sessionFactory", SessionFactoryImpl.class).getConnectionProvider().getConnection();
    }
}
