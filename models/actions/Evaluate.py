from models.process.CustomePrepDataProcess import CustomePrepDataProcess as PREP_DATA
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn import datasets
from sklearn.cluster import KMeans
from sklearn.cluster import DBSCAN
from sklearn.metrics import silhouette_score


class CustomerBehaviorProcess:

    def __init__(self):
        uid = PREP_DATA().PRE_PROCESS()
        PREP_DATA().k_mean_service()
        PREP_DATA().dbscan_service()
        PREP_DATA().kmedoids_service()
        PREP_DATA().meanshift_clustering()
        PREP_DATA().POST_PROCESS(uid)

    def k_mean_silhouette_score(self, feature):
        for i in range(2, 11):
            labels = KMeans(n_clusters=i, init='k-means++').fit(feature).labels_
            score = silhouette_score(feature, labels, metric='euclidean')
            # print('Silhouetter ' + str(i) + ' Score: %.3f' % score)
            return score

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
            # print("Silhouette Coefficient: %0.3f"
            #          % metrics.silhouette_score(feature, labels))
            # print("Silhouette Coefficient: %0.3f" % score)
            return score