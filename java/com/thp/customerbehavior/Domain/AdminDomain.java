package com.thp.customerbehavior.Domain;

public class AdminDomain {

    private String JOB_ID;
    private String JOB_STATUS;
    private String STATUS;
    private String JOB_DATE;

    private int k_mean_cluster;
    private float dbscan_eps;
    private int kmedoid_cluster;
    private int meanshift_bndwidth;

    private int k_mean_cluster_suggest;
    private float dbscan_eps_suggest;
    private int kmedoid_cluster_suggest;
    private int meanshift_bndwidth_suggest;



    public String getJOB_ID() {
        return JOB_ID;
    }

    public void setJOB_ID(String JOB_ID) {
        this.JOB_ID = JOB_ID;
    }

    public String getJOB_STATUS() {
        return JOB_STATUS;
    }

    public void setJOB_STATUS(String JOB_STATUS) {
        this.JOB_STATUS = JOB_STATUS;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getJOB_DATE() {
        return JOB_DATE;
    }

    public void setJOB_DATE(String JOB_DATE) {
        this.JOB_DATE = JOB_DATE;
    }

    public int getK_mean_cluster() {
        return k_mean_cluster;
    }

    public void setK_mean_cluster(int k_mean_cluster) {
        this.k_mean_cluster = k_mean_cluster;
    }

    public float getDbscan_eps() {
        return dbscan_eps;
    }

    public void setDbscan_eps(float dbscan_eps) {
        this.dbscan_eps = dbscan_eps;
    }

    public int getKmedoid_cluster() {
        return kmedoid_cluster;
    }

    public void setKmedoid_cluster(int kmedoid_cluster) {
        this.kmedoid_cluster = kmedoid_cluster;
    }

    public int getMeanshift_bndwidth() {
        return meanshift_bndwidth;
    }

    public void setMeanshift_bndwidth(int meanshift_bndwidth) {
        this.meanshift_bndwidth = meanshift_bndwidth;
    }

    public int getK_mean_cluster_suggest() {
        return k_mean_cluster_suggest;
    }

    public void setK_mean_cluster_suggest(int k_mean_cluster_suggest) {
        this.k_mean_cluster_suggest = k_mean_cluster_suggest;
    }

    public float getDbscan_eps_suggest() {
        return dbscan_eps_suggest;
    }

    public void setDbscan_eps_suggest(float dbscan_eps_suggest) {
        this.dbscan_eps_suggest = dbscan_eps_suggest;
    }

    public int getKmedoid_cluster_suggest() {
        return kmedoid_cluster_suggest;
    }

    public void setKmedoid_cluster_suggest(int kmedoid_cluster_suggest) {
        this.kmedoid_cluster_suggest = kmedoid_cluster_suggest;
    }

    public int getMeanshift_bndwidth_suggest() {
        return meanshift_bndwidth_suggest;
    }

    public void setMeanshift_bndwidth_suggest(int meanshift_bndwidth_suggest) {
        this.meanshift_bndwidth_suggest = meanshift_bndwidth_suggest;
    }
}
