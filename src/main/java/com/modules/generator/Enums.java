package com.modules.generator;

public class Enums {
    public enum DeviceCommand {
        UploadData("上传数据", "2011"),
        UploadStatus("上传状态", "2000"),
        OpenDoor("开舱门", "9003"),
        CloseDoor("关舱门", "9004"),
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
        MovementClear("机芯清零", "9015"),
        MovementPower("机芯上电", "9016"),
        MovementShutdown("机芯关机", "9017"),
        SingleWeighing("单重量", "9018"),
        ViewInsideBucket("查看内筒", "9019"),
        Drying("烘干", "9020"),
        BalanceBeforeWeighing("称量前平衡", "9021"),
        Weighing("称量（全过程）", "9022"),
        CloseAlarm("关报警", "9023"),
        Unlock("解锁", "9024"),
        UpdateApp("App更新", "9101"),
        UpdateConfig("配置更新", "9102"),
        updateRestart("设备重启", "9103"),
        EquipmentMaintenanceStatus("设备维护状态下发", "9104");
        private String cmdName;
        private String code;

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
        OpenDoor("其他");
        private String msgType;

        NotifyMsgType(String msgType) {
            this.msgType = msgType;
        }

        public String getMsgType() {
            return msgType;
        }
    }
}
