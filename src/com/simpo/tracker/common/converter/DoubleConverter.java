package com.simpo.tracker.common.converter;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * @author yu
 */
public class DoubleConverter implements Converter<String, Double> {

    @Override
    public Double convert(String param) {
        if (!StringUtils.isBlank(param)) {
            return Double.valueOf(param);
        }
        return 0.0;
    }

}
