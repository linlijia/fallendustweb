package com.modules.generator.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("dust_notify")
public class NotifyEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 消息类型
     */
    private String type;
    /**
     * 消息生成时间
     */
    private Date createAt;
    /**
     * 状态
     */
    private Integer messageStatus;
    /**
     * 关联ID
     */
    private Integer linkId;
    /**
     * 通知人
     */
    private Integer userId;
    /**
     * 通知人
     */
    private String userName;
    /**
     * 创建人
     */
    private Integer creator;
    /**
     * 创建人姓名
     */
    private String creatorName;

    /**
     * 已读
     */
    private Integer isRead;

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：消息内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：消息内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置：消息类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取：消息类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置：消息生成时间
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * 获取：消息生成时间
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * 设置：状态
     */
    public void setMessageStatus(Integer messageStatus) {
        this.messageStatus = messageStatus;
    }

    /**
     * 获取：状态
     */
    public Integer getMessageStatus() {
        return messageStatus;
    }

    /**
     * 设置：关联ID
     */
    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    /**
     * 获取：关联ID
     */
    public Integer getLinkId() {
        return linkId;
    }

    /**
     * 设置：通知人
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：通知人
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置：创建人
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * 获取：创建人
     */
    public Integer getCreator() {
        return creator;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
