from sklearn.metrics import silhouette_score
from sqlalchemy import create_engine

from models.service.databaseTools import databaseTools as DB_TOOLS
from models.service.CustomerPrepDataService import CustomerPrepDataService as CUS_PREP_SERVICE
import pyodbc
import numpy as np
import pandas as pd
from sklearn import datasets
from sklearn import metrics
from sklearn.cluster import DBSCAN
from sqlalchemy.engine import URL
import urllib

class DBSCANClusteringService:

    def dbscan_clusering(self):
        connection = DB_TOOLS().connect_sqlserver()
        params = urllib.parse.quote_plus(connection)
        engine = create_engine("mssql+pyodbc:///?odbc_connect=%s" % params)
        query = CUS_PREP_SERVICE().query_customer_behavior_prep()

        df = pd.read_sql(query, engine)
        feature = df.iloc[:, 1:4].values

        query = CUS_PREP_SERVICE().query_inital_parameter()
        connec = pyodbc.connect(connection)
        cursor = connec.cursor()
        cursor.execute(query, ("2"))
        dbscan_eps = 0.1;
        # for row in cursor:
        #     dbscan_eps = row[0]

        dbscan = DBSCAN(eps=dbscan_eps, min_samples=40)
        labels = dbscan.fit_predict(feature)
        df['label'] = labels
        df.to_sql("CUSTOMER_BEHAVIOR_DBSCAN_PREDICT", engine)
        score = self.dbscan_silhouette_score(labels, feature, dbscan)
        print(score)
        # score = str(score);
        query = CUS_PREP_SERVICE().query_insert_score()
        print(query)
        connec = pyodbc.connect(connection)
        cursor = connec.cursor()
        cursor.execute(query, ("2", "CUSTOMER_BEHAVIOR_DBSCAN_PREDICT", score))
        connec.commit()
        connec.close()

    def dbscan_silhouette_score(self, labels, feature, dbscan):
        cluster_labels = dbscan.labels_

        # measure the performance of dbscan algo
        # Identifying which points make up our “core points”
        core_samples = np.zeros_like(labels, dtype=bool)
        core_samples[dbscan.core_sample_indices_] = True

        # Calculating "the number of clusters"
        n_clusters_ = len(set(labels)) - (1 if -1 in labels else 0)

        score = silhouette_score(feature, labels, metric='euclidean')
        # Computing "the Silhouette Score"
        #print("Silhouette Coefficient: %0.3f"
        #          % metrics.silhouette_score(feature, labels))
        # print("Silhouette Coefficient: %0.3f" % score)
        return score