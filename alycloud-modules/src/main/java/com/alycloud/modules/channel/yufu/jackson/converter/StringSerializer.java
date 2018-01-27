/*
 * 类文件名:  StringSerializer.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月14日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.channel.yufu.jackson.converter;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月14日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class StringSerializer  extends StdSerializer<Object>
{
    protected StringSerializer() {
        super(Object.class);
    }

    private static final long serialVersionUID = 1L;

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        StringWriter sw = new StringWriter();
        JsonGenerator jg = gen.getCodec().getFactory().createGenerator(sw);
        jg.writeObject(value);
        gen.writeString(sw.getBuffer().toString());
    }
}
