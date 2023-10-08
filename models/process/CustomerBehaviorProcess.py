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



    def kmean_clus(self):
        return ''

    def dbscan_clus(self):
        return ''

    def kmedoids_clus(self):
        return ''

    def compare_score(self):
        return ''
