package com.bcipher.core.schedulers;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple demo for cron-job like tasks that get executed regularly.
 * It also demonstrates how property values can be set. Users can
 * set the property values in /system/console/configMgr
 */
@Designate(ocd = SimpleScheduledTask.Config.class)
@Component(service = Runnable.class)
public class SimpleScheduledTask implements Runnable {

    /**
     * Logger constant.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * parameters.
     */
    private String myParameter;


    /**
     * A scheduler task.
     */
    @ObjectClassDefinition(name = "A scheduled task",
            description = "Simple demo for cron-job like task with properties")
    public @interface Config {
        /**
         * Scheduler cron expression.
         * @return {@link String}
         */
        @AttributeDefinition(name = "Cron-job expression")
        String schedulerExpression() default "*/30 * * * * ?";

        /**
         * Concurrent task.
         * @return {@link boolean}
         */
        @AttributeDefinition(
        name = "Concurrent task",
        description = "Whether or not to schedule this task concurrently")
        boolean schedulerConcurrent() default false;

        /**
         * A Parameter.
         * @return {@link String}
         */
        @AttributeDefinition(name = "A parameter",
                description = "Can be configured in /system/console/configMgr")
        String myParameter() default "";
    }


    /**
     * Run scheduler.
     */
    @Override
    public void run() {
        logger.debug("SimpleScheduledTask is now running, myParameter='{}'",
                myParameter);
    }

    /**
     * activate scheduler.
     * @param config
     */
    @Activate
    protected void activate(final Config config) {
        myParameter = config.myParameter();
    }

}
