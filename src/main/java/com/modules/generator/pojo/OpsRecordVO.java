package com.modules.generator.pojo;

import com.modules.generator.entity.OpsImageEntity;
import com.modules.generator.entity.OpsRecordEntity;

import java.io.Serializable;
import java.util.List;

public class OpsRecordVO extends OpsRecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<OpsImageEntity> opsImageEntity;

    public List<OpsImageEntity> getOpsImageEntity() {
        return opsImageEntity;
    }

    public void setOpsImageEntity(List<OpsImageEntity> opsImageEntity) {
        this.opsImageEntity = opsImageEntity;
    }
}
