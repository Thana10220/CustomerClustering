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
from sklearn.cluster import KMeans
from sqlalchemy.engine import URL
import urllib


class KmeanClusteringService:

    def k_mean_clusering(self):
        connection = DB_TOOLS().connect_sqlserver()
        params = urllib.parse.quote_plus(connection)
        engine = create_engine("mssql+pyodbc:///?odbc_connect=%s" % params)
        query = CUS_PREP_SERVICE().query_customer_behavior_prep()

        df = pd.read_sql(query, engine)

        feature = df.iloc[:, 1:4].values

        query = CUS_PREP_SERVICE().query_inital_parameter()
        connec = pyodbc.connect(connection)
        cursor = connec.cursor()
        cursor.execute(query, ("1"))
        k_mean_cluster = 0;
        for row in cursor:
            k_mean_cluster = row[0]
        k_mean_cluster = int(k_mean_cluster)

        # self.k_mean_eblow_wcss(feature)
        kmeans = KMeans(n_clusters=k_mean_cluster)
        labels = kmeans.fit_predict(feature)
        #print(labels)
        #print(kmeans.cluster_centers_)
        #self.k_mean_graph_plot(feature, kmeans)
        df['label'] = labels
        df.to_sql("CUSTOMER_BEHAVIOR_KMEAN_PREDICT", engine)
        cluster_labels = kmeans.labels_
        score = self.k_mean_silhouette_score(feature);
        print(score)
        # score = str(score);
        query = CUS_PREP_SERVICE().query_insert_score()
        print(query)
        connec = pyodbc.connect(connection)
        cursor = connec.cursor()
        cursor.execute(query, ("1", "CUSTOMER_BEHAVIOR_KMEAN_PREDICT", score))
        connec.commit()
        connec.close()



    def k_mean_silhouette_score(self, feature):
        for i in range(2, 11):
            labels = KMeans(n_clusters=i, init='k-means++').fit(feature).labels_
            score = silhouette_score(feature, labels, metric='euclidean')
            #print('Silhouetter ' + str(i) + ' Score: %.3f' % score)
            return score

    def k_mean_graph_plot(self, feature, kmeans):
        plt.figure(figsize=(14, 8))
        plt.scatter(feature[:, 0], feature[:, 1], c=kmeans.labels_, s=105)
        plt.scatter(kmeans.cluster_centers_[:, 0], kmeans.cluster_centers_[:, 1], color='red', s=250)
        plt.title('Cluster of Customer\n', fontsize=20)
        plt.xlabel('TOTAL')
        plt.ylabel('ITEM_ID')
        plt.show()

    def k_mean_eblow_wcss(self, feature):
        wcss = []
        for k in range(1, 11):
            kmeans = KMeans(n_clusters=k, init='k-means++')
            kmeans.fit(feature)
            wcss.append(kmeans.inertia_)
        plt.figure(figsize=(12, 7))
        plt.plot(range(1, 11), wcss, linewidth=2, marker='8')
        plt.title('Elbow Plot\n', fontsize=20)
        plt.xlabel('K')
        plt.ylabel('WCSS')
        plt.show()