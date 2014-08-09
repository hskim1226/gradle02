package com.apexsoft.framework.common.vo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * storeplatform 공통 ValueObject 확장 class.
 * 
 * Updated on : 2013-10-28 Updated by : 김상호, 에이엔비
 */
@SuppressWarnings("serial")
public abstract class CommonInfo implements Serializable {

	/**
	 * Create Valid Interface Class.
	 * 
	 */
	public interface Create {
	}

	/**
	 * Modify Valid Interface Class.
	 * 
	 */
	public interface Modify {
	}

	/**
	 * View Valid Interface Class.
	 * 
	 */
	public interface View {
	}

	/**
	 * 객체 정보 확인.
	 * 
	 * @return string
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * 객체 비교.
	 * 
	 * @param obj
	 *            obj
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
