

package com.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.sys.entity.SysLogEntity;

import java.util.Map;

public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
