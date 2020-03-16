package com.simpo.tracker.common.converter;

import org.springframework.core.convert.converter.Converter;

public class DoubleConverter implements Converter<String,Double>{

	@Override
	public Double convert(String param) {
		// TODO Auto-generated method stub
		if(param != null && !"".equals(param)){
			return Double.valueOf(param);
		}
		return 0.0;
	}

}
