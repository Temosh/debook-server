package com.summ.debook.service.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * TODO Delete after migration to UUID [DB-46]
 *
 * @author Serhii Tymoshenko
 */
public class IdConversionHelper {
    private static Logger LOG = LoggerFactory.getLogger(IdConversionHelper.class);

    public static long parseId(String id, String errorMessage) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage + ": " + id);
        }
    }

    public static String toString(Long id) {
        if (id == null) return null;
        return id.toString();
    }
}
