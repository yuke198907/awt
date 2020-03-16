package com.simpo.tracker.common.converter;

import org.springframework.core.convert.converter.Converter;

public class LongConverter implements Converter<String,Long>{

	@Override
	public Long convert(String param) {
		// TODO Auto-generated method stub
		if(param != null && !"".equals(param)){
			return Long.valueOf(param);
		}
		return 0l;
	}

}
