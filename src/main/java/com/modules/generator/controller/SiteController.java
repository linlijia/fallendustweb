package com.modules.generator.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.common.annotation.SysLog;
import com.common.utils.DateUtils;
import com.common.utils.PageUtils;
import com.common.utils.R;
import com.modules.generator.entity.DeviceDataEntity;
import com.modules.generator.entity.DeviceEntity;
import com.modules.generator.entity.DeviceStatusEntity;
import com.modules.generator.entity.SiteEntity;
import com.modules.generator.service.DeviceDataService;
import com.modules.generator.service.DeviceService;
import com.modules.generator.service.DeviceStatusService;
import com.modules.generator.service.SiteService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("generator/site")
public class SiteController {
    @Autowired
    private SiteService siteService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceDataService deviceDataService;
    @Autowired
    private DeviceStatusService deviceStatusService;
    private Map<String, SiteEntity> siteEntityMap;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:site:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = siteService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:site:info")
    public R info(@PathVariable("id") Integer id) {
        SiteEntity site = siteService.selectById(id);

        return R.ok().put("site", site);
    }

    /**
     * 保存
     */
    @SysLog("新增站点")
    @RequestMapping("/save")
    @RequiresPermissions("generator:site:save")
    public R save(@RequestBody SiteEntity site) {
        siteService.insert(site);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改站点")
    @RequestMapping("/update")
    @RequiresPermissions("generator:site:update")
    public R update(@RequestBody SiteEntity site) {
        siteService.updateById(site);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除站点")
    @RequestMapping("/delete")
    @RequiresPermissions("generator:site:delete")
    public R delete(@RequestBody Integer[] ids) {
        siteService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @SysLog("地图数据列表")
    @RequestMapping("/map/list")
    /**
     *     @RequiresPermissions("generator:site:map:list")
     */
    public R SiteMapList(String city) {

        //月降尘量计算公式：a34011_rtd/353.25*10000/30*a34011_ori
        if (city == null || city.trim().equals("")) {
            city = "上海市";
        }
        List<SiteEntity> siteEntities = siteService.selectList(new EntityWrapper<SiteEntity>().eq("city", city));
        Date end = new Date();
        Date start = DateUtils.addDateMonths(end, -6);
        Map<String, Object> result = deviceDataService.calculation(start, end, city);
        Map<String, Map<String, Object>> data = (Map<String, Map<String, Object>>) result.get("dustData");
        for (Map.Entry<String, Map<String, Object>> entry : data.entrySet()) {
            Map<String, Object> e = entry.getValue();

        }
        List<Map<String, Object>> list = new ArrayList<>();
        for (SiteEntity siteEntity : siteEntities) {
            Map<String, Object> r = new HashMap<>();
            r.put("ID", siteEntity.getId());
            r.put("siteName", siteEntity.getSite());
            Map<String, Object> e = data.get(siteEntity.getSite());
            r.put("weight", e == null ? "-" : e.get("avgRtd"));
            r.put("dust", e == null ? "-" : e.get("avgDust"));
            r.put("effectiveDay", e == null ? "-" : e.get("avgEffectiveDay"));
            r.put("weighingTime", e == null ? "-" : e.get("dataTime"));
            list.add(r);
        }
        return R.ok().put("data", list);
    }

    /**
     * 设备位置(地图坐标)
     */
    @RequestMapping("/location")
    /**
     * @RequiresPermissions("generator:device:location")
     */
    public R Location() {
        //查询站点
        List<SiteEntity> sites = siteService.selectList(null);
        Map<String, SiteEntity> siteEntityMap = new HashMap<>();
        for (SiteEntity siteEntity : sites) {
            siteEntityMap.put(String.valueOf(siteEntity.getId()), siteEntity);
        }

        //查询设备
        List<DeviceEntity> devices = deviceService.selectList(null);
        Map<Integer, DeviceEntity> deviceEntityMap = new HashMap<>(1);
        for (DeviceEntity deviceEntity : devices) {
            deviceEntityMap.put(deviceEntity.getSiteId(), deviceEntity);
        }


        //查询最后一次上报的数据
        EntityWrapper<DeviceDataEntity> entityEntityWrapper = new EntityWrapper();
        entityEntityWrapper.where(" id in (select max(id) from dust_device_data group by mn)");
        List<DeviceDataEntity> dataList = deviceDataService.selectList(entityEntityWrapper);
        Map<String, DeviceDataEntity> dataEntityMap = new HashMap<>();
        for (DeviceDataEntity data : dataList) {
            dataEntityMap.put(data.getMn(), data);
        }

        //查询最后一次上报的状态
        EntityWrapper<DeviceStatusEntity> deviceStatusEntity = new EntityWrapper();
        deviceStatusEntity.where(" id in (select max(id) from dust_device_status group by  mn)");
        List<DeviceStatusEntity> statusList = deviceStatusService.selectList(deviceStatusEntity);
        Map<String, DeviceStatusEntity> statusMap = new HashMap<>();
        for (DeviceStatusEntity status : statusList) {
            statusMap.put(status.getMn(), status);
        }

        Map<String, List<Map<String, Object>>> ret = new HashMap<>();
        ret.put("failure", new ArrayList<>());
        ret.put("online", new ArrayList<>());
        ret.put("offline", new ArrayList<>());

        for (SiteEntity siteEntity : sites) {
            String mn = "";
            if (deviceEntityMap.get(siteEntity.getId()) != null) {
                mn = deviceEntityMap.get(siteEntity.getId()).getMn();
            }
            Map<String, Object> r = new HashMap<>();
            Map<String, Object> details = new HashMap<>();
            details.put("id", siteEntity.getId());
            details.put("mn", mn);
            r.put("name", siteEntity.getSite());
            details.put("name", siteEntity.getSite());
            //坐标
            r.put("value", new BigDecimal[]{siteEntity.getLatidute(), siteEntity.getLongidute()});
            //状态
            Integer operation = -1;
            if (deviceEntityMap.get(siteEntity.getId()) != null) {
                operation = deviceEntityMap.get(siteEntity.getId()).getOperation();
            }
            details.put("operation", operation);
            details.put("name", siteEntity.getSite());
            //状态更新时间
            Date statusDate = null;
            if (statusMap.get(mn) != null) {
                statusDate = statusMap.get(mn).getDataTime();
            }
            details.put("statsUpdateAt", statusDate);
            details.put("city", siteEntity.getCity());
            details.put("district", siteEntity.getDistrict());
            details.put("address", siteEntity.getAddress());
            if (dataEntityMap.get(mn) != null) {
                details.put("Ori", dataEntityMap.get(mn).getA34011Ori());
                details.put("Rtd", dataEntityMap.get(mn).getA34011Rtd());
                details.put("flag", dataEntityMap.get(mn).getA34011Flag());
            } else {
                details.put("Ori", "-");
                details.put("Rtd", "-");
                details.put("flag", "-");
            }

            r.put("details", details);
            if (statusDate != null && (new Date()).getTime() - statusDate.getTime() > 1000 * 60 * 30) {
                //30分钟未上报数据，离线
                ret.get("offline").add(r);
            } else if (operation != null && operation == 0) {
                //operation 状态为0. 故障
                ret.get("failure").add(r);

            } else {
                //在线
                ret.get("online").add(r);
            }
        }

        return R.ok().put("data", ret);
    }


    /**
     * 地图上的站点详情
     */
    @RequestMapping("/details/{id}")
    /**
     *  @RequiresPermissions("generator:site:details")
     */
    public R SiteDetailsForMap(@PathVariable("id") Integer id, String start, String end) {
        Date startTime;
        if (start == null || "".equals(start.trim())) {
            startTime = DateUtils.addDateDays(new Date(), -30);
        } else {
            startTime = DateUtils.stringToDate(start, DateUtils.DATE_TIME_PATTERN);
        }
        Date endTime;
        if (end == null || "".equals(end.trim())) {
            endTime = new Date();
        } else {
            endTime = DateUtils.stringToDate(end, DateUtils.DATE_TIME_PATTERN);
        }

        Map<String, Object> m = new HashMap<>(1);
        SiteEntity site = siteService.selectById(id);
        if (site == null) {
            return R.ok().put("data", m);
        }
        List<DeviceEntity> deviceEntities = deviceService.selectBySiteId(site.getId());
        Map<String, List<Map<String, Object>>> data = new HashMap(1);
        for (DeviceEntity deviceEntity : deviceEntities) {
            data.put(deviceEntity.getMn(), deviceDataService.queryHistoryData(startTime, endTime, deviceEntity.getMn()));
        }

        m.put("site", site);
        m.put("dustData", data);
        return R.ok().put("data", m);
    }

}
