package com.apexsoft.ysprj.clerk.user.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AdmissionExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ADMS
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ADMS
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ADMS
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
     */
    public AdmissionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
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
     * This method corresponds to the database table ADMS
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMS
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ADMS
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
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

        public Criteria andEntrYearIsNull() {
            addCriterion("ENTR_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andEntrYearIsNotNull() {
            addCriterion("ENTR_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andEntrYearEqualTo(String value) {
            addCriterion("ENTR_YEAR =", value, "entrYear");
            return (Criteria) this;
        }

        public Criteria andEntrYearNotEqualTo(String value) {
            addCriterion("ENTR_YEAR <>", value, "entrYear");
            return (Criteria) this;
        }

        public Criteria andEntrYearGreaterThan(String value) {
            addCriterion("ENTR_YEAR >", value, "entrYear");
            return (Criteria) this;
        }

        public Criteria andEntrYearGreaterThanOrEqualTo(String value) {
            addCriterion("ENTR_YEAR >=", value, "entrYear");
            return (Criteria) this;
        }

        public Criteria andEntrYearLessThan(String value) {
            addCriterion("ENTR_YEAR <", value, "entrYear");
            return (Criteria) this;
        }

        public Criteria andEntrYearLessThanOrEqualTo(String value) {
            addCriterion("ENTR_YEAR <=", value, "entrYear");
            return (Criteria) this;
        }

        public Criteria andEntrYearLike(String value) {
            addCriterion("ENTR_YEAR like", value, "entrYear");
            return (Criteria) this;
        }

        public Criteria andEntrYearNotLike(String value) {
            addCriterion("ENTR_YEAR not like", value, "entrYear");
            return (Criteria) this;
        }

        public Criteria andEntrYearIn(List<String> values) {
            addCriterion("ENTR_YEAR in", values, "entrYear");
            return (Criteria) this;
        }

        public Criteria andEntrYearNotIn(List<String> values) {
            addCriterion("ENTR_YEAR not in", values, "entrYear");
            return (Criteria) this;
        }

        public Criteria andEntrYearBetween(String value1, String value2) {
            addCriterion("ENTR_YEAR between", value1, value2, "entrYear");
            return (Criteria) this;
        }

        public Criteria andEntrYearNotBetween(String value1, String value2) {
            addCriterion("ENTR_YEAR not between", value1, value2, "entrYear");
            return (Criteria) this;
        }

        public Criteria andAdmsTypeIsNull() {
            addCriterion("ADMS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andAdmsTypeIsNotNull() {
            addCriterion("ADMS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andAdmsTypeEqualTo(String value) {
            addCriterion("ADMS_TYPE =", value, "admsType");
            return (Criteria) this;
        }

        public Criteria andAdmsTypeNotEqualTo(String value) {
            addCriterion("ADMS_TYPE <>", value, "admsType");
            return (Criteria) this;
        }

        public Criteria andAdmsTypeGreaterThan(String value) {
            addCriterion("ADMS_TYPE >", value, "admsType");
            return (Criteria) this;
        }

        public Criteria andAdmsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ADMS_TYPE >=", value, "admsType");
            return (Criteria) this;
        }

        public Criteria andAdmsTypeLessThan(String value) {
            addCriterion("ADMS_TYPE <", value, "admsType");
            return (Criteria) this;
        }

        public Criteria andAdmsTypeLessThanOrEqualTo(String value) {
            addCriterion("ADMS_TYPE <=", value, "admsType");
            return (Criteria) this;
        }

        public Criteria andAdmsTypeLike(String value) {
            addCriterion("ADMS_TYPE like", value, "admsType");
            return (Criteria) this;
        }

        public Criteria andAdmsTypeNotLike(String value) {
            addCriterion("ADMS_TYPE not like", value, "admsType");
            return (Criteria) this;
        }

        public Criteria andAdmsTypeIn(List<String> values) {
            addCriterion("ADMS_TYPE in", values, "admsType");
            return (Criteria) this;
        }

        public Criteria andAdmsTypeNotIn(List<String> values) {
            addCriterion("ADMS_TYPE not in", values, "admsType");
            return (Criteria) this;
        }

        public Criteria andAdmsTypeBetween(String value1, String value2) {
            addCriterion("ADMS_TYPE between", value1, value2, "admsType");
            return (Criteria) this;
        }

        public Criteria andAdmsTypeNotBetween(String value1, String value2) {
            addCriterion("ADMS_TYPE not between", value1, value2, "admsType");
            return (Criteria) this;
        }

        public Criteria andAdmsDescIsNull() {
            addCriterion("ADMS_DESC is null");
            return (Criteria) this;
        }

        public Criteria andAdmsDescIsNotNull() {
            addCriterion("ADMS_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andAdmsDescEqualTo(String value) {
            addCriterion("ADMS_DESC =", value, "admsDesc");
            return (Criteria) this;
        }

        public Criteria andAdmsDescNotEqualTo(String value) {
            addCriterion("ADMS_DESC <>", value, "admsDesc");
            return (Criteria) this;
        }

        public Criteria andAdmsDescGreaterThan(String value) {
            addCriterion("ADMS_DESC >", value, "admsDesc");
            return (Criteria) this;
        }

        public Criteria andAdmsDescGreaterThanOrEqualTo(String value) {
            addCriterion("ADMS_DESC >=", value, "admsDesc");
            return (Criteria) this;
        }

        public Criteria andAdmsDescLessThan(String value) {
            addCriterion("ADMS_DESC <", value, "admsDesc");
            return (Criteria) this;
        }

        public Criteria andAdmsDescLessThanOrEqualTo(String value) {
            addCriterion("ADMS_DESC <=", value, "admsDesc");
            return (Criteria) this;
        }

        public Criteria andAdmsDescLike(String value) {
            addCriterion("ADMS_DESC like", value, "admsDesc");
            return (Criteria) this;
        }

        public Criteria andAdmsDescNotLike(String value) {
            addCriterion("ADMS_DESC not like", value, "admsDesc");
            return (Criteria) this;
        }

        public Criteria andAdmsDescIn(List<String> values) {
            addCriterion("ADMS_DESC in", values, "admsDesc");
            return (Criteria) this;
        }

        public Criteria andAdmsDescNotIn(List<String> values) {
            addCriterion("ADMS_DESC not in", values, "admsDesc");
            return (Criteria) this;
        }

        public Criteria andAdmsDescBetween(String value1, String value2) {
            addCriterion("ADMS_DESC between", value1, value2, "admsDesc");
            return (Criteria) this;
        }

        public Criteria andAdmsDescNotBetween(String value1, String value2) {
            addCriterion("ADMS_DESC not between", value1, value2, "admsDesc");
            return (Criteria) this;
        }

        public Criteria andAdmsStsIsNull() {
            addCriterion("ADMS_STS is null");
            return (Criteria) this;
        }

        public Criteria andAdmsStsIsNotNull() {
            addCriterion("ADMS_STS is not null");
            return (Criteria) this;
        }

        public Criteria andAdmsStsEqualTo(String value) {
            addCriterion("ADMS_STS =", value, "admsSts");
            return (Criteria) this;
        }

        public Criteria andAdmsStsNotEqualTo(String value) {
            addCriterion("ADMS_STS <>", value, "admsSts");
            return (Criteria) this;
        }

        public Criteria andAdmsStsGreaterThan(String value) {
            addCriterion("ADMS_STS >", value, "admsSts");
            return (Criteria) this;
        }

        public Criteria andAdmsStsGreaterThanOrEqualTo(String value) {
            addCriterion("ADMS_STS >=", value, "admsSts");
            return (Criteria) this;
        }

        public Criteria andAdmsStsLessThan(String value) {
            addCriterion("ADMS_STS <", value, "admsSts");
            return (Criteria) this;
        }

        public Criteria andAdmsStsLessThanOrEqualTo(String value) {
            addCriterion("ADMS_STS <=", value, "admsSts");
            return (Criteria) this;
        }

        public Criteria andAdmsStsLike(String value) {
            addCriterion("ADMS_STS like", value, "admsSts");
            return (Criteria) this;
        }

        public Criteria andAdmsStsNotLike(String value) {
            addCriterion("ADMS_STS not like", value, "admsSts");
            return (Criteria) this;
        }

        public Criteria andAdmsStsIn(List<String> values) {
            addCriterion("ADMS_STS in", values, "admsSts");
            return (Criteria) this;
        }

        public Criteria andAdmsStsNotIn(List<String> values) {
            addCriterion("ADMS_STS not in", values, "admsSts");
            return (Criteria) this;
        }

        public Criteria andAdmsStsBetween(String value1, String value2) {
            addCriterion("ADMS_STS between", value1, value2, "admsSts");
            return (Criteria) this;
        }

        public Criteria andAdmsStsNotBetween(String value1, String value2) {
            addCriterion("ADMS_STS not between", value1, value2, "admsSts");
            return (Criteria) this;
        }

        public Criteria andCloseDateIsNull() {
            addCriterion("CLOSE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCloseDateIsNotNull() {
            addCriterion("CLOSE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCloseDateEqualTo(Date value) {
            addCriterionForJDBCDate("CLOSE_DATE =", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("CLOSE_DATE <>", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateGreaterThan(Date value) {
            addCriterionForJDBCDate("CLOSE_DATE >", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CLOSE_DATE >=", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateLessThan(Date value) {
            addCriterionForJDBCDate("CLOSE_DATE <", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CLOSE_DATE <=", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateIn(List<Date> values) {
            addCriterionForJDBCDate("CLOSE_DATE in", values, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("CLOSE_DATE not in", values, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CLOSE_DATE between", value1, value2, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CLOSE_DATE not between", value1, value2, "closeDate");
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
     * This class corresponds to the database table ADMS
     *
     * @mbggenerated do_not_delete_during_merge Fri Sep 12 19:41:34 KST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ADMS
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
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