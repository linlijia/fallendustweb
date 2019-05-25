package com.modules.generator.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("dust_ops_image")
public class OpsImageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Integer id;
	/**
	 * 运维记录ID
	 */
	private Integer recordId;
	/**
	 * 运维记录表
	 */
	private String filePath;
	/**
	 * 
	 */
	private Date createAt;
	/**
	 * 上传人
	 */
	private Integer uploadUser;

	/**
	 * 上传人名
 	 */

	private String uploadUserName;


	/**
	 * 设置：ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：运维记录ID
	 */
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	/**
	 * 获取：运维记录ID
	 */
	public Integer getRecordId() {
		return recordId;
	}
	/**
	 * 设置：运维记录表
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * 获取：运维记录表
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * 设置：
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	/**
	 * 获取：
	 */
	public Date getCreateAt() {
		return createAt;
	}
	/**
	 * 设置：上传人
	 */
	public void setUploadUser(Integer uploadUser) {
		this.uploadUser = uploadUser;
	}
	/**
	 * 获取：上传人
	 */
	public Integer getUploadUser() {
		return uploadUser;
	}

	public String getUploadUserName() {
		return uploadUserName;
	}

	public void setUploadUserName(String uploadUserName) {
		this.uploadUserName = uploadUserName;
	}
}
