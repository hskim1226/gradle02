package com.apexsoft.ysprj.common.persistence.dao.handler;

/**
 * RowHandler
 * 
 * @param <T>
 *            오브젝트 제너릭
 * @param <R>
 *            오브젝트 제너릭
 */
public abstract class RowHandler<T, R> implements StreamHandler<T, R> {

	/** The stop. */
	private boolean stop = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.skplanet.storeplatform.framework.core.persistence.dao.handler.StreamHandler#open()
	 */
	@Override
	public void open() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.skplanet.storeplatform.framework.core.persistence.dao.handler.StreamHandler#close()
	 */
	@Override
	public void close() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.skplanet.storeplatform.framework.core.persistence.dao.handler.StreamHandler#isStop()
	 */
	@Override
	public boolean isStop() {
		return stop;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.skplanet.storeplatform.framework.core.persistence.dao.handler.StreamHandler#stop()
	 */
	@Override
	public void stop() {
		this.stop = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.skplanet.storeplatform.framework.core.persistence.dao.handler.StreamHandler#handleRow(java.lang.Object)
	 */
	@Override
	public abstract R handleRow(T valueObject);

}
