import sklearn.metrics
from sqlalchemy import create_engine

from models.service.databaseTools import databaseTools as DB_TOOLS
from models.service.CustomerPrepDataService import CustomerPrepDataService as CUS_PREP_SERVICE
import pyodbc
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn import datasets
from sklearn.cluster import MeanShift
from sqlalchemy.engine import URL
import urllib

class MeanShiftClusteringService:

    def meanshift_clustering(self):
        connection = DB_TOOLS().connect_sqlserver()
        params = urllib.parse.quote_plus(connection)
        engine = create_engine("mssql+pyodbc:///?odbc_connect=%s" % params)
        query = CUS_PREP_SERVICE().query_customer_behavior_prep_mean()

        df = pd.read_sql(query, engine)
        feature = df.iloc[:, 2:4].values

        query = CUS_PREP_SERVICE().query_inital_parameter()
        connec = pyodbc.connect(connection)
        cursor = connec.cursor()
        cursor.execute(query, ("5"))
        mean_shift_bandwidth = 0
        for row in cursor:
            mean_shift_bandwidth = row[0]
        mean_shift_bandwidth = int(mean_shift_bandwidth)

        clustering = MeanShift(bandwidth=mean_shift_bandwidth).fit(feature)
        # print(clustering.labels_)
        df['label'] = clustering.labels_
        df.to_sql("CUSTOMER_BEHAVIOR_MEANSHIFT_PREDICT", engine)
        score = sklearn.metrics.silhouette_score(feature, clustering.labels_, metric='euclidean')
        query = CUS_PREP_SERVICE().query_insert_score()
        print(query)
        connec = pyodbc.connect(connection)
        cursor = connec.cursor()
        cursor.execute(query, ("4", "CUSTOMER_BEHAVIOR_MEANSHIFT_PREDICT", score))
        connec.commit()
        connec.close()
        # print(score)