import com.alycloud.pay.gateway.channel.util.SignUtil;

/*
 * 类文件名:  Test.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月5日
 * 功能版本:  V001Z0001
 */

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月5日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Test
{
    public static void main(String[] args)
    {
        String sign = SignUtil.genSign("F868E97F0C09D48C71767BAA5F623A3C", "accountName=赵启超&accountno=6214852102366096&accountType=1&address=浙桥路&agentno=4403050016&areaCode=310110&areaName=杨浦区&bankName=招商银行上海分行长阳支行&bankno=308290003046&bankType=招商银行&categoryWx=209&categoryZfb=2015091000052157&city=上海市&email=123@abc.com&fullName=赵启超&identityCard=370687199205122874&legalName=赵启超&legalPersonType=LEGAL_PERSON&merchName=赵启超&mobile=18512120736&payFee=0.00&payTypes=[{\"rate\":\"0.0010\",\"payType\":\"1\",\"scanType\":\"1\",\"settleType\":\"2\"},{\"rate\":\"0.0001\",\"payType\":\"2\",\"scanType\":\"1\",\"settleType\":\"2\"},{\"rate\":\"0.0030\",\"payType\":\"4\",\"scanType\":\"1\",\"settleType\":\"2\"},{\"rate\":\"0.0010\",\"payType\":\"1\",\"scanType\":\"2\",\"settleType\":\"2\"},{\"rate\":\"0.0001\",\"payType\":\"2\",\"scanType\":\"2\",\"settleType\":\"2\"},{\"rate\":\"0.0030\",\"payType\":\"4\",\"scanType\":\"2\",\"settleType\":\"2\"}]&province=上海&quickPayD0Rate=0.0010&quickPayT1Rate=0.0010&version=1.2");
       //String sign2 = SignUtil.genSign("F868E97F0C09D48C71767BAA5F623A3C", "accountName=赵启超&accountno=6214852102366096&accountType=1&address=浙桥路&agentno=4403050016&areaCode=310110&areaName=杨浦区&bankName=招商银行上海分行长阳支行&bankno=308290003046&bankType=招商银行&categoryWx=209&categoryZfb=2015091000052157&city=上海市&email=123@abc.com&fullName=赵启超&identityCard=370687199205122874&legalName=赵启超&legalPersonType=LEGAL_PERSON&merchName=赵启超&mobile=18512120736&payFee=0.00&payTypes=[{\"rate\":0.0010,\"payType\":1,\"scanType\":1,\"settleType\":2},{\"rate\":0.0001,\"payType\":2,\"scanType\":1,\"settleType\":2},{\"rate\":0.0030,\"payType\":4,\"scanType\":1,\"settleType\":2},{\"rate\":0.0010,\"payType\":1,\"scanType\":2,\"settleType\":2},{\"rate\":0.0001,\"payType\":2,\"scanType\":2,\"settleType\":2},{\"rate\":0.0030,\"payType\":4,\"scanType\":2,\"settleType\":2}]&province=上海&quickPayD0Rate=0.0010&quickPayT1Rate=0.0010&version=1.2");
       String sign2 = SignUtil.genSign("F868E97F0C09D48C71767BAA5F623A3C", "accountName=赵启超&accountno=6214852102366096&accountType=1&address=浙桥路&agentno=4403050016&areaCode=310110&areaName=杨浦区&bankName=招商银行上海分行长阳支行&bankno=308290003046&bankType=招商银行&categoryWx=209&categoryZfb=2015091000052157&city=上海市&email=123@abc.com&fullName=赵启超&identityCard=370687199205122874&legalName=赵启超&legalPersonType=LEGAL_PERSON&merchName=赵启超&mobile=18512120736&payFee=0.00&payTypes=[{\"rate\":0.0010,\"payType\":\"1\",\"scanType\":1,\"settleType\":2},{\"rate\":0.0001,\"payType\":2,\"scanType\":1,\"settleType\":2},{\"rate\":0.0030,\"payType\":4,\"scanType\":1,\"settleType\":2},{\"rate\":0.0010,\"payType\":1,\"scanType\":2,\"settleType\":2},{\"rate\":0.0001,\"payType\":2,\"scanType\":2,\"settleType\":2},{\"rate\":0.0030,\"payType\":4,\"scanType\":2,\"settleType\":2}]&province=上海&quickPayD0Rate=0.0010&quickPayT1Rate=0.0010&version=1.2");
       System.out.println(sign);
        System.out.println(sign2);
    }
}
