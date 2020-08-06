package com.simpo.tracker.common.converter;

import org.springframework.core.convert.converter.Converter;

import com.simpo.tracker.common.IntegerTools;

public class IntegerConverter implements Converter<String, Integer> {

    @Override
    public Integer convert(String param) {
        // TODO Auto-generated method stub
        if (param != null && !"".equals(param)) {
            return IntegerTools.parseInt(param);
        }
        return 0;
    }

}
