import pyodbc

class databaseTools:
    global host_target
    global db_name
    global db_auth_u
    global db_auth_p

    host_target = "localhost\SQLEXPRESS"
    db_name = "CUSTOMERBEV"
    db_auth_u = 'sa'
    db_auth_p = 'password'

    def connect_sqlserver(self):
        # connection = pyodbc.connect(
        #   'DRIVER={ODBC Driver 17 for SQL Server};'
        #   'SERVER=' + host_target +
        #   ';DATABASE=' + db_name +
        #   ';UID=' + db_auth_u +
        #   ';PWD=' + db_auth_p)

        connection = 'DRIVER={ODBC Driver 17 for SQL Server};'+'SERVER=' + host_target +';DATABASE=' + db_name +';UID=' + db_auth_u +';PWD=' + db_auth_p

        return connection;
