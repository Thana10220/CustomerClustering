package com.thp.customerbehavior.Service;

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

public class GetReportService {

    DatabaseUtil DB = new DatabaseUtil();

    public List<ReportDomain> GET_ALL_REPORT() throws SQLException, ClassNotFoundException {
        List<ReportDomain> item = new ArrayList<ReportDomain>();
        String TABLE_NAME = GET_TABLE();
//        System.out.println(TABLE_NAME);
        Connection connection = DB.connectSQLServer();
        Statement statement = connection.createStatement();
//        ResultSet result = statement.executeQuery(
//                "SELECT DISTINCT TOP (1000)"+
//                "  [FULL_NAME]"+
//                " ,[TOTAL_USED]"+
//                " ,[TOTAL]"+
//                ",[label] "+
//                ",[PRO].Promotion_Desc"+
//                " FROM "+TABLE_NAME+
//                " LEFT JOIN [CUSTOMERBEV].[dbo].[Promotion] PRO"+
//                " ON CUS.label = PRO.Promotion_ID"+
//                " WHERE CUS.label < 5 "
//        );
        String query = "SELECT "+
                "  [FULL_NAME]"+
                " ,[TOTAL_USED]"+
                " ,[TOTAL]"+
                ",[label] "+
                ",[PRO].Promotion_Desc"+
//                " FROM "+TABLE_NAME+
                " from [CUSTOMERBEV].[dbo].[CUSTOMER_BEHAVIOR_KMEAN_PREDICT] CUS"+
                " LEFT JOIN [CUSTOMERBEV].[dbo].[Promotion] PRO"+
                " ON CUS.label = PRO.Promotion_ID"+
//                " WHERE CUS.label < 5 "+
//                " AND CUS.label != 0 "+
                " ORDER BY CUS.label DESC ";
        ResultSet result = statement.executeQuery(query);
        System.out.println(query);
        while((result !=null) && (result.next())) {
            ReportDomain REPORT_DOMAIN = new ReportDomain();
            REPORT_DOMAIN.setCUSTOMER_NAME(result.getString("FULL_NAME"));
            REPORT_DOMAIN.setCUSTOMER_ADDR("-------");
            REPORT_DOMAIN.setCUSTOMER_TEL("-------");
            System.out.println(result.getString("Promotion_Desc"));
            REPORT_DOMAIN.setPROMOTION_RESULT(result.getString("Promotion_Desc"));
            item.add(REPORT_DOMAIN);
        }
        connection.close();
        return item;
    }


    public List<ReportDomain> GET_DASH_BOARD() throws SQLException, ClassNotFoundException {
        List<ReportDomain> item = new ArrayList<ReportDomain>();
        String TABLE_NAME = GET_TABLE();
//        System.out.println(TABLE_NAME);
        Connection connection = DB.connectSQLServer();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(
                "SELECT COUNT(CUS.[label]) as COUNT,PRO.[Promotion_Desc]"+
        " from [CUSTOMERBEV].[dbo].[CUSTOMER_BEHAVIOR_KMEAN_PREDICT] CUS"+
        " LEFT JOIN [CUSTOMERBEV].[dbo].[Promotion] PRO"+
        " ON CUS.[label] = PRO.[Promotion_ID]"+
        " GROUP BY CUS.[label],[Promotion_Desc];"
        );
        while((result !=null) && (result.next())) {
            ReportDomain REPORT_DOMAIN = new ReportDomain();
            REPORT_DOMAIN.setPROMOTION_RESULT(result.getString("Promotion_Desc"));
            REPORT_DOMAIN.setCOUNTER(result.getInt("COUNT"));
            item.add(REPORT_DOMAIN);
        }
        connection.close();
        return item;
    }

    private String GET_TABLE() throws SQLException, ClassNotFoundException {
        String TABLE_NAME = "";
        Connection connection = DB.connectSQLServer();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(
                "SELECT TOP (1) [SCORE_SILH_ID] "+
                        "  ,[MODULE_NAME],[SLIH_SCORE] "+
                        " FROM [CUSTOMERBEV].[dbo].[SCORE_SILH] "+
                        " ORDER BY [SLIH_SCORE] DESC "
        );
        while((result !=null) && (result.next())) {
            TABLE_NAME = "[CUSTOMERBEV].[dbo]."+result.getString("MODULE_NAME")+" AS CUS";
        }
        connection.close();
        return TABLE_NAME;
    }


}
