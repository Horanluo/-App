/*
 * 类文件名:  FormatUtil.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月27日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.channel.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月27日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class FormatUtil
{
    @SuppressWarnings("rawtypes")
    public static String formatMap(Map map) {
        StringBuilder sb = new StringBuilder();
        Set set = map.keySet();
        Iterator it = set.iterator();
        sb.append("{");
        while(it.hasNext()){
            Object key = it.next();
            Object value = map.get(key);
            sb.append("\r\n\t\"" + key + "\":\"" + value + "\",");
        }
        sb.append("\r\n}");
        return sb.toString();
    }

    /**
     * 格式化JSON字符串
     * @param jsonStr
     * @return
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr))
            return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
            case '{':
            case '[':
                sb.append(current);
                sb.append('\n');
                indent++;
                addIndentBlank(sb, indent);
                break;
            case '}':
            case ']':
                sb.append('\n');
                indent--;
                addIndentBlank(sb, indent);
                sb.append(current);
                break;
            case ',':
                sb.append(current);
                if (last != '\\') {
                    sb.append('\n');
                    addIndentBlank(sb, indent);
                }
                break;
            default:
                sb.append(current);
            }
        }
        return sb.toString();
    }

    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }
}
