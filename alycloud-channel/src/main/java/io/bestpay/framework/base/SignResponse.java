package io.bestpay.framework.base;

import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

@javax.xml.bind.annotation.XmlRootElement
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlType(name="SignResponse", namespace="io.bestpay.framework.base")
public class SignResponse<T extends SpecificRecordBase> extends Response<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5634998185418915050L;
	@javax.xml.bind.annotation.XmlElement(name="sign")
	private String sign;
	
	public SignResponse(boolean success, String message, T data, String sign) {
		super(success, message, data);
		this.sign = sign;
	}
	
	public SignResponse(Response<T> response, String sign) {
		super(response.isSuccess(), response.getMessage(), response.getData());
		this.sign = sign;
	}

	@com.fasterxml.jackson.annotation.JsonGetter("sign")
  	@org.codehaus.jackson.annotate.JsonProperty("sign")
	@com.fasterxml.jackson.annotation.JsonInclude(Include.NON_NULL)
	@org.codehaus.jackson.map.annotate.JsonSerialize(include=Inclusion.NON_NULL)
	@javax.xml.bind.annotation.XmlTransient
	public String getSign() {
		return sign;
	}

	protected void setSign(String sign) {
		this.sign = sign;
	}

	public SignResponse() {
		super();
	}

}
