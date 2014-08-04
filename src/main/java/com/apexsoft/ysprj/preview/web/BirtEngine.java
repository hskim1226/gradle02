package com.apexsoft.ysprj.preview.web;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.IPlatformContext;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.core.framework.PlatformServletContext;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Created by Administrator on 2014-08-04.
 */
public class BirtEngine {


    private static IReportEngine birtEngine;

    private static Properties configProps = new Properties();

    private final static String configFile = "birt.properties";

    public static synchronized void initBirtConfig() {
        loadEngineProps();
    }

    @SuppressWarnings("unchecked")
    public static synchronized IReportEngine getBirtEngine(ServletContext sc) {
        if( birtEngine == null ) {
            EngineConfig config = new EngineConfig();
            if( configProps != null) {
                String logLevel = configProps.getProperty("logLevel");
                Level level = Level.OFF;
                if( "SEVERE".equals(logLevel) ) {
                    level = Level.SEVERE;
                } else if( "WARNING".equals(logLevel) ) {
                    level = Level.WARNING;
                } else if( "INFO".equals(logLevel) ) {
                    level = Level.INFO;
                } else if( "CONFIG".equals(logLevel) ) {
                    level = Level.CONFIG;
                } else if( "FINE".equals(logLevel) ) {
                    level = Level.FINE;
                } else if( "FINER".equals(logLevel) ) {
                    level = Level.FINER;
                } else if( "FINEST".equals(logLevel) ) {
                    level = Level.FINEST;
                } else if( "OFF".equals(logLevel) ) {
                    level = Level.OFF;
                }
                config.setLogConfig( configProps.getProperty("logDirectory"), level );
            }

            config.getAppContext().put( EngineConstants.APPCONTEXT_CLASSLOADER_KEY,
                    Thread.currentThread().getContextClassLoader() );
            config.setEngineHome( "" );
            IPlatformContext context = new PlatformServletContext( sc );
            config.setPlatformContext( context );

            try {
                Platform.startup(config);
            } catch( BirtException e ) {
                e.printStackTrace();
            }

            IReportEngineFactory factory = (IReportEngineFactory) Platform
                    .createFactoryObject( IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY );
            birtEngine = factory.createReportEngine( config );
        }
        return birtEngine;
    }

    public static synchronized void destroyBirtEngine() {
        if( birtEngine == null ) {
            return;
        }
        birtEngine.destroy();
        Platform.shutdown();
        birtEngine = null;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    private static void loadEngineProps() {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream( configFile );
            if( stream != null ) {
                configProps.load( stream );
                stream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
