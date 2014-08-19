package com.apexsoft.ysprj.preview.web;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by Administrator on 2014-08-13.
 */
@Deprecated
public class BirtEngineFactory implements FactoryBean<IReportEngine>, ApplicationContextAware, DisposableBean {

    private ApplicationContext context;
    private IReportEngine birtEngine;
    private File _resolvedDirectory;
    private Level logLevel;

    @Override
    public void setApplicationContext( ApplicationContext applicationContext ) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void destroy() throws Exception {
        birtEngine.destroy();
        Platform.shutdown();
    }

    @Override
    public IReportEngine getObject() {
        EngineConfig config = new EngineConfig();
        config.getAppContext().put( "spring", this.context );
        config.setLogConfig( null != this._resolvedDirectory ? this._resolvedDirectory.getAbsolutePath() : null, this.logLevel);

        try {
            Platform.startup(config);
        } catch ( BirtException e ) {
            throw new RuntimeException( "Could not start the Birt engine!", e );
        }

        IReportEngineFactory factory = (IReportEngineFactory) Platform
                .createFactoryObject( IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY );
        IReportEngine be = factory.createReportEngine( config );
        this.birtEngine = be;

        return be;
    }

    @Override
    public Class<?> getObjectType() {
        return IReportEngine.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setLogLevel( Level logLevel ) {
        this.logLevel = logLevel;
    }

    public void setLogDirectory( File f) {
        validateLogDirectory(f);
        this._resolvedDirectory = f;
    }

    public void setLogDirectory( Resource resource ) {
        File f = null;
        try {
            f = resource.getFile();
            validateLogDirectory( f );
            this._resolvedDirectory = f;
        } catch (IOException e) {
            throw new RuntimeException( "Could not set the log directory", e );
        }
    }

    private void validateLogDirectory( File f ) {
        Assert.notNull(f, "The directory must not be null");
        Assert.isTrue(f.isDirectory() , "The path given must be a directory");
        Assert.isTrue(f.exists(), "The path specified must exist!");
    }
}
