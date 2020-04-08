package com.sys.supervision.remote;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sys.supervision.dao.EquipmentMapper;
import com.sys.supervision.dao.ProjectMapper;
import com.sys.supervision.entity.db.Equipment;
import com.sys.supervision.entity.db.Project;
import com.sys.supervision.enums.ProjectStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class RpcService {

    @Value("${req.user}")
    private String user;

    @Value("${req.password}")
    private String pwd;

    @Value("${url.base}")
    private String baseUrl;

    @Value("${action.getDevInfo}")
    private String action_getDevInfo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private EquipmentMapper equipmentMapper;


    public static String generateUrl(String base, String action, String user, String pwd) {
        return base + "?action=" + action + "&user=" + user + "&pwd=" + pwd;
    }

    public void getDevInfo() {
        String url = generateUrl(baseUrl, action_getDevInfo, user, pwd) + "&getOnlineDevice=all&networkModeList=3";
        log.info(url);
        String result = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println(jsonObject);

        JSONArray unitList = (JSONArray) jsonObject.get("UnitList");
        if (unitList == null) {
            log.info("DeviceList is empty");
            unitList = new JSONArray();
        };

        JSONArray deviceList = (JSONArray) jsonObject.get("DeviceList");
        if (deviceList == null) {
            log.info("DeviceList is empty");
            deviceList = new JSONArray();
        }

        for (Object o : unitList) {
            System.out.println(o);
            Map<String, Object> map = (Map<String, Object>) o;
            String unitId = String.valueOf(map.get("UnitId"));
            String unitName = String.valueOf(map.get("UnitName"));
            int exist = projectMapper.countByUnitId(unitId);
            if (exist == 0) {
                Project p = new Project();
                p.setEquipmentNumnber(0);
                p.setCity("未知");
                p.setName(unitName);
                p.setUnitId(unitId);
                p.setCreateAt(new Date());
                p.setCreater("auto");
                p.setModifier("auto");
                p.setModifyAt(new Date());
                p.setProjectStatus(ProjectStatusEnum.ON_LINE.getCode());
                projectMapper.insert(p);
            }
        }

        for (Object o : deviceList) {
            JSONObject device = (JSONObject) o;
            String devCode = String.valueOf(device.get("DevCode"));

            int exist = equipmentMapper.countByDevCode(devCode);
            if (exist > 0) continue;

            Equipment equipment = new Equipment();
            equipment.setProjectId(String.valueOf(device.get("UnitId")));
            equipment.setEquipmentStatus((Integer) device.get("Status3G"));
            equipment.setDevCode(devCode);
            equipment.setDevName(String.valueOf(device.get("DevName")));
            equipment.setLongitude(String.valueOf(device.get("Latitude")));
            equipment.setLatitude(String.valueOf(device.get("Longitude")));
            equipment.setCreateAt(new Date());
            equipment.setCreater("auto");
            equipment.setModifier("auto");
            equipment.setModifyAt(new Date());
            equipmentMapper.insert(equipment);
        }


        List<JSONObject> groupMap = equipmentMapper.countByGroup();
        for (JSONObject map : groupMap) {
            Integer projectId = Integer.valueOf((String) map.get("project_id"));
            Integer count = Long.valueOf(String.valueOf(map.get("count"))).intValue();
            projectMapper.updateNumberByProjectCode(projectId, count);
        }
    }


}
