package com.apexsoft.ysprj.applicants.admission.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AdmissionCourseExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ADMS_CORS
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ADMS_CORS
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ADMS_CORS
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS_CORS
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    public AdmissionCourseExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS_CORS
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS_CORS
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS_CORS
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS_CORS
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS_CORS
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS_CORS
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS_CORS
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
     * This method corresponds to the database table ADMS_CORS
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
     * This method corresponds to the database table ADMS_CORS
     *
     * @mbggenerated Wed Sep 03 21:15:46 KST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS_CORS
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
     * This class corresponds to the database table ADMS_CORS
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

        public Criteria andAdmsNoIsNull() {
            addCriterion("ADMS_NO is null");
            return (Criteria) this;
        }

        public Criteria andAdmsNoIsNotNull() {
            addCriterion("ADMS_NO is not null");
            return (Criteria) this;
        }

        public Criteria andAdmsNoEqualTo(String value) {
            addCriterion("ADMS_NO =", value, "admsNo");
            return (Criteria) this;
        }

        public Criteria andAdmsNoNotEqualTo(String value) {
            addCriterion("ADMS_NO <>", value, "admsNo");
            return (Criteria) this;
        }

        public Criteria andAdmsNoGreaterThan(String value) {
            addCriterion("ADMS_NO >", value, "admsNo");
            return (Criteria) this;
        }

        public Criteria andAdmsNoGreaterThanOrEqualTo(String value) {
            addCriterion("ADMS_NO >=", value, "admsNo");
            return (Criteria) this;
        }

        public Criteria andAdmsNoLessThan(String value) {
            addCriterion("ADMS_NO <", value, "admsNo");
            return (Criteria) this;
        }

        public Criteria andAdmsNoLessThanOrEqualTo(String value) {
            addCriterion("ADMS_NO <=", value, "admsNo");
            return (Criteria) this;
        }

        public Criteria andAdmsNoLike(String value) {
            addCriterion("ADMS_NO like", value, "admsNo");
            return (Criteria) this;
        }

        public Criteria andAdmsNoNotLike(String value) {
            addCriterion("ADMS_NO not like", value, "admsNo");
            return (Criteria) this;
        }

        public Criteria andAdmsNoIn(List<String> values) {
            addCriterion("ADMS_NO in", values, "admsNo");
            return (Criteria) this;
        }

        public Criteria andAdmsNoNotIn(List<String> values) {
            addCriterion("ADMS_NO not in", values, "admsNo");
            return (Criteria) this;
        }

        public Criteria andAdmsNoBetween(String value1, String value2) {
            addCriterion("ADMS_NO between", value1, value2, "admsNo");
            return (Criteria) this;
        }

        public Criteria andAdmsNoNotBetween(String value1, String value2) {
            addCriterion("ADMS_NO not between", value1, value2, "admsNo");
            return (Criteria) this;
        }

        public Criteria andDeptCodeIsNull() {
            addCriterion("DEPT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDeptCodeIsNotNull() {
            addCriterion("DEPT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDeptCodeEqualTo(String value) {
            addCriterion("DEPT_CODE =", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeNotEqualTo(String value) {
            addCriterion("DEPT_CODE <>", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeGreaterThan(String value) {
            addCriterion("DEPT_CODE >", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_CODE >=", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeLessThan(String value) {
            addCriterion("DEPT_CODE <", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeLessThanOrEqualTo(String value) {
            addCriterion("DEPT_CODE <=", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeLike(String value) {
            addCriterion("DEPT_CODE like", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeNotLike(String value) {
            addCriterion("DEPT_CODE not like", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeIn(List<String> values) {
            addCriterion("DEPT_CODE in", values, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeNotIn(List<String> values) {
            addCriterion("DEPT_CODE not in", values, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeBetween(String value1, String value2) {
            addCriterion("DEPT_CODE between", value1, value2, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeNotBetween(String value1, String value2) {
            addCriterion("DEPT_CODE not between", value1, value2, "deptCode");
            return (Criteria) this;
        }

        public Criteria andAriInstCodeIsNull() {
            addCriterion("ARI_INST_CODE is null");
            return (Criteria) this;
        }

        public Criteria andAriInstCodeIsNotNull() {
            addCriterion("ARI_INST_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andAriInstCodeEqualTo(String value) {
            addCriterion("ARI_INST_CODE =", value, "ariInstCode");
            return (Criteria) this;
        }

        public Criteria andAriInstCodeNotEqualTo(String value) {
            addCriterion("ARI_INST_CODE <>", value, "ariInstCode");
            return (Criteria) this;
        }

        public Criteria andAriInstCodeGreaterThan(String value) {
            addCriterion("ARI_INST_CODE >", value, "ariInstCode");
            return (Criteria) this;
        }

        public Criteria andAriInstCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ARI_INST_CODE >=", value, "ariInstCode");
            return (Criteria) this;
        }

        public Criteria andAriInstCodeLessThan(String value) {
            addCriterion("ARI_INST_CODE <", value, "ariInstCode");
            return (Criteria) this;
        }

        public Criteria andAriInstCodeLessThanOrEqualTo(String value) {
            addCriterion("ARI_INST_CODE <=", value, "ariInstCode");
            return (Criteria) this;
        }

        public Criteria andAriInstCodeLike(String value) {
            addCriterion("ARI_INST_CODE like", value, "ariInstCode");
            return (Criteria) this;
        }

        public Criteria andAriInstCodeNotLike(String value) {
            addCriterion("ARI_INST_CODE not like", value, "ariInstCode");
            return (Criteria) this;
        }

        public Criteria andAriInstCodeIn(List<String> values) {
            addCriterion("ARI_INST_CODE in", values, "ariInstCode");
            return (Criteria) this;
        }

        public Criteria andAriInstCodeNotIn(List<String> values) {
            addCriterion("ARI_INST_CODE not in", values, "ariInstCode");
            return (Criteria) this;
        }

        public Criteria andAriInstCodeBetween(String value1, String value2) {
            addCriterion("ARI_INST_CODE between", value1, value2, "ariInstCode");
            return (Criteria) this;
        }

        public Criteria andAriInstCodeNotBetween(String value1, String value2) {
            addCriterion("ARI_INST_CODE not between", value1, value2, "ariInstCode");
            return (Criteria) this;
        }

        public Criteria andCorsTypeCodeIsNull() {
            addCriterion("CORS_TYPE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCorsTypeCodeIsNotNull() {
            addCriterion("CORS_TYPE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCorsTypeCodeEqualTo(String value) {
            addCriterion("CORS_TYPE_CODE =", value, "corsTypeCode");
            return (Criteria) this;
        }

        public Criteria andCorsTypeCodeNotEqualTo(String value) {
            addCriterion("CORS_TYPE_CODE <>", value, "corsTypeCode");
            return (Criteria) this;
        }

        public Criteria andCorsTypeCodeGreaterThan(String value) {
            addCriterion("CORS_TYPE_CODE >", value, "corsTypeCode");
            return (Criteria) this;
        }

        public Criteria andCorsTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CORS_TYPE_CODE >=", value, "corsTypeCode");
            return (Criteria) this;
        }

        public Criteria andCorsTypeCodeLessThan(String value) {
            addCriterion("CORS_TYPE_CODE <", value, "corsTypeCode");
            return (Criteria) this;
        }

        public Criteria andCorsTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("CORS_TYPE_CODE <=", value, "corsTypeCode");
            return (Criteria) this;
        }

        public Criteria andCorsTypeCodeLike(String value) {
            addCriterion("CORS_TYPE_CODE like", value, "corsTypeCode");
            return (Criteria) this;
        }

        public Criteria andCorsTypeCodeNotLike(String value) {
            addCriterion("CORS_TYPE_CODE not like", value, "corsTypeCode");
            return (Criteria) this;
        }

        public Criteria andCorsTypeCodeIn(List<String> values) {
            addCriterion("CORS_TYPE_CODE in", values, "corsTypeCode");
            return (Criteria) this;
        }

        public Criteria andCorsTypeCodeNotIn(List<String> values) {
            addCriterion("CORS_TYPE_CODE not in", values, "corsTypeCode");
            return (Criteria) this;
        }

        public Criteria andCorsTypeCodeBetween(String value1, String value2) {
            addCriterion("CORS_TYPE_CODE between", value1, value2, "corsTypeCode");
            return (Criteria) this;
        }

        public Criteria andCorsTypeCodeNotBetween(String value1, String value2) {
            addCriterion("CORS_TYPE_CODE not between", value1, value2, "corsTypeCode");
            return (Criteria) this;
        }

        public Criteria andAdmsFeeIsNull() {
            addCriterion("ADMS_FEE is null");
            return (Criteria) this;
        }

        public Criteria andAdmsFeeIsNotNull() {
            addCriterion("ADMS_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andAdmsFeeEqualTo(Integer value) {
            addCriterion("ADMS_FEE =", value, "admsFee");
            return (Criteria) this;
        }

        public Criteria andAdmsFeeNotEqualTo(Integer value) {
            addCriterion("ADMS_FEE <>", value, "admsFee");
            return (Criteria) this;
        }

        public Criteria andAdmsFeeGreaterThan(Integer value) {
            addCriterion("ADMS_FEE >", value, "admsFee");
            return (Criteria) this;
        }

        public Criteria andAdmsFeeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ADMS_FEE >=", value, "admsFee");
            return (Criteria) this;
        }

        public Criteria andAdmsFeeLessThan(Integer value) {
            addCriterion("ADMS_FEE <", value, "admsFee");
            return (Criteria) this;
        }

        public Criteria andAdmsFeeLessThanOrEqualTo(Integer value) {
            addCriterion("ADMS_FEE <=", value, "admsFee");
            return (Criteria) this;
        }

        public Criteria andAdmsFeeIn(List<Integer> values) {
            addCriterion("ADMS_FEE in", values, "admsFee");
            return (Criteria) this;
        }

        public Criteria andAdmsFeeNotIn(List<Integer> values) {
            addCriterion("ADMS_FEE not in", values, "admsFee");
            return (Criteria) this;
        }

        public Criteria andAdmsFeeBetween(Integer value1, Integer value2) {
            addCriterion("ADMS_FEE between", value1, value2, "admsFee");
            return (Criteria) this;
        }

        public Criteria andAdmsFeeNotBetween(Integer value1, Integer value2) {
            addCriterion("ADMS_FEE not between", value1, value2, "admsFee");
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
     * This class corresponds to the database table ADMS_CORS
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
     * This class corresponds to the database table ADMS_CORS
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