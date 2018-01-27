package com.alycloud.modules.entity;

import java.io.Serializable;

import com.alycloud.modules.enums.ChangeMerchQualificationAuditStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 商户经营资质材料变更信息
 * @author Moyq5
 * @date 2017年9月20日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class ChangeMerchQualification implements Serializable {
	
	private Long id;
	private Long merchId;// 商户ID
	private String imgHead;// 门头照片
	private String imgPlace;// 经营场所照片
	private String imgCashier;// 收银台照片
	private String imgLicense;// 营业执照照片
	private String imgContract;// 合同照
	private String fileOther;// 其它文件
	private String oriImgHead;// 原门头照片
	private String oriImgPlace;// 原经营场所照片
	private String oriImgCashier;// 原收银台照片
	private String oriImgLicense;// 原营业执照照片
	private String oriImgContract;// 原合同照
	private String oriFileOther;// 原其它文件
	/**
	 * @see {@link ChangeMerchQualificationAuditStatus}
	 */
	private Integer auditStatus;// 审核状态
	private String auditDescr;// 审核状态描述
	private String auditTime;// 审核时间：yyyy-MM-dd HH:mm:ss
	private String addTime;// 添加时间（资料提交时间）：yyyy-MM-dd HH:mm:ss
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMerchId() {
		return merchId;
	}
	public void setMerchId(Long merchId) {
		this.merchId = merchId;
	}
	public String getImgHead() {
		return imgHead;
	}
	public void setImgHead(String imgHead) {
		this.imgHead = imgHead;
	}
	public String getImgPlace() {
		return imgPlace;
	}
	public void setImgPlace(String imgPlace) {
		this.imgPlace = imgPlace;
	}
	public String getImgCashier() {
		return imgCashier;
	}
	public void setImgCashier(String imgCashier) {
		this.imgCashier = imgCashier;
	}
	public String getImgLicense() {
		return imgLicense;
	}
	public void setImgLicense(String imgLicense) {
		this.imgLicense = imgLicense;
	}
	public String getImgContract() {
		return imgContract;
	}
	public void setImgContract(String imgContract) {
		this.imgContract = imgContract;
	}
	public String getFileOther() {
		return fileOther;
	}
	public void setFileOther(String fileOther) {
		this.fileOther = fileOther;
	}
	public String getOriImgHead() {
		return oriImgHead;
	}
	public void setOriImgHead(String oriImgHead) {
		this.oriImgHead = oriImgHead;
	}
	public String getOriImgPlace() {
		return oriImgPlace;
	}
	public void setOriImgPlace(String oriImgPlace) {
		this.oriImgPlace = oriImgPlace;
	}
	public String getOriImgCashier() {
		return oriImgCashier;
	}
	public void setOriImgCashier(String oriImgCashier) {
		this.oriImgCashier = oriImgCashier;
	}
	public String getOriImgLicense() {
		return oriImgLicense;
	}
	public void setOriImgLicense(String oriImgLicense) {
		this.oriImgLicense = oriImgLicense;
	}
	public String getOriImgContract() {
		return oriImgContract;
	}
	public void setOriImgContract(String oriImgContract) {
		this.oriImgContract = oriImgContract;
	}
	public String getOriFileOther() {
		return oriFileOther;
	}
	public void setOriFileOther(String oriFileOther) {
		this.oriFileOther = oriFileOther;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAuditDescr() {
		return auditDescr;
	}
	public void setAuditDescr(String auditDescr) {
		this.auditDescr = auditDescr;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	
}
