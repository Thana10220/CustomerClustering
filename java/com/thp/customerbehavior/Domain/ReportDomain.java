package com.thp.customerbehavior.Domain;

public class ReportDomain {

   private String CUSTOMER_ID;
   private String CUSTOMER_NAME;
   private String CUSTOMER_ADDR;
   private String CUSTOMER_TEL;
   private String PROMOTION_ID;
   private String PROMOTION_RESULT;
   private String STATUS;

   private int COUNTER;

    public String getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(String CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }

    public String getCUSTOMER_NAME() {
        return CUSTOMER_NAME;
    }

    public void setCUSTOMER_NAME(String CUSTOMER_NAME) {
        this.CUSTOMER_NAME = CUSTOMER_NAME;
    }

    public String getCUSTOMER_ADDR() {
        return CUSTOMER_ADDR;
    }

    public void setCUSTOMER_ADDR(String CUSTOMER_ADDR) {
        this.CUSTOMER_ADDR = CUSTOMER_ADDR;
    }

    public String getCUSTOMER_TEL() {
        return CUSTOMER_TEL;
    }

    public void setCUSTOMER_TEL(String CUSTOMER_TEL) {
        this.CUSTOMER_TEL = CUSTOMER_TEL;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public String getPROMOTION_ID() {
        return PROMOTION_ID;
    }

    public void setPROMOTION_ID(String PROMOTION_ID) {
        this.PROMOTION_ID = PROMOTION_ID;
    }

    public String getPROMOTION_RESULT() {
        return PROMOTION_RESULT;
    }

    public void setPROMOTION_RESULT(String PROMOTION_RESULT) {
        this.PROMOTION_RESULT = PROMOTION_RESULT;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public int getCOUNTER() {
        return COUNTER;
    }

    public void setCOUNTER(int COUNTER) {
        this.COUNTER = COUNTER;
    }
}
