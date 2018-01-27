package com.alycloud.channel.esyto.jackson.converter;

import com.alycloud.channel.esyto.enums.ScanType;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * @author Moyq5
 * @date 2017年9月30日
 */
public class ScanTypeDeserialize extends StdConverter<Integer, ScanType> {

	@Override
	public ScanType convert(Integer value) {
		if (null == value) {
			return null;
		}
		return ScanType.values()[value];
	}

}
