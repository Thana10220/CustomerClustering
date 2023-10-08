package com.thp.customerbehavior.Service;

import com.thp.customerbehavior.Domain.AdminDomain;
import com.thp.customerbehavior.Domain.ReportDomain;
import com.thp.customerbehavior.Util.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdminService {

    DatabaseUtil DB = new DatabaseUtil();

    public void ML_MODULE() throws SQLException, ClassNotFoundException {

        Connection connection = DB.connectSQLServer();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("exec [CUSTOMERBEV].[dbo].[excute_ml]");
        connection.close();

    }

    public List<AdminDomain> GET_JOB() throws SQLException, ClassNotFoundException {

        List<AdminDomain> item = new ArrayList<AdminDomain>();
        Connection connection = DB.connectSQLServer();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(
                " SELECT  [JOB_PROGRESS_ID],[JOB_PROGRESS_STATUS],[JOB_PROGRESS_DATE] "+
                        "FROM [CUSTOMERBEV].[dbo].[JOB_PROGRESS]"
        );
        while((result !=null) && (result.next())) {
            AdminDomain ADMIN_DOMAIN = new AdminDomain();
            ADMIN_DOMAIN.setJOB_ID(result.getString("JOB_PROGRESS_ID"));
            ADMIN_DOMAIN.setJOB_STATUS(result.getString("JOB_PROGRESS_STATUS"));
            ADMIN_DOMAIN.setJOB_DATE(result.getString("JOB_PROGRESS_DATE"));
            item.add(ADMIN_DOMAIN);
        }
        connection.close();
        return item;
    }

   public AdminDomain GET_INIT_PARAMETER() throws SQLException, ClassNotFoundException {
       AdminDomain item = new AdminDomain();
       Connection connection = DB.connectSQLServer();
       Statement statement = connection.createStatement();

       ResultSet result = statement.executeQuery(
             "SELECT [CLUST_PARA_ID]"+
              " ,[CLUST_PARA_NAME]"+
              " ,[CLUST_PARA_VALUE]"+
              " ,[STATUS]"+
              " FROM [CUSTOMERBEV].[dbo].[CLUST_PARA]"+
              " ORDER BY [CLUST_PARA_ID] ASC"
       );
       int index = 1;
       while((result !=null) && (result.next())) {
           switch (index) {
               case 1:
                   item.setK_mean_cluster(result.getInt("CLUST_PARA_VALUE"));
                   break;
               case 2:
                   item.setDbscan_eps(result.getFloat("CLUST_PARA_VALUE"));
               case 4:
                   item.setKmedoid_cluster(result.getInt("CLUST_PARA_VALUE"));
               case 5:
                   item.setMeanshift_bndwidth(result.getInt("CLUST_PARA_VALUE"));
           }
           index++;
       }

       result = statement.executeQuery(
               "SELECT [CLUST_PARA_ID]"+
                       " ,[CLUST_PARA_NAME]"+
                       " ,[CLUST_PARA_VALUE]"+
                       " ,[STATUS]"+
                       " FROM [CUSTOMERBEV].[dbo].[CLUST_PARA_SUGGEST]"+
                       " ORDER BY [CLUST_PARA_ID] ASC"
       );
       index = 1;
       while((result !=null) && (result.next())) {
           switch (index) {
               case 1:
                   item.setK_mean_cluster_suggest(result.getInt("CLUST_PARA_VALUE"));
                   break;
               case 2:
                   item.setDbscan_eps_suggest(result.getFloat("CLUST_PARA_VALUE"));
               case 4:
                   item.setKmedoid_cluster_suggest(result.getInt("CLUST_PARA_VALUE"));
               case 5:
                   item.setMeanshift_bndwidth_suggest(result.getInt("CLUST_PARA_VALUE"));
           }
           index++;
       }

       connection.close();
       return item;
   }

    public boolean UPDATE_PARAMETER(AdminDomain DOMAIN) throws SQLException, ClassNotFoundException {

        List<AdminDomain> item = new ArrayList<AdminDomain>();
        Connection connection = DB.connectSQLServer();
        Statement statement = connection.createStatement();
       try {
           statement.executeUpdate(
                   " UPDATE [CUSTOMERBEV].[dbo].[CLUST_PARA] " +
                           " SET [CLUST_PARA_VALUE] = '" + DOMAIN.getK_mean_cluster() + "' " +
                           " WHERE [CLUST_PARA_NAME] = 'cluster' "
           );

          statement.executeUpdate(
                   " UPDATE [CUSTOMERBEV].[dbo].[CLUST_PARA] " +
                           " SET [CLUST_PARA_VALUE] = '" + DOMAIN.getDbscan_eps() + "' " +
                           " WHERE [CLUST_PARA_NAME] = 'eps' "
           );

           statement.executeUpdate(
                   " UPDATE [CUSTOMERBEV].[dbo].[CLUST_PARA] " +
                           " SET [CLUST_PARA_VALUE] = '" + DOMAIN.getKmedoid_cluster() + "' " +
                           " WHERE [CLUST_PARA_NAME] = 'n_clusters' "
           );

           statement.executeUpdate(
                   " UPDATE [CUSTOMERBEV].[dbo].[CLUST_PARA] " +
                           " SET [CLUST_PARA_VALUE] = '" + DOMAIN.getMeanshift_bndwidth() + "' " +
                           " WHERE [CLUST_PARA_NAME] = 'bandwidth' "
           );


       }catch (Exception e){
           e.printStackTrace();
           return false;
       }finally {
           connection.close();
       }
        return true;
    }

}
