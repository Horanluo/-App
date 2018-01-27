package com.alycloud.channel.support.config;

import com.alycloud.channel.payeco.Config;

/**
 * 易联支付配置参数
 * @author Moyq5
 * @date 2017年4月19日
 */
public class PayecoConfig implements Config {

	@Override
	public String getPrivateKey() {
		return com.alycloud.channel.support.config.Config.getString("quick-pri-key");
		//return "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKilPOUrVO/XDPG8pu+bcYRFgf8amSYenwNcr4vdx9KBRDZ+NHVY0AeBqbUZW2PRH5LvINaJ7RwPdcFWhktGXygZluCpFpBqDfAq3DkQZxHZzoDasCBC2D2PWZPrd7s6qGB7lD477o7/1nDbxFmohFPwI9JUPcrdj1xvYtApLL09AgMBAAECgYBDW7w2zFfgc8R3ySHkEfIFIr4JZlZFI3XfoeV7t9lX+smD+mR5ej+cv+1IqBgYhi5WQaycA/rwejxOPIDnD/RxKb9Zmu0ggP/pfXq/6rk07JTm4VXqB0ZzBwJFRHvq+l99WG0PczrBbHFc2q186S/RDS9C4qezNNorUynKApIAAQJBANTpfgLWI3/EcbtTJ55w5cNOpJK5pc80Q34qtw1BrrF6MyVnwF5O5VWsJnxxiZOUElF9EFe9vI8/1Bgif7GeTD0CQQDKxmOk7xnX0Yc5WiZcHyxYH+ekoT4UBu1qyzjPza6+R+uxxNNM7+QeFsvRw9+CCVlvFCLUBmoJVDmoafcnKkUBAkAg4jcuJPiyoRqXvg4ecH1sHtdYOggb6oyKDfrbt0tN6fvASTfy2OiexMnk6nw/KN1zbId7pbhbv5X4bEugmTxZAkB8rY8HEpzpPkPO18dOTTyO0s8zQpFQ7xF74IiF/Qm8paweGc9yNu4kMhAQYkdDRUxgKBliFK+OHON20fDNfAgBAkEAxFL2aLrWxbC235miukTxSm5b5/38Q+XkELFkU2LAkAI6PSxj3xEO2duhFb2mqE0wFt/d44bm6u4e+1jg5zdQiQ==";
	}
	
	@Override
	public String getPublicKey() {
		return com.alycloud.channel.support.config.Config.getString("quick-pub-key");
		//return "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoymAVb04bvtIrJxczCT/DYYltVlRjBXEBFDYQpjCgSorM/4vnvVXGRb7cIaWpI5SYR6YKrWjvKTJTzD5merQM8hlbKDucxm0DwEj4JbAJvkmDRTUs/MZuYjBrw8wP7Lnr6D6uThqybENRsaJO4G8tv0WMQZ9WLUOknNv0xOzqFQIDAQAB";
	}

	@Override
	public String getDataEncode() {
		return "UTF-8";
	}

	@Override
	public String getServer() {
		return com.alycloud.channel.support.config.Config.getString("quick-url-request");
		//return "https://mobile.payeco.com";
	}

	@Override
	public String getMerchantId() {
		return com.alycloud.channel.support.config.Config.getString("quick-merchno");
		//return "502050002399";
	}

	@Override
	public String getNotifyUrl() {
		return com.alycloud.channel.support.config.Config.getString("quick-url-notify-2");
		//return "http://moyq5.oicp.net/posp-api/fastNotify.do?m=payeco";
	}
	
}
