package com.apexsoft.ysprj.applicants.common.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommonCodeExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMM_CODE
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMM_CODE
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMM_CODE
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMM_CODE
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    public CommonCodeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMM_CODE
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMM_CODE
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMM_CODE
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMM_CODE
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMM_CODE
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMM_CODE
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMM_CODE
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMM_CODE
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
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
     * This method corresponds to the database table COMM_CODE
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMM_CODE
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table COMM_CODE
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
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

        public Criteria andCodeGrpIsNull() {
            addCriterion("CODE_GRP is null");
            return (Criteria) this;
        }

        public Criteria andCodeGrpIsNotNull() {
            addCriterion("CODE_GRP is not null");
            return (Criteria) this;
        }

        public Criteria andCodeGrpEqualTo(String value) {
            addCriterion("CODE_GRP =", value, "codeGrp");
            return (Criteria) this;
        }

        public Criteria andCodeGrpNotEqualTo(String value) {
            addCriterion("CODE_GRP <>", value, "codeGrp");
            return (Criteria) this;
        }

        public Criteria andCodeGrpGreaterThan(String value) {
            addCriterion("CODE_GRP >", value, "codeGrp");
            return (Criteria) this;
        }

        public Criteria andCodeGrpGreaterThanOrEqualTo(String value) {
            addCriterion("CODE_GRP >=", value, "codeGrp");
            return (Criteria) this;
        }

        public Criteria andCodeGrpLessThan(String value) {
            addCriterion("CODE_GRP <", value, "codeGrp");
            return (Criteria) this;
        }

        public Criteria andCodeGrpLessThanOrEqualTo(String value) {
            addCriterion("CODE_GRP <=", value, "codeGrp");
            return (Criteria) this;
        }

        public Criteria andCodeGrpLike(String value) {
            addCriterion("CODE_GRP like", value, "codeGrp");
            return (Criteria) this;
        }

        public Criteria andCodeGrpNotLike(String value) {
            addCriterion("CODE_GRP not like", value, "codeGrp");
            return (Criteria) this;
        }

        public Criteria andCodeGrpIn(List<String> values) {
            addCriterion("CODE_GRP in", values, "codeGrp");
            return (Criteria) this;
        }

        public Criteria andCodeGrpNotIn(List<String> values) {
            addCriterion("CODE_GRP not in", values, "codeGrp");
            return (Criteria) this;
        }

        public Criteria andCodeGrpBetween(String value1, String value2) {
            addCriterion("CODE_GRP between", value1, value2, "codeGrp");
            return (Criteria) this;
        }

        public Criteria andCodeGrpNotBetween(String value1, String value2) {
            addCriterion("CODE_GRP not between", value1, value2, "codeGrp");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("CODE is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("CODE =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("CODE <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("CODE >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CODE >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("CODE <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("CODE <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("CODE like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("CODE not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("CODE in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("CODE not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("CODE between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("CODE not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeValIsNull() {
            addCriterion("CODE_VAL is null");
            return (Criteria) this;
        }

        public Criteria andCodeValIsNotNull() {
            addCriterion("CODE_VAL is not null");
            return (Criteria) this;
        }

        public Criteria andCodeValEqualTo(String value) {
            addCriterion("CODE_VAL =", value, "codeVal");
            return (Criteria) this;
        }

        public Criteria andCodeValNotEqualTo(String value) {
            addCriterion("CODE_VAL <>", value, "codeVal");
            return (Criteria) this;
        }

        public Criteria andCodeValGreaterThan(String value) {
            addCriterion("CODE_VAL >", value, "codeVal");
            return (Criteria) this;
        }

        public Criteria andCodeValGreaterThanOrEqualTo(String value) {
            addCriterion("CODE_VAL >=", value, "codeVal");
            return (Criteria) this;
        }

        public Criteria andCodeValLessThan(String value) {
            addCriterion("CODE_VAL <", value, "codeVal");
            return (Criteria) this;
        }

        public Criteria andCodeValLessThanOrEqualTo(String value) {
            addCriterion("CODE_VAL <=", value, "codeVal");
            return (Criteria) this;
        }

        public Criteria andCodeValLike(String value) {
            addCriterion("CODE_VAL like", value, "codeVal");
            return (Criteria) this;
        }

        public Criteria andCodeValNotLike(String value) {
            addCriterion("CODE_VAL not like", value, "codeVal");
            return (Criteria) this;
        }

        public Criteria andCodeValIn(List<String> values) {
            addCriterion("CODE_VAL in", values, "codeVal");
            return (Criteria) this;
        }

        public Criteria andCodeValNotIn(List<String> values) {
            addCriterion("CODE_VAL not in", values, "codeVal");
            return (Criteria) this;
        }

        public Criteria andCodeValBetween(String value1, String value2) {
            addCriterion("CODE_VAL between", value1, value2, "codeVal");
            return (Criteria) this;
        }

        public Criteria andCodeValNotBetween(String value1, String value2) {
            addCriterion("CODE_VAL not between", value1, value2, "codeVal");
            return (Criteria) this;
        }

        public Criteria andCodeValXxenIsNull() {
            addCriterion("CODE_VAL_XXEN is null");
            return (Criteria) this;
        }

        public Criteria andCodeValXxenIsNotNull() {
            addCriterion("CODE_VAL_XXEN is not null");
            return (Criteria) this;
        }

        public Criteria andCodeValXxenEqualTo(String value) {
            addCriterion("CODE_VAL_XXEN =", value, "codeValXxen");
            return (Criteria) this;
        }

        public Criteria andCodeValXxenNotEqualTo(String value) {
            addCriterion("CODE_VAL_XXEN <>", value, "codeValXxen");
            return (Criteria) this;
        }

        public Criteria andCodeValXxenGreaterThan(String value) {
            addCriterion("CODE_VAL_XXEN >", value, "codeValXxen");
            return (Criteria) this;
        }

        public Criteria andCodeValXxenGreaterThanOrEqualTo(String value) {
            addCriterion("CODE_VAL_XXEN >=", value, "codeValXxen");
            return (Criteria) this;
        }

        public Criteria andCodeValXxenLessThan(String value) {
            addCriterion("CODE_VAL_XXEN <", value, "codeValXxen");
            return (Criteria) this;
        }

        public Criteria andCodeValXxenLessThanOrEqualTo(String value) {
            addCriterion("CODE_VAL_XXEN <=", value, "codeValXxen");
            return (Criteria) this;
        }

        public Criteria andCodeValXxenLike(String value) {
            addCriterion("CODE_VAL_XXEN like", value, "codeValXxen");
            return (Criteria) this;
        }

        public Criteria andCodeValXxenNotLike(String value) {
            addCriterion("CODE_VAL_XXEN not like", value, "codeValXxen");
            return (Criteria) this;
        }

        public Criteria andCodeValXxenIn(List<String> values) {
            addCriterion("CODE_VAL_XXEN in", values, "codeValXxen");
            return (Criteria) this;
        }

        public Criteria andCodeValXxenNotIn(List<String> values) {
            addCriterion("CODE_VAL_XXEN not in", values, "codeValXxen");
            return (Criteria) this;
        }

        public Criteria andCodeValXxenBetween(String value1, String value2) {
            addCriterion("CODE_VAL_XXEN between", value1, value2, "codeValXxen");
            return (Criteria) this;
        }

        public Criteria andCodeValXxenNotBetween(String value1, String value2) {
            addCriterion("CODE_VAL_XXEN not between", value1, value2, "codeValXxen");
            return (Criteria) this;
        }

        public Criteria andUseYnIsNull() {
            addCriterion("USE_YN is null");
            return (Criteria) this;
        }

        public Criteria andUseYnIsNotNull() {
            addCriterion("USE_YN is not null");
            return (Criteria) this;
        }

        public Criteria andUseYnEqualTo(String value) {
            addCriterion("USE_YN =", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnNotEqualTo(String value) {
            addCriterion("USE_YN <>", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnGreaterThan(String value) {
            addCriterion("USE_YN >", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnGreaterThanOrEqualTo(String value) {
            addCriterion("USE_YN >=", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnLessThan(String value) {
            addCriterion("USE_YN <", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnLessThanOrEqualTo(String value) {
            addCriterion("USE_YN <=", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnLike(String value) {
            addCriterion("USE_YN like", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnNotLike(String value) {
            addCriterion("USE_YN not like", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnIn(List<String> values) {
            addCriterion("USE_YN in", values, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnNotIn(List<String> values) {
            addCriterion("USE_YN not in", values, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnBetween(String value1, String value2) {
            addCriterion("USE_YN between", value1, value2, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnNotBetween(String value1, String value2) {
            addCriterion("USE_YN not between", value1, value2, "useYn");
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
            addCriterion("CRE_DATE =", value, "creDate");
            return (Criteria) this;
        }

        public Criteria andCreDateNotEqualTo(Date value) {
            addCriterion("CRE_DATE <>", value, "creDate");
            return (Criteria) this;
        }

        public Criteria andCreDateGreaterThan(Date value) {
            addCriterion("CRE_DATE >", value, "creDate");
            return (Criteria) this;
        }

        public Criteria andCreDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CRE_DATE >=", value, "creDate");
            return (Criteria) this;
        }

        public Criteria andCreDateLessThan(Date value) {
            addCriterion("CRE_DATE <", value, "creDate");
            return (Criteria) this;
        }

        public Criteria andCreDateLessThanOrEqualTo(Date value) {
            addCriterion("CRE_DATE <=", value, "creDate");
            return (Criteria) this;
        }

        public Criteria andCreDateIn(List<Date> values) {
            addCriterion("CRE_DATE in", values, "creDate");
            return (Criteria) this;
        }

        public Criteria andCreDateNotIn(List<Date> values) {
            addCriterion("CRE_DATE not in", values, "creDate");
            return (Criteria) this;
        }

        public Criteria andCreDateBetween(Date value1, Date value2) {
            addCriterion("CRE_DATE between", value1, value2, "creDate");
            return (Criteria) this;
        }

        public Criteria andCreDateNotBetween(Date value1, Date value2) {
            addCriterion("CRE_DATE not between", value1, value2, "creDate");
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
            addCriterion("MOD_DATE =", value, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateNotEqualTo(Date value) {
            addCriterion("MOD_DATE <>", value, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateGreaterThan(Date value) {
            addCriterion("MOD_DATE >", value, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateGreaterThanOrEqualTo(Date value) {
            addCriterion("MOD_DATE >=", value, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateLessThan(Date value) {
            addCriterion("MOD_DATE <", value, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateLessThanOrEqualTo(Date value) {
            addCriterion("MOD_DATE <=", value, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateIn(List<Date> values) {
            addCriterion("MOD_DATE in", values, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateNotIn(List<Date> values) {
            addCriterion("MOD_DATE not in", values, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateBetween(Date value1, Date value2) {
            addCriterion("MOD_DATE between", value1, value2, "modDate");
            return (Criteria) this;
        }

        public Criteria andModDateNotBetween(Date value1, Date value2) {
            addCriterion("MOD_DATE not between", value1, value2, "modDate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table COMM_CODE
     *
     * @mbggenerated do_not_delete_during_merge Thu Sep 18 02:12:42 KST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table COMM_CODE
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
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