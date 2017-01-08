package ru.javawebinar.topjava;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Asfarus on 08.01.2017.
 */
public class SimpleRule implements TestRule {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleRule.class);
    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                long milisTime = System.currentTimeMillis();
                try {
                    base.evaluate();
                } catch (Throwable throwable) {
                    LOG.error(" test " + description + ": " +throwable.toString());
                }
                milisTime = System.currentTimeMillis() - milisTime;
                LOG.info("test {} in {} ms",description,milisTime);
            }
        };

    }
}
