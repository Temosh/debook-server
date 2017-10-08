/**
 * @author Serhii Tymoshenko
 */
@GenericGenerators(
        @GenericGenerator(
                name = IdGenerator.INCREMENTAL,
                strategy = IdGeneratorStrategy.INCREMENT
        )
)
package com.summ.debook.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerators;
import com.summ.debook.util.HibernateUtils.IdGenerator;
import com.summ.debook.util.HibernateUtils.IdGeneratorStrategy;