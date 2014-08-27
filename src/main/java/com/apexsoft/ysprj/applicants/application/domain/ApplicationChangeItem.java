package com.apexsoft.ysprj.applicants.application.domain;

import java.util.Date;

public class ApplicationChangeItem extends ApplicationChangeItemKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_CHG_ITEM.CHG_ITEM_CODE
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    private String chgItemCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_CHG_ITEM.ITEM_NAME
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    private String itemName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_CHG_ITEM.BEF_ITEM_DETL
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    private String befItemDetl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_CHG_ITEM.AFT_ITEM_DETL
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    private String aftItemDetl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_CHG_ITEM.CRE_ID
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    private String creId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_CHG_ITEM.CRE_DATE
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    private Date creDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_CHG_ITEM.MOD_ID
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    private String modId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_CHG_ITEM.MOD_DATE
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    private Date modDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_CHG_ITEM.CHG_ITEM_CODE
     *
     * @return the value of APPL_CHG_ITEM.CHG_ITEM_CODE
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    public String getChgItemCode() {
        return chgItemCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_CHG_ITEM.CHG_ITEM_CODE
     *
     * @param chgItemCode the value for APPL_CHG_ITEM.CHG_ITEM_CODE
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    public void setChgItemCode(String chgItemCode) {
        this.chgItemCode = chgItemCode == null ? null : chgItemCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_CHG_ITEM.ITEM_NAME
     *
     * @return the value of APPL_CHG_ITEM.ITEM_NAME
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_CHG_ITEM.ITEM_NAME
     *
     * @param itemName the value for APPL_CHG_ITEM.ITEM_NAME
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_CHG_ITEM.BEF_ITEM_DETL
     *
     * @return the value of APPL_CHG_ITEM.BEF_ITEM_DETL
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    public String getBefItemDetl() {
        return befItemDetl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_CHG_ITEM.BEF_ITEM_DETL
     *
     * @param befItemDetl the value for APPL_CHG_ITEM.BEF_ITEM_DETL
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    public void setBefItemDetl(String befItemDetl) {
        this.befItemDetl = befItemDetl == null ? null : befItemDetl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_CHG_ITEM.AFT_ITEM_DETL
     *
     * @return the value of APPL_CHG_ITEM.AFT_ITEM_DETL
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    public String getAftItemDetl() {
        return aftItemDetl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_CHG_ITEM.AFT_ITEM_DETL
     *
     * @param aftItemDetl the value for APPL_CHG_ITEM.AFT_ITEM_DETL
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    public void setAftItemDetl(String aftItemDetl) {
        this.aftItemDetl = aftItemDetl == null ? null : aftItemDetl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_CHG_ITEM.CRE_ID
     *
     * @return the value of APPL_CHG_ITEM.CRE_ID
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    public String getCreId() {
        return creId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_CHG_ITEM.CRE_ID
     *
     * @param creId the value for APPL_CHG_ITEM.CRE_ID
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    public void setCreId(String creId) {
        this.creId = creId == null ? null : creId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_CHG_ITEM.CRE_DATE
     *
     * @return the value of APPL_CHG_ITEM.CRE_DATE
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    public Date getCreDate() {
        return creDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_CHG_ITEM.CRE_DATE
     *
     * @param creDate the value for APPL_CHG_ITEM.CRE_DATE
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    public void setCreDate(Date creDate) {
        this.creDate = creDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_CHG_ITEM.MOD_ID
     *
     * @return the value of APPL_CHG_ITEM.MOD_ID
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    public String getModId() {
        return modId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_CHG_ITEM.MOD_ID
     *
     * @param modId the value for APPL_CHG_ITEM.MOD_ID
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    public void setModId(String modId) {
        this.modId = modId == null ? null : modId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_CHG_ITEM.MOD_DATE
     *
     * @return the value of APPL_CHG_ITEM.MOD_DATE
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    public Date getModDate() {
        return modDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_CHG_ITEM.MOD_DATE
     *
     * @param modDate the value for APPL_CHG_ITEM.MOD_DATE
     *
     * @mbggenerated Mon Aug 25 20:58:46 KST 2014
     */
    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }
}