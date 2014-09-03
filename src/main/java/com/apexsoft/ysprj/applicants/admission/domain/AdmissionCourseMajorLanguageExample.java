package com.apexsoft.ysprj.applicants.admission.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AdmissionCourseMajorLanguageExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ADMS_CORS_MAJ_LANG
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ADMS_CORS_MAJ_LANG
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ADMS_CORS_MAJ_LANG
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS_CORS_MAJ_LANG
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    public AdmissionCourseMajorLanguageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS_CORS_MAJ_LANG
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS_CORS_MAJ_LANG
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS_CORS_MAJ_LANG
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS_CORS_MAJ_LANG
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS_CORS_MAJ_LANG
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS_CORS_MAJ_LANG
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS_CORS_MAJ_LANG
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS_CORS_MAJ_LANG
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS_CORS_MAJ_LANG
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS_CORS_MAJ_LANG
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ADMS_CORS_MAJ_LANG
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andAdmsCorsNoIsNull() {
            addCriterion("ADMS_CORS_NO is null");
            return (Criteria) this;
        }

        public Criteria andAdmsCorsNoIsNotNull() {
            addCriterion("ADMS_CORS_NO is not null");
            return (Criteria) this;
        }

        public Criteria andAdmsCorsNoEqualTo(Integer value) {
            addCriterion("ADMS_CORS_NO =", value, "admsCorsNo");
            return (Criteria) this;
        }

        public Criteria andAdmsCorsNoNotEqualTo(Integer value) {
            addCriterion("ADMS_CORS_NO <>", value, "admsCorsNo");
            return (Criteria) this;
        }

        public Criteria andAdmsCorsNoGreaterThan(Integer value) {
            addCriterion("ADMS_CORS_NO >", value, "admsCorsNo");
            return (Criteria) this;
        }

        public Criteria andAdmsCorsNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("ADMS_CORS_NO >=", value, "admsCorsNo");
            return (Criteria) this;
        }

        public Criteria andAdmsCorsNoLessThan(Integer value) {
            addCriterion("ADMS_CORS_NO <", value, "admsCorsNo");
            return (Criteria) this;
        }

        public Criteria andAdmsCorsNoLessThanOrEqualTo(Integer value) {
            addCriterion("ADMS_CORS_NO <=", value, "admsCorsNo");
            return (Criteria) this;
        }

        public Criteria andAdmsCorsNoIn(List<Integer> values) {
            addCriterion("ADMS_CORS_NO in", values, "admsCorsNo");
            return (Criteria) this;
        }

        public Criteria andAdmsCorsNoNotIn(List<Integer> values) {
            addCriterion("ADMS_CORS_NO not in", values, "admsCorsNo");
            return (Criteria) this;
        }

        public Criteria andAdmsCorsNoBetween(Integer value1, Integer value2) {
            addCriterion("ADMS_CORS_NO between", value1, value2, "admsCorsNo");
            return (Criteria) this;
        }

        public Criteria andAdmsCorsNoNotBetween(Integer value1, Integer value2) {
            addCriterion("ADMS_CORS_NO not between", value1, value2, "admsCorsNo");
            return (Criteria) this;
        }

        public Criteria andDetlMajCodeIsNull() {
            addCriterion("DETL_MAJ_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDetlMajCodeIsNotNull() {
            addCriterion("DETL_MAJ_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDetlMajCodeEqualTo(String value) {
            addCriterion("DETL_MAJ_CODE =", value, "detlMajCode");
            return (Criteria) this;
        }

        public Criteria andDetlMajCodeNotEqualTo(String value) {
            addCriterion("DETL_MAJ_CODE <>", value, "detlMajCode");
            return (Criteria) this;
        }

        public Criteria andDetlMajCodeGreaterThan(String value) {
            addCriterion("DETL_MAJ_CODE >", value, "detlMajCode");
            return (Criteria) this;
        }

        public Criteria andDetlMajCodeGreaterThanOrEqualTo(String value) {
            addCriterion("DETL_MAJ_CODE >=", value, "detlMajCode");
            return (Criteria) this;
        }

        public Criteria andDetlMajCodeLessThan(String value) {
            addCriterion("DETL_MAJ_CODE <", value, "detlMajCode");
            return (Criteria) this;
        }

        public Criteria andDetlMajCodeLessThanOrEqualTo(String value) {
            addCriterion("DETL_MAJ_CODE <=", value, "detlMajCode");
            return (Criteria) this;
        }

        public Criteria andDetlMajCodeLike(String value) {
            addCriterion("DETL_MAJ_CODE like", value, "detlMajCode");
            return (Criteria) this;
        }

        public Criteria andDetlMajCodeNotLike(String value) {
            addCriterion("DETL_MAJ_CODE not like", value, "detlMajCode");
            return (Criteria) this;
        }

        public Criteria andDetlMajCodeIn(List<String> values) {
            addCriterion("DETL_MAJ_CODE in", values, "detlMajCode");
            return (Criteria) this;
        }

        public Criteria andDetlMajCodeNotIn(List<String> values) {
            addCriterion("DETL_MAJ_CODE not in", values, "detlMajCode");
            return (Criteria) this;
        }

        public Criteria andDetlMajCodeBetween(String value1, String value2) {
            addCriterion("DETL_MAJ_CODE between", value1, value2, "detlMajCode");
            return (Criteria) this;
        }

        public Criteria andDetlMajCodeNotBetween(String value1, String value2) {
            addCriterion("DETL_MAJ_CODE not between", value1, value2, "detlMajCode");
            return (Criteria) this;
        }

        public Criteria andExamCodeIsNull() {
            addCriterion("EXAM_CODE is null");
            return (Criteria) this;
        }

        public Criteria andExamCodeIsNotNull() {
            addCriterion("EXAM_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andExamCodeEqualTo(String value) {
            addCriterion("EXAM_CODE =", value, "examCode");
            return (Criteria) this;
        }

        public Criteria andExamCodeNotEqualTo(String value) {
            addCriterion("EXAM_CODE <>", value, "examCode");
            return (Criteria) this;
        }

        public Criteria andExamCodeGreaterThan(String value) {
            addCriterion("EXAM_CODE >", value, "examCode");
            return (Criteria) this;
        }

        public Criteria andExamCodeGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_CODE >=", value, "examCode");
            return (Criteria) this;
        }

        public Criteria andExamCodeLessThan(String value) {
            addCriterion("EXAM_CODE <", value, "examCode");
            return (Criteria) this;
        }

        public Criteria andExamCodeLessThanOrEqualTo(String value) {
            addCriterion("EXAM_CODE <=", value, "examCode");
            return (Criteria) this;
        }

        public Criteria andExamCodeLike(String value) {
            addCriterion("EXAM_CODE like", value, "examCode");
            return (Criteria) this;
        }

        public Criteria andExamCodeNotLike(String value) {
            addCriterion("EXAM_CODE not like", value, "examCode");
            return (Criteria) this;
        }

        public Criteria andExamCodeIn(List<String> values) {
            addCriterion("EXAM_CODE in", values, "examCode");
            return (Criteria) this;
        }

        public Criteria andExamCodeNotIn(List<String> values) {
            addCriterion("EXAM_CODE not in", values, "examCode");
            return (Criteria) this;
        }

        public Criteria andExamCodeBetween(String value1, String value2) {
            addCriterion("EXAM_CODE between", value1, value2, "examCode");
            return (Criteria) this;
        }

        public Criteria andExamCodeNotBetween(String value1, String value2) {
            addCriterion("EXAM_CODE not between", value1, value2, "examCode");
            return (Criteria) this;
        }

        public Criteria andMdtYnIsNull() {
            addCriterion("MDT_YN is null");
            return (Criteria) this;
        }

        public Criteria andMdtYnIsNotNull() {
            addCriterion("MDT_YN is not null");
            return (Criteria) this;
        }

        public Criteria andMdtYnEqualTo(String value) {
            addCriterion("MDT_YN =", value, "mdtYn");
            return (Criteria) this;
        }

        public Criteria andMdtYnNotEqualTo(String value) {
            addCriterion("MDT_YN <>", value, "mdtYn");
            return (Criteria) this;
        }

        public Criteria andMdtYnGreaterThan(String value) {
            addCriterion("MDT_YN >", value, "mdtYn");
            return (Criteria) this;
        }

        public Criteria andMdtYnGreaterThanOrEqualTo(String value) {
            addCriterion("MDT_YN >=", value, "mdtYn");
            return (Criteria) this;
        }

        public Criteria andMdtYnLessThan(String value) {
            addCriterion("MDT_YN <", value, "mdtYn");
            return (Criteria) this;
        }

        public Criteria andMdtYnLessThanOrEqualTo(String value) {
            addCriterion("MDT_YN <=", value, "mdtYn");
            return (Criteria) this;
        }

        public Criteria andMdtYnLike(String value) {
            addCriterion("MDT_YN like", value, "mdtYn");
            return (Criteria) this;
        }

        public Criteria andMdtYnNotLike(String value) {
            addCriterion("MDT_YN not like", value, "mdtYn");
            return (Criteria) this;
        }

        public Criteria andMdtYnIn(List<String> values) {
            addCriterion("MDT_YN in", values, "mdtYn");
            return (Criteria) this;
        }

        public Criteria andMdtYnNotIn(List<String> values) {
            addCriterion("MDT_YN not in", values, "mdtYn");
            return (Criteria) this;
        }

        public Criteria andMdtYnBetween(String value1, String value2) {
            addCriterion("MDT_YN between", value1, value2, "mdtYn");
            return (Criteria) this;
        }

        public Criteria andMdtYnNotBetween(String value1, String value2) {
            addCriterion("MDT_YN not between", value1, value2, "mdtYn");
            return (Criteria) this;
        }

        public Criteria andCanYnIsNull() {
            addCriterion("CAN_YN is null");
            return (Criteria) this;
        }

        public Criteria andCanYnIsNotNull() {
            addCriterion("CAN_YN is not null");
            return (Criteria) this;
        }

        public Criteria andCanYnEqualTo(String value) {
            addCriterion("CAN_YN =", value, "canYn");
            return (Criteria) this;
        }

        public Criteria andCanYnNotEqualTo(String value) {
            addCriterion("CAN_YN <>", value, "canYn");
            return (Criteria) this;
        }

        public Criteria andCanYnGreaterThan(String value) {
            addCriterion("CAN_YN >", value, "canYn");
            return (Criteria) this;
        }

        public Criteria andCanYnGreaterThanOrEqualTo(String value) {
            addCriterion("CAN_YN >=", value, "canYn");
            return (Criteria) this;
        }

        public Criteria andCanYnLessThan(String value) {
            addCriterion("CAN_YN <", value, "canYn");
            return (Criteria) this;
        }

        public Criteria andCanYnLessThanOrEqualTo(String value) {
            addCriterion("CAN_YN <=", value, "canYn");
            return (Criteria) this;
        }

        public Criteria andCanYnLike(String value) {
            addCriterion("CAN_YN like", value, "canYn");
            return (Criteria) this;
        }

        public Criteria andCanYnNotLike(String value) {
            addCriterion("CAN_YN not like", value, "canYn");
            return (Criteria) this;
        }

        public Criteria andCanYnIn(List<String> values) {
            addCriterion("CAN_YN in", values, "canYn");
            return (Criteria) this;
        }

        public Criteria andCanYnNotIn(List<String> values) {
            addCriterion("CAN_YN not in", values, "canYn");
            return (Criteria) this;
        }

        public Criteria andCanYnBetween(String value1, String value2) {
            addCriterion("CAN_YN between", value1, value2, "canYn");
            return (Criteria) this;
        }

        public Criteria andCanYnNotBetween(String value1, String value2) {
            addCriterion("CAN_YN not between", value1, value2, "canYn");
            return (Criteria) this;
        }

        public Criteria andMinScrIsNull() {
            addCriterion("MIN_SCR is null");
            return (Criteria) this;
        }

        public Criteria andMinScrIsNotNull() {
            addCriterion("MIN_SCR is not null");
            return (Criteria) this;
        }

        public Criteria andMinScrEqualTo(Integer value) {
            addCriterion("MIN_SCR =", value, "minScr");
            return (Criteria) this;
        }

        public Criteria andMinScrNotEqualTo(Integer value) {
            addCriterion("MIN_SCR <>", value, "minScr");
            return (Criteria) this;
        }

        public Criteria andMinScrGreaterThan(Integer value) {
            addCriterion("MIN_SCR >", value, "minScr");
            return (Criteria) this;
        }

        public Criteria andMinScrGreaterThanOrEqualTo(Integer value) {
            addCriterion("MIN_SCR >=", value, "minScr");
            return (Criteria) this;
        }

        public Criteria andMinScrLessThan(Integer value) {
            addCriterion("MIN_SCR <", value, "minScr");
            return (Criteria) this;
        }

        public Criteria andMinScrLessThanOrEqualTo(Integer value) {
            addCriterion("MIN_SCR <=", value, "minScr");
            return (Criteria) this;
        }

        public Criteria andMinScrIn(List<Integer> values) {
            addCriterion("MIN_SCR in", values, "minScr");
            return (Criteria) this;
        }

        public Criteria andMinScrNotIn(List<Integer> values) {
            addCriterion("MIN_SCR not in", values, "minScr");
            return (Criteria) this;
        }

        public Criteria andMinScrBetween(Integer value1, Integer value2) {
            addCriterion("MIN_SCR between", value1, value2, "minScr");
            return (Criteria) this;
        }

        public Criteria andMinScrNotBetween(Integer value1, Integer value2) {
            addCriterion("MIN_SCR not between", value1, value2, "minScr");
            return (Criteria) this;
        }

        public Criteria andMsgNoIsNull() {
            addCriterion("MSG_NO is null");
            return (Criteria) this;
        }

        public Criteria andMsgNoIsNotNull() {
            addCriterion("MSG_NO is not null");
            return (Criteria) this;
        }

        public Criteria andMsgNoEqualTo(String value) {
            addCriterion("MSG_NO =", value, "msgNo");
            return (Criteria) this;
        }

        public Criteria andMsgNoNotEqualTo(String value) {
            addCriterion("MSG_NO <>", value, "msgNo");
            return (Criteria) this;
        }

        public Criteria andMsgNoGreaterThan(String value) {
            addCriterion("MSG_NO >", value, "msgNo");
            return (Criteria) this;
        }

        public Criteria andMsgNoGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_NO >=", value, "msgNo");
            return (Criteria) this;
        }

        public Criteria andMsgNoLessThan(String value) {
            addCriterion("MSG_NO <", value, "msgNo");
            return (Criteria) this;
        }

        public Criteria andMsgNoLessThanOrEqualTo(String value) {
            addCriterion("MSG_NO <=", value, "msgNo");
            return (Criteria) this;
        }

        public Criteria andMsgNoLike(String value) {
            addCriterion("MSG_NO like", value, "msgNo");
            return (Criteria) this;
        }

        public Criteria andMsgNoNotLike(String value) {
            addCriterion("MSG_NO not like", value, "msgNo");
            return (Criteria) this;
        }

        public Criteria andMsgNoIn(List<String> values) {
            addCriterion("MSG_NO in", values, "msgNo");
            return (Criteria) this;
        }

        public Criteria andMsgNoNotIn(List<String> values) {
            addCriterion("MSG_NO not in", values, "msgNo");
            return (Criteria) this;
        }

        public Criteria andMsgNoBetween(String value1, String value2) {
            addCriterion("MSG_NO between", value1, value2, "msgNo");
            return (Criteria) this;
        }

        public Criteria andMsgNoNotBetween(String value1, String value2) {
            addCriterion("MSG_NO not between", value1, value2, "msgNo");
            return (Criteria) this;
        }

        public Criteria andCreIdIsNull() {
            addCriterion("CRE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCreIdIsNotNull() {
            addCriterion("CRE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCreIdEqualTo(String value) {
            addCriterion("CRE_ID =", value, "creId");
            return (Criteria) this;
        }

        public Criteria andCreIdNotEqualTo(String value) {
            addCriterion("CRE_ID <>", value, "creId");
            return (Criteria) this;
        }

        public Criteria andCreIdGreaterThan(String value) {
            addCriterion("CRE_ID >", value, "creId");
            return (Criteria) this;
        }

        public Criteria andCreIdGreaterThanOrEqualTo(String value) {
            addCriterion("CRE_ID >=", value, "creId");
            return (Criteria) this;
        }

        public Criteria andCreIdLessThan(String value) {
            addCriterion("CRE_ID <", value, "creId");
            return (Criteria) this;
        }

        public Criteria andCreIdLessThanOrEqualTo(String value) {
            addCriterion("CRE_ID <=", value, "creId");
            return (Criteria) this;
        }

        public Criteria andCreIdLike(String value) {
            addCriterion("CRE_ID like", value, "creId");
            return (Criteria) this;
        }

        public Criteria andCreIdNotLike(String value) {
            addCriterion("CRE_ID not like", value, "creId");
            return (Criteria) this;
        }

        public Criteria andCreIdIn(List<String> values) {
            addCriterion("CRE_ID in", values, "creId");
            return (Criteria) this;
        }

        public Criteria andCreIdNotIn(List<String> values) {
            addCriterion("CRE_ID not in", values, "creId");
            return (Criteria) this;
        }

        public Criteria andCreIdBetween(String value1, String value2) {
            addCriterion("CRE_ID between", value1, value2, "creId");
            return (Criteria) this;
        }

        public Criteria andCreIdNotBetween(String value1, String value2) {
            addCriterion("CRE_ID not between", value1, value2, "creId");
            return (Criteria) this;
        }

        public Criteria andCreDateIsNull() {
            addCriterion("CRE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreDateIsNotNull() {
            addCriterion("CRE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreDateEqualTo(Date value) {
            addCriterionForJDBCDate("CRE_DATE =", value, "creDate");
            return (Criteria) this;
        }

        public Criteria andCreDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("CRE_DATE <>", value, "creDate");
            return (Criteria) this;
        }

        public Criteria andCreDateGreaterThan(Date value) {
            addCriterionForJDBCDate("CRE_DATE >", value, "creDate");
            return (Criteria) this;
        }

        public Criteria andCreDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CRE_DATE >=", value, "creDate");
            return (Criteria) this;
        }

        public Criteria andCreDateLessThan(Date value) {
            addCriterionForJDBCDate("CRE_DATE <", value, "creDate");
            return (Criteria) this;
        }

        public Criteria andCreDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CRE_DATE <=", value, "creDate");
            return (Criteria) this;
        }

        public Criteria andCreDateIn(List<Date> values) {
            addCriterionForJDBCDate("CRE_DATE in", values, "creDate");
            return (Criteria) this;
        }

        public Criteria andCreDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("CRE_DATE not in", values, "creDate");
            return (Criteria) this;
        }

        public Criteria andCreDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CRE_DATE between", value1, value2, "creDate");
            return (Criteria) this;
        }

        public Criteria andCreDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CRE_DATE not between", value1, value2, "creDate");
            return (Criteria) this;
        }

        public Criteria andModIdIsNull() {
            addCriterion("MOD_ID is null");
            return (Criteria) this;
        }

        public Criteria andModIdIsNotNull() {
            addCriterion("MOD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andModIdEqualTo(String value) {
            addCriterion("MOD_ID =", value, "modId");
            return (Criteria) this;
        }

        public Criteria andModIdNotEqualTo(String value) {
            addCriterion("MOD_ID <>", value, "modId");
            return (Criteria) this;
        }

        public Criteria andModIdGreaterThan(String value) {
            addCriterion("MOD_ID >", value, "modId");
            return (Criteria) this;
        }

        public Criteria andModIdGreaterThanOrEqualTo(String value) {
            addCriterion("MOD_ID >=", value, "modId");
            return (Criteria) this;
        }

        public Criteria andModIdLessThan(String value) {
            addCriterion("MOD_ID <", value, "modId");
            return (Criteria) this;
        }

        public Criteria andModIdLessThanOrEqualTo(String value) {
            addCriterion("MOD_ID <=", value, "modId");
            return (Criteria) this;
        }

        public Criteria andModIdLike(String value) {
            addCriterion("MOD_ID like", value, "modId");
            return (Criteria) this;
        }

        public Criteria andModIdNotLike(String value) {
            addCriterion("MOD_ID not like", value, "modId");
            return (Criteria) this;
        }

        public Criteria andModIdIn(List<String> values) {
            addCriterion("MOD_ID in", values, "modId");
            return (Criteria) this;
        }

        public Criteria andModIdNotIn(List<String> values) {
            addCriterion("MOD_ID not in", values, "modId");
            return (Criteria) this;
        }

        public Criteria andModIdBetween(String value1, String value2) {
            addCriterion("MOD_ID between", value1, value2, "modId");
            return (Criteria) this;
        }

        public Criteria andModIdNotBetween(String value1, String value2) {
            addCriterion("MOD_ID not between", value1, value2, "modId");
            return (Criteria) this;
        }

        public Criteria andModDateIsNull() {
            addCriterion("MOD_DATE is null");
            return (Criteria) this;
        }

        public Criteria andModDateIsNotNull() {
            addCriterion("MOD_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andModDateEqualTo(Date value) {
            addCriterionForJDBCDate("MOD_DATE =", value, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("MOD_DATE <>", value, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateGreaterThan(Date value) {
            addCriterionForJDBCDate("MOD_DATE >", value, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("MOD_DATE >=", value, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateLessThan(Date value) {
            addCriterionForJDBCDate("MOD_DATE <", value, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("MOD_DATE <=", value, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateIn(List<Date> values) {
            addCriterionForJDBCDate("MOD_DATE in", values, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("MOD_DATE not in", values, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("MOD_DATE between", value1, value2, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("MOD_DATE not between", value1, value2, "modDate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ADMS_CORS_MAJ_LANG
     *
     * @mbggenerated do_not_delete_during_merge Wed Sep 03 21:15:46 KST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ADMS_CORS_MAJ_LANG
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}