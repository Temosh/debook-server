package com.summ.debook.util;

/**
 * @author Serhii Tymoshenko
 */
public class HibernateUtils {

    public interface IdGenerator {
        String INCREMENTAL = "INCREMENTAL";
    }

    public interface IdGeneratorStrategy{
        String INCREMENT = "increment";
    }
}
