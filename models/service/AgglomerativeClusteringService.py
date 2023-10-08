from sklearn.metrics import silhouette_score
from sqlalchemy import create_engine

from models.service.databaseTools import databaseTools as DB_TOOLS
from models.service.CustomerPrepDataService import CustomerPrepDataService as CUS_PREP_SERVICE
import pyodbc
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn import datasets
from sklearn.cluster import AgglomerativeClustering
from sklearn.cluster import MeanShift
from sqlalchemy.engine import URL
import urllib

class AgglomerativeClusteringService:

    def agglomerative_clustering(self):
        connection = DB_TOOLS().connect_sqlserver()
        params = urllib.parse.quote_plus(connection)
        engine = create_engine("mssql+pyodbc:///?odbc_connect=%s" % params)
        query = CUS_PREP_SERVICE().query_customer_behavior_prep()

        df = pd.read_sql(query, engine)
        feature = df.iloc[:, 3:4].values
        clustering = AgglomerativeClustering().fit(feature)
        print(clustering.labels_)
        score = sklearn.metrics.silhouette_score(feature, clustering.labels_, metric='euclidean')
        # print(score)