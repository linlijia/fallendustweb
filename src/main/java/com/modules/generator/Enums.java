package com.modules.generator;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

public class Enums {
    public enum DeviceCommand {
        /**
         * 远程控制指令
         */

        OpenDoor("开舱门", "9003"),
        CloseDoor("关舱门", "9004"),
        TrayUp("托盘上升", "9001"),
        TrayDown("托盘下降", "9002"),
        OpenFillLight("开补光灯", "9005"),
        CloseFillLight("关补光灯", "9006"),
        TakePhoto("拍照", "9007"),
        OpenHeating("开加热", "9008"),
        CloseHeating("关加热", "9009"),
        OpenRefrigeration("开制冷", "9010"),
        CloseRefrigeration("关制冷", "9011"),
        OpenHumidification("开加湿", "9012"),
        CloseHumidification("关加湿", "9013"),
        MovementCalibration("机芯校准", "9014"),
        WeighingEmptyBucket("空桶称量", "9015"),
        MovementPower("机芯上电", "9016"),
        MovementShutdown("机芯关机", "9017"),
        MoveTrayToBalancePosition("托盘至平衡位", "9018"),
        Weighing("称量（全过程）", "9022"),
        StartToStandby("启动复位检测", "9031"),
        StopToStandby("停止复位检测", "9032"),
        SavePotentiometerUpperLimitValue("保存电位器上限位", "9033"),
        SavePotentiometerResetValue("保存电位器复位值", "9034"),
        TrayUpByStep("托盘按部上升", "9035"),
        TrayDownByStep("托盘按部下降", "9036"),
        UpdateApp("App更新", "9101"),
        UpdateConfig("配置更新", "9102"),
        updateRestart("设备重启", "9103"),
        EquipmentMaintenanceStatus("设备维护状态下发", "9104");
        private final String cmdName;
        private final String code;

        DeviceCommand(String cmdName, String code) {
            this.cmdName = cmdName;
            this.code = code;
        }


        public String getCmdName() {
            return cmdName;
        }

        public String getCode() {
            return code;
        }

    }

    public enum NotifyMsgType {
        OpsTask("运维任务"),
        UploadStatus("系统通知"),
        OpenDoor("其他"),
        Trouble("故障通知");

        private final String msgType;

        NotifyMsgType(String msgType) {
            this.msgType = msgType;
        }

        public String getMsgType() {
            return msgType;
        }
    }

    public enum TroubleType implements IEnum {
        NO_TROUBLE(90000, "无故障"),
        OFFLINE(90101, "离线"),
        WEIGHT_DATA_OVERRANGE(90201, "重量数据超量程"),
        WEIGHT_REDUCTION(90202, "重量数据负增重"),
        UNABLE_TO_CALIBRATION(90203, "机芯无法校准"),
        UNABLE_TO_CLEAR(90204, "机芯无法清零"),
        WEIGHT_DATA_OVERRANGE_HEAVY(90205, "重量数据严重超量程"),
        EMPTY_BUCKET_WEIGHT_EXCEPTION(90206, "空桶重量异常"),
        DOOR_POSITION_EXCEPTION(90301, "门状态异常"),
        OPEN_DOOR_FAILURE(90302, "开门故障"),
        CLOSE_DOOR_FAILURE(90303, "关门故障"),
        PLATE_POSITION_EXCEPTION(90401, "托盘状态异常"),
        PLATE_SLIGHT_TILT(90402, "托盘轻微倾斜"),
        PLATE_SEVERE_TILT(90403, "托盘严重倾斜"),
        PLATE_DOWN_FAILURE(90404, "托盘下降故障"),
        PLATE_UP_FAILURE(90405, "托盘上升故障"),
        PLATE_TO_BALANCE_POSITION_FAILURE(90406, "前往平衡位故障"),
        HEATING_FAILURE(90501, "加热故障"),
        REFRIGERATION_FAILURE(90502, "制冷故障"),
        HEATING_PLATE_SENSOR_BROKEN(90503, "热盘温度传感器故障"),
        WATER_TANK_SENSOR_BROKEN(90504, "水箱温度传感器故障"),
        TEMPERATURE_CONTROL_SENSOR_BROKEN(90505, "温控温度传感器故障"),
        EXIT_SENSOR_BROKEN(90506, "出口温湿度传感器故障"),
        ENTRANCE_SENSOR_BROKEN(90507, "进口温湿度传感器故障"),
        ENVIROMENT_SENSOR_BROKEN(90508, "环境温湿度传感器故障"),
        RAINFUL_SENSOR_BROKEN(90509, "雨量传感器故障"),
        POTENTIOMETER_BROKEN(90510, "电位器故障"),
        SNAPSHOT_FAILURE(90601, "抓图失败"),
        CAMERA_BROKEN(90602, "摄像机故障"),
        COMMUNICATION_FAILURE(90701, "与下位机通讯故障"),
        PROGRAM_CRASH(90702, "程序崩溃"),
        SYSTEM_REBOOT(90703, "系统重启");
        private final int code;
        private final String troubleName;

        TroubleType(int code, String troubleName) {
            this.code = code;
            this.troubleName = troubleName;
        }

        public int getCode() {
            return code;
        }

        public String getTroubleName() {
            return troubleName;
        }

        @Override
        public Serializable getValue() {
            return this.getCode();
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
