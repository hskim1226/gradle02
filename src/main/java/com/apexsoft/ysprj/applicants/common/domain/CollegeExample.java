package com.apexsoft.ysprj.applicants.common.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CollegeExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COLL
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COLL
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COLL
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COLL
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    public CollegeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COLL
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COLL
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COLL
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COLL
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COLL
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COLL
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COLL
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
     * This method corresponds to the database table COLL
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
     * This method corresponds to the database table COLL
     *
     * @mbggenerated Thu Sep 18 02:12:42 KST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COLL
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
     * This class corresponds to the database table COLL
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

        public Criteria andCollCodeIsNull() {
            addCriterion("COLL_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCollCodeIsNotNull() {
            addCriterion("COLL_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCollCodeEqualTo(String value) {
            addCriterion("COLL_CODE =", value, "collCode");
            return (Criteria) this;
        }

        public Criteria andCollCodeNotEqualTo(String value) {
            addCriterion("COLL_CODE <>", value, "collCode");
            return (Criteria) this;
        }

        public Criteria andCollCodeGreaterThan(String value) {
            addCriterion("COLL_CODE >", value, "collCode");
            return (Criteria) this;
        }

        public Criteria andCollCodeGreaterThanOrEqualTo(String value) {
            addCriterion("COLL_CODE >=", value, "collCode");
            return (Criteria) this;
        }

        public Criteria andCollCodeLessThan(String value) {
            addCriterion("COLL_CODE <", value, "collCode");
            return (Criteria) this;
        }

        public Criteria andCollCodeLessThanOrEqualTo(String value) {
            addCriterion("COLL_CODE <=", value, "collCode");
            return (Criteria) this;
        }

        public Criteria andCollCodeLike(String value) {
            addCriterion("COLL_CODE like", value, "collCode");
            return (Criteria) this;
        }

        public Criteria andCollCodeNotLike(String value) {
            addCriterion("COLL_CODE not like", value, "collCode");
            return (Criteria) this;
        }

        public Criteria andCollCodeIn(List<String> values) {
            addCriterion("COLL_CODE in", values, "collCode");
            return (Criteria) this;
        }

        public Criteria andCollCodeNotIn(List<String> values) {
            addCriterion("COLL_CODE not in", values, "collCode");
            return (Criteria) this;
        }

        public Criteria andCollCodeBetween(String value1, String value2) {
            addCriterion("COLL_CODE between", value1, value2, "collCode");
            return (Criteria) this;
        }

        public Criteria andCollCodeNotBetween(String value1, String value2) {
            addCriterion("COLL_CODE not between", value1, value2, "collCode");
            return (Criteria) this;
        }

        public Criteria andCollNameIsNull() {
            addCriterion("COLL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCollNameIsNotNull() {
            addCriterion("COLL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCollNameEqualTo(String value) {
            addCriterion("COLL_NAME =", value, "collName");
            return (Criteria) this;
        }

        public Criteria andCollNameNotEqualTo(String value) {
            addCriterion("COLL_NAME <>", value, "collName");
            return (Criteria) this;
        }

        public Criteria andCollNameGreaterThan(String value) {
            addCriterion("COLL_NAME >", value, "collName");
            return (Criteria) this;
        }

        public Criteria andCollNameGreaterThanOrEqualTo(String value) {
            addCriterion("COLL_NAME >=", value, "collName");
            return (Criteria) this;
        }

        public Criteria andCollNameLessThan(String value) {
            addCriterion("COLL_NAME <", value, "collName");
            return (Criteria) this;
        }

        public Criteria andCollNameLessThanOrEqualTo(String value) {
            addCriterion("COLL_NAME <=", value, "collName");
            return (Criteria) this;
        }

        public Criteria andCollNameLike(String value) {
            addCriterion("COLL_NAME like", value, "collName");
            return (Criteria) this;
        }

        public Criteria andCollNameNotLike(String value) {
            addCriterion("COLL_NAME not like", value, "collName");
            return (Criteria) this;
        }

        public Criteria andCollNameIn(List<String> values) {
            addCriterion("COLL_NAME in", values, "collName");
            return (Criteria) this;
        }

        public Criteria andCollNameNotIn(List<String> values) {
            addCriterion("COLL_NAME not in", values, "collName");
            return (Criteria) this;
        }

        public Criteria andCollNameBetween(String value1, String value2) {
            addCriterion("COLL_NAME between", value1, value2, "collName");
            return (Criteria) this;
        }

        public Criteria andCollNameNotBetween(String value1, String value2) {
            addCriterion("COLL_NAME not between", value1, value2, "collName");
            return (Criteria) this;
        }

        public Criteria andCollNameXxenIsNull() {
            addCriterion("COLL_NAME_XXEN is null");
            return (Criteria) this;
        }

        public Criteria andCollNameXxenIsNotNull() {
            addCriterion("COLL_NAME_XXEN is not null");
            return (Criteria) this;
        }

        public Criteria andCollNameXxenEqualTo(String value) {
            addCriterion("COLL_NAME_XXEN =", value, "collNameXxen");
            return (Criteria) this;
        }

        public Criteria andCollNameXxenNotEqualTo(String value) {
            addCriterion("COLL_NAME_XXEN <>", value, "collNameXxen");
            return (Criteria) this;
        }

        public Criteria andCollNameXxenGreaterThan(String value) {
            addCriterion("COLL_NAME_XXEN >", value, "collNameXxen");
            return (Criteria) this;
        }

        public Criteria andCollNameXxenGreaterThanOrEqualTo(String value) {
            addCriterion("COLL_NAME_XXEN >=", value, "collNameXxen");
            return (Criteria) this;
        }

        public Criteria andCollNameXxenLessThan(String value) {
            addCriterion("COLL_NAME_XXEN <", value, "collNameXxen");
            return (Criteria) this;
        }

        public Criteria andCollNameXxenLessThanOrEqualTo(String value) {
            addCriterion("COLL_NAME_XXEN <=", value, "collNameXxen");
            return (Criteria) this;
        }

        public Criteria andCollNameXxenLike(String value) {
            addCriterion("COLL_NAME_XXEN like", value, "collNameXxen");
            return (Criteria) this;
        }

        public Criteria andCollNameXxenNotLike(String value) {
            addCriterion("COLL_NAME_XXEN not like", value, "collNameXxen");
            return (Criteria) this;
        }

        public Criteria andCollNameXxenIn(List<String> values) {
            addCriterion("COLL_NAME_XXEN in", values, "collNameXxen");
            return (Criteria) this;
        }

        public Criteria andCollNameXxenNotIn(List<String> values) {
            addCriterion("COLL_NAME_XXEN not in", values, "collNameXxen");
            return (Criteria) this;
        }

        public Criteria andCollNameXxenBetween(String value1, String value2) {
            addCriterion("COLL_NAME_XXEN between", value1, value2, "collNameXxen");
            return (Criteria) this;
        }

        public Criteria andCollNameXxenNotBetween(String value1, String value2) {
            addCriterion("COLL_NAME_XXEN not between", value1, value2, "collNameXxen");
            return (Criteria) this;
        }

        public Criteria andCampCodeIsNull() {
            addCriterion("CAMP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCampCodeIsNotNull() {
            addCriterion("CAMP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCampCodeEqualTo(String value) {
            addCriterion("CAMP_CODE =", value, "campCode");
            return (Criteria) this;
        }

        public Criteria andCampCodeNotEqualTo(String value) {
            addCriterion("CAMP_CODE <>", value, "campCode");
            return (Criteria) this;
        }

        public Criteria andCampCodeGreaterThan(String value) {
            addCriterion("CAMP_CODE >", value, "campCode");
            return (Criteria) this;
        }

        public Criteria andCampCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CAMP_CODE >=", value, "campCode");
            return (Criteria) this;
        }

        public Criteria andCampCodeLessThan(String value) {
            addCriterion("CAMP_CODE <", value, "campCode");
            return (Criteria) this;
        }

        public Criteria andCampCodeLessThanOrEqualTo(String value) {
            addCriterion("CAMP_CODE <=", value, "campCode");
            return (Criteria) this;
        }

        public Criteria andCampCodeLike(String value) {
            addCriterion("CAMP_CODE like", value, "campCode");
            return (Criteria) this;
        }

        public Criteria andCampCodeNotLike(String value) {
            addCriterion("CAMP_CODE not like", value, "campCode");
            return (Criteria) this;
        }

        public Criteria andCampCodeIn(List<String> values) {
            addCriterion("CAMP_CODE in", values, "campCode");
            return (Criteria) this;
        }

        public Criteria andCampCodeNotIn(List<String> values) {
            addCriterion("CAMP_CODE not in", values, "campCode");
            return (Criteria) this;
        }

        public Criteria andCampCodeBetween(String value1, String value2) {
            addCriterion("CAMP_CODE between", value1, value2, "campCode");
            return (Criteria) this;
        }

        public Criteria andCampCodeNotBetween(String value1, String value2) {
            addCriterion("CAMP_CODE not between", value1, value2, "campCode");
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
     * This class corresponds to the database table COLL
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
     * This class corresponds to the database table COLL
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