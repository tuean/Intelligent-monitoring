package com.sys.supervision.remote;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sys.supervision.dao.EquipmentMapper;
import com.sys.supervision.dao.ProjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
            
        }

        for (Object o : deviceList) {
            JSONObject device = (JSONObject) o;

        }
    }


}
