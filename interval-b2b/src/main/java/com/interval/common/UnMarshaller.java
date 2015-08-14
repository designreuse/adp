package com.interval.common;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Created by USER on 11-08-2015.
 */
public class UnMarshaller {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public static <T> T unmarshallJSON(final Class clazz, final String json) throws Exception {

        T bo;
        try{
            bo = (T) JSON_MAPPER.readValue(json, clazz);
        }
        catch (Exception exception) {

            throw new Exception(exception); //NOPMD
        }

        return bo;
    }
}
