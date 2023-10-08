class CustomerPrepDataService:

    def query_customer_behavior_prep(self):
        query = 'SELECT TOP (1000) [FULL_NAME]'\
                ', [TOTAL_USED]'\
                ', [TOTAL]'\
                ', [ITEM_ID]'\
                ', [ITEM_NAME]'\
                ' FROM [CUSTOMERBEV].[dbo].[CUSTOMER_BEHAVIOR_PREP]'\
                ' WHERE [FULL_NAME] IS NOT NULL'\
                ' AND [TOTAL] > 0.00 '\
                ' ORDER BY [TOTAL] DESC'
        return query

    def query_customer_behavior_prep_mean(self):
        query = 'SELECT TOP (1000) [FULL_NAME]'\
                ', [TOTAL_USED]'\
                ', [TOTAL]'\
                ', [ITEM_ID]'\
                ', [ITEM_NAME]'\
                ' FROM [CUSTOMERBEV].[dbo].[CUSTOMER_BEHAVIOR_PREP_BK_NEW]'\
                ' WHERE [FULL_NAME] IS NOT NULL'\
                ' AND [TOTAL] > 0.00 '\
                ' ORDER BY [TOTAL] DESC'
        return query

    def query_insert_score(self):
        query = 'INSERT INTO [CUSTOMERBEV].[dbo].[SCORE_SILH] '\
            ' (SCORE_SILH_ID,MODULE_NAME,SLIH_SCORE) '\
            ' VALUES '\
            ' (?,?,?) ';
        return query

    def query_preproces(self):
        query = 'exec pre_process'
        return query

    def query_insert_progress_job(self):
        query = 'INSERT INTO [CUSTOMERBEV].[dbo].[JOB_PROGRESS] '\
                ' (JOB_PROGRESS_ID,JOB_PROGRESS_STATUS,STATUS,JOB_PROGRESS_DATE) '\
                ' VALUES '\
                ' (?,?,?,GETDATE()) ';
        return query;

    def query_update_job(self):
        query = 'UPDATE [CUSTOMERBEV].[dbo].[JOB_PROGRESS] ' \
                ' SET JOB_PROGRESS_STATUS = ?, ' \
                ' STATUS = ? '\
                ' WHERE JOB_PROGRESS_ID = ?'
        return query

    def query_inital_parameter(self):
        query = ' SELECT [CLUST_PARA_VALUE] ' \
                ' FROM  [CUSTOMERBEV].[dbo].CLUST_PARA '\
                ' WHERE [CLUST_PARA_ID] = ?'
        return query