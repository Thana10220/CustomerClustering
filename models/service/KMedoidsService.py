import sklearn.metrics
from sklearn.metrics import silhouette_score
from sqlalchemy import create_engine

from models.service.databaseTools import databaseTools as DB_TOOLS
from models.service.CustomerPrepDataService import CustomerPrepDataService as CUS_PREP_SERVICE
import pyodbc
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn_extra.cluster import KMedoids
from sqlalchemy.engine import URL
import urllib

class KMedoidsClusteringService:

    def k_medoid_clusering(self):
        connection = DB_TOOLS().connect_sqlserver()
        params = urllib.parse.quote_plus(connection)
        engine = create_engine("mssql+pyodbc:///?odbc_connect=%s" % params)
        query = CUS_PREP_SERVICE().query_customer_behavior_prep()

        df = pd.read_sql(query, engine)
        feature = df.iloc[:, 1:4].values

        query = CUS_PREP_SERVICE().query_inital_parameter()
        connec = pyodbc.connect(connection)
        cursor = connec.cursor()
        cursor.execute(query, ("4"))
        kmedoids_cluster = 5;
        # for row in cursor:
        #     kmedoids_cluster = row[0]
        # kmedoids_cluster = int(kmedoids_cluster)

        kmedoids = KMedoids(n_clusters=kmedoids_cluster, random_state=0).fit(feature)
        # print(kmedoids.labels_)
        df['label'] = kmedoids.labels_
        # print(df)
        score = sklearn.metrics.silhouette_score(feature, kmedoids.labels_, metric='euclidean')
        # print(score)
        df.to_sql("CUSTOMER_BEHAVIOR_KMEDOIDS_PREDICT", engine)
        query = CUS_PREP_SERVICE().query_insert_score()
        connec = pyodbc.connect(connection)
        cursor = connec.cursor()
        cursor.execute(query, ("3", "CUSTOMER_BEHAVIOR_KMEDOIDS_PREDICT", score))
        connec.commit()
        connec.close()




