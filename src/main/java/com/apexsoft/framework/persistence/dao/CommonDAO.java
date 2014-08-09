package com.apexsoft.framework.persistence.dao;

import com.apexsoft.framework.persistence.dao.handler.StreamHandler;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.framework.persistence.dao.page.PageStatement;

import java.util.List;
import java.util.Map;

/**
 * 내부적으로 초기화하여 지원하는 편의 클래스. 배치 트랜잭션 모드를 지원하는 배치 연산 추가 제공 .
 */
public interface CommonDAO {

	/**
	 * 단건 조회(int) 연산을 수행한다.
	 * 
	 * @param statementId
	 *            MyBatis namespace + statementId
	 * @param parameter
	 *            입력 parameter
	 * 
	 * @return 조회된 데이터
	 */
	Integer queryForInt(String statementId, Object parameter);

	/**
	 * 단건 조회(long) 연산을 수행한다.
	 * 
	 * @param statementId
	 *            MyBatis namespace + statementId
	 * @param parameter
	 *            입력 parameter
	 * 
	 * @return 조회된 데이터
	 */
	Long queryForLong(String statementId, Object parameter);

	/**
	 * 단건 조회 연산을 수행한다.
	 * 
	 * @param <T>
	 *            generic type class
	 * @param statementId
	 *            MyBatis namespace + statementId
	 * @param parameter
	 *            입력 parameter
	 * @param clazz
	 *            generic type class
	 * 
	 * @return 조회된 데이터 (단건) or null
	 */
	<T> T queryForObject(String statementId, Object parameter, Class<T> clazz);

	/**
	 * 단건 조회 연산을 수행한다.
	 * 
	 * @param statementId
	 *            MyBatis namespace + statementId
	 * @param parameter
	 *            입력 parameter
	 * 
	 * @return 조회된 데이터 (단건) or null
	 */
	Object queryForObject(String statementId, Object parameter);

	/**
	 * 단건 조회 연산을 수행한다.
	 * 
	 * @param statementId
	 *            MyBatis namespace + statementId
	 * @param mapKey
	 *            resultMap key
	 * @param parameter
	 *            입력 parameter
	 * 
	 * @return 조회된 데이터 (단건) or null
	 */
	Map<?, ?> queryForMap(String statementId, String mapKey, Object parameter);

	/**
	 * 다건 조회 연산을 수행한다.
	 * 
	 * @param <T>
	 *            generic type class
	 * @param statementId
	 *            MyBatis namespace + statementId
	 * @param parameter
	 *            입력 parameter
	 * @param clazz
	 *            generic type class
	 * 
	 * @return 조회된 데이터 (0건 이상)
	 */
	<T> List<T> queryForList(String statementId, Object parameter, Class<T> clazz);

	/**
	 * 다건 조회 연산을 수행한다.
	 * 
	 * @param statementId
	 *            MyBatis namespace + statementId
	 * @param parameter
	 *            입력 parameter
	 * 
	 * @return 조회된 데이터 (0건 이상)
	 */
	List<?> queryForList(String statementId, Object parameter);

	/**
	 * @param <T>
	 *            generic type class 다건 조회 연산을 수행한다.
	 * 
	 * @param statement
	 *            TotalCount와 데이터를 조회하기 위한 Statement (MyBatis namespace + statementId)
	 * @param parameter
	 *            입력 parameter
	 * @return PageInfo
	 */
	<T> PageInfo<T> queryForPagenatedList(PageStatement statement, Object parameter);

	/**
	 * 다건 조회 연산을 수행한다.
	 * 
	 * @param <T>
	 *            generic type class
	 * @param statement
	 *            TotalCount와 데이터를 조회하기 위한 Statement (MyBatis namespace + statementId)
	 * @param parameter
	 *            입력 parameter
	 * @param pageNum
	 *            페이징 처리에 사용 (1..n)
	 * @param pageRows
	 *            페이징 처리에 사용
	 * @return PageInfo
	 */
	<T> PageInfo<T> queryForPagenatedList(PageStatement statement, Object parameter, int pageNum, int pageRows);

	/**
	 * 다건 조회 연산을 수행한다.
	 * 
	 * @param <T>
	 *            generic type class
	 * @param <R>
	 *            generic type class
	 * @param statementId
	 *            MyBatis namespace + statementId
	 * @param parameter
	 *            입력 parameter
	 * @param rowHandler
	 *            ResultSet을 가공하기 위한 row mapper
	 * @return List
	 */
	<T, R> List<R> queryWithResultHandler(String statementId, Object parameter, StreamHandler<T, R> rowHandler);

	/**
	 * <pre>
	 * 다건 조회 연산을 수행한다.
	 * </pre>
	 * 
	 * @param <T>
	 *            generic type class
	 * @param <R>
	 *            generic type class
	 * @param statement
	 *            TotalCount와 데이터를 조회하기 위한 Statement (MyBatis namespace + statementId)
	 * @param parameter
	 *            입력 parameter
	 * @param pageNum
	 *            페이징 처리에 사용
	 * @param pageRows
	 *            페이징 처리에 사용
	 * @param streamHandler
	 *            ResultSet을 가공하기 위한 row mapper
	 * @return PageInfo PageInfo
	 */
	<T, R> PageInfo<R> queryWithResultHandler(PageStatement statement, Object parameter, int pageNum, int pageRows,
                                              final StreamHandler<T, R> streamHandler);

	/**
	 * 수정 연산을 수행한다.
	 * 
	 * @param statementId
	 *            MyBatis namespace + statementId
	 * @param parameter
	 *            입력 parameter
	 * 
	 * @return null or inline query result
	 */
	Integer update(String statementId, Object parameter);

	/**
	 * 입력 연산을 수행한다.
	 * 
	 * @param statementId
	 *            MyBatis namespace + statementId
	 * @param parameter
	 *            입력 parameter
	 * 
	 * @return affected row count
	 */
	Object insert(String statementId, Object parameter);

	/**
	 * 삭제 연산을 수행한다.
	 * 
	 * @param statementId
	 *            MyBatis namespace + statementId
	 * @param parameter
	 *            입력 parameter
	 * 
	 * @return affected row count
	 */
	Integer delete(String statementId, Object parameter);

}
