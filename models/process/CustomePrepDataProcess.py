from sqlalchemy import create_engine

from models.service.databaseTools import databaseTools as DB_TOOLS
from models.service.CustomerPrepDataService import CustomerPrepDataService as CUS_PREP_SERVICE
from models.service.KmeanClusteringService import KmeanClusteringService as K_MEAN
from models.service.DBSCANClusteringService import DBSCANClusteringService as DBSCAN
from models.service.KMedoidsService import KMedoidsClusteringService as KMEDOIDS
from models.service.databaseTools import databaseTools as DB_TOOLS
from models.service.CustomerPrepDataService import CustomerPrepDataService as CUS_PREP_SERVICE
from models.service.MeanShiftClusteringService import MeanShiftClusteringService as MEAN_SHIFT

import pyodbc
import uuid
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn import datasets
from sklearn.cluster import KMeans
from sqlalchemy.engine import URL
import urllib


class CustomePrepDataProcess:

    def PRE_PROCESS(self):
        connection = DB_TOOLS().connect_sqlserver()
        query = CUS_PREP_SERVICE().query_preproces()
        connec = pyodbc.connect(connection)
        cursor = connec.cursor()
        cursor.execute(query)
        query = CUS_PREP_SERVICE().query_insert_progress_job()
        uid = uuid.uuid1()
        cursor.execute(query, (uid, 'in progress', '1'))
        connec.commit()
        connec.close()
        return uid

    def k_mean_service(self):
        K_MEAN().k_mean_clusering()

    def dbscan_service(self):
        DBSCAN().dbscan_clusering()

    def kmedoids_service(self):
        KMEDOIDS().k_medoid_clusering()

    def meanshift_clustering(self):
        MEAN_SHIFT().meanshift_clustering()

    def POST_PROCESS(self, uid):
        connection = DB_TOOLS().connect_sqlserver()
        query = CUS_PREP_SERVICE().query_update_job()
        connec = pyodbc.connect(connection)
        cursor = connec.cursor()
        cursor.execute(query, ('finish', '0', uid))
        connec.commit()
        connec.close()




