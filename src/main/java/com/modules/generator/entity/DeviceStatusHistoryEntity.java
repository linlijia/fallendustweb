package com.modules.generator.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @author novacaine
 * @email xxxxx@gmail.com
 * @date 2019-04-21 16:56:50
 */
@TableName("dust_device_status_history")
public class DeviceStatusHistoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Integer id;
    /**
     * 请求编码
     */
    private String qn;
    /**
     * 设备编码
     */
    private String mn;
    /**
     * 系统编码
     */
    private String st;
    /**
     * 命令编码
     */
    private String cn;
    /**
     * 数据时间
     */
    private Date dataTime;
    /**
     * 密码
     */
    private String pw;
    /**
     * 拆分包应答标识
     */
    private Integer flag;
    /**
     * 指令参数
     */
    private String cp;
    /**
     * X轴偏移量
     */
    private Float s10001;
    /**
     * Y轴偏移量
     */
    private Float s10002;
    /**
     * 热盘温度
     */
    private Float s10003;
    /**
     * 降尘室温度
     */
    private Float s10004;
    /**
     * 降尘室温度
     */
    private Float s10005;
    /**
     * 测量室温度
     */
    private Float s10006;
    /**
     * 测量室湿度
     */
    private Float s10007;
    /**
     * 电源电压
     */
    private Float s10008;
    /**
     * 电池电压
     */
    private Float s10009;
    /**
     * 经度
     */
    private Float s10010;
    /**
     * 纬度
     */
    private Float s10011;
    /**
     * 高度
     */
    private Float s10012;
    /**
     * 门状态
     */
    private Float s10013;
    /**
     * 托盘状态
     */
    private Float s10014;
    /**
     * 降水状态
     */
    private Float s10015;
    /**
     * 水箱水位状态
     */
    private Float s10016;
    /**
     * 机芯上电状态
     */
    private Float s10017;
    /**
     * 机芯校准状态
     */
    private Float s10018;
    /**
     * 操作
     */
    private Integer operation;
    /**
     * 站点ID
     */
    private Integer siteId;
    /**
     * 站点名称
     */
    private String siteName;
    /**
     * 城市
     */
    private String city;

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
     * 设置：请求编码
     */
    public void setQn(String qn) {
        this.qn = qn;
    }

    /**
     * 获取：请求编码
     */
    public String getQn() {
        return qn;
    }

    /**
     * 设置：设备编码
     */
    public void setMn(String mn) {
        this.mn = mn;
    }

    /**
     * 获取：设备编码
     */
    public String getMn() {
        return mn;
    }

    /**
     * 设置：系统编码
     */
    public void setSt(String st) {
        this.st = st;
    }

    /**
     * 获取：系统编码
     */
    public String getSt() {
        return st;
    }

    /**
     * 设置：命令编码
     */
    public void setCn(String cn) {
        this.cn = cn;
    }

    /**
     * 获取：命令编码
     */
    public String getCn() {
        return cn;
    }

    /**
     * 设置：数据时间
     */
    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    /**
     * 获取：数据时间
     */
    public Date getDataTime() {
        return dataTime;
    }

    /**
     * 设置：密码
     */
    public void setPw(String pw) {
        this.pw = pw;
    }

    /**
     * 获取：密码
     */
    public String getPw() {
        return pw;
    }

    /**
     * 设置：拆分包应答标识
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * 获取：拆分包应答标识
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * 设置：指令参数
     */
    public void setCp(String cp) {
        this.cp = cp;
    }

    /**
     * 获取：指令参数
     */
    public String getCp() {
        return cp;
    }

    /**
     * 设置：X轴偏移量
     */
    public void setS10001(Float s10001) {
        this.s10001 = s10001;
    }

    /**
     * 获取：X轴偏移量
     */
    public Float getS10001() {
        return s10001;
    }

    /**
     * 设置：Y轴偏移量
     */
    public void setS10002(Float s10002) {
        this.s10002 = s10002;
    }

    /**
     * 获取：Y轴偏移量
     */
    public Float getS10002() {
        return s10002;
    }

    /**
     * 设置：热盘温度
     */
    public void setS10003(Float s10003) {
        this.s10003 = s10003;
    }

    /**
     * 获取：热盘温度
     */
    public Float getS10003() {
        return s10003;
    }

    /**
     * 设置：降尘室温度
     */
    public void setS10004(Float s10004) {
        this.s10004 = s10004;
    }

    /**
     * 获取：降尘室温度
     */
    public Float getS10004() {
        return s10004;
    }

    /**
     * 设置：降尘室温度
     */
    public void setS10005(Float s10005) {
        this.s10005 = s10005;
    }

    /**
     * 获取：降尘室温度
     */
    public Float getS10005() {
        return s10005;
    }

    /**
     * 设置：测量室温度
     */
    public void setS10006(Float s10006) {
        this.s10006 = s10006;
    }

    /**
     * 获取：测量室温度
     */
    public Float getS10006() {
        return s10006;
    }

    /**
     * 设置：测量室湿度
     */
    public void setS10007(Float s10007) {
        this.s10007 = s10007;
    }

    /**
     * 获取：测量室湿度
     */
    public Float getS10007() {
        return s10007;
    }

    /**
     * 设置：电源电压
     */
    public void setS10008(Float s10008) {
        this.s10008 = s10008;
    }

    /**
     * 获取：电源电压
     */
    public Float getS10008() {
        return s10008;
    }

    /**
     * 设置：电池电压
     */
    public void setS10009(Float s10009) {
        this.s10009 = s10009;
    }

    /**
     * 获取：电池电压
     */
    public Float getS10009() {
        return s10009;
    }

    /**
     * 设置：经度
     */
    public void setS10010(Float s10010) {
        this.s10010 = s10010;
    }

    /**
     * 获取：经度
     */
    public Float getS10010() {
        return s10010;
    }

    /**
     * 设置：纬度
     */
    public void setS10011(Float s10011) {
        this.s10011 = s10011;
    }

    /**
     * 获取：纬度
     */
    public Float getS10011() {
        return s10011;
    }

    /**
     * 设置：高度
     */
    public void setS10012(Float s10012) {
        this.s10012 = s10012;
    }

    /**
     * 获取：高度
     */
    public Float getS10012() {
        return s10012;
    }

    /**
     * 设置：门状态
     */
    public void setS10013(Float s10013) {
        this.s10013 = s10013;
    }

    /**
     * 获取：门状态
     */
    public Float getS10013() {
        return s10013;
    }

    /**
     * 设置：托盘状态
     */
    public void setS10014(Float s10014) {
        this.s10014 = s10014;
    }

    /**
     * 获取：托盘状态
     */
    public Float getS10014() {
        return s10014;
    }

    /**
     * 设置：降水状态
     */
    public void setS10015(Float s10015) {
        this.s10015 = s10015;
    }

    /**
     * 获取：降水状态
     */
    public Float getS10015() {
        return s10015;
    }

    /**
     * 设置：水箱水位状态
     */
    public void setS10016(Float s10016) {
        this.s10016 = s10016;
    }

    /**
     * 获取：水箱水位状态
     */
    public Float getS10016() {
        return s10016;
    }

    /**
     * 设置：机芯上电状态
     */
    public void setS10017(Float s10017) {
        this.s10017 = s10017;
    }

    /**
     * 获取：机芯上电状态
     */
    public Float getS10017() {
        return s10017;
    }

    /**
     * 设置：机芯校准状态
     */
    public void setS10018(Float s10018) {
        this.s10018 = s10018;
    }

    /**
     * 获取：机芯校准状态
     */
    public Float getS10018() {
        return s10018;
    }

    /**
     * 设置：操作
     */
    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    /**
     * 获取：操作
     */
    public Integer getOperation() {
        return operation;
    }

    /**
     * 设置：站点ID
     */
    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    /**
     * 获取：站点ID
     */
    public Integer getSiteId() {
        return siteId;
    }

    /**
     * 设置：站点名称
     */
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    /**
     * 获取：站点名称
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     * 设置：城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取：城市
     */
    public String getCity() {
        return city;
    }
}
