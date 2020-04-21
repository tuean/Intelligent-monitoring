package com.sys.supervision.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sys.supervision.config.Constant;
import com.sys.supervision.dao.EquipmentMapper;
import com.sys.supervision.entity.db.Equipment;
import com.sys.supervision.enums.EquipmentStatusEnum;
import com.sys.supervision.model.BaseListResponse;
import com.sys.supervision.model.BaseResponse;
import com.sys.supervision.model.enhance.EquipGroupByProject;
import com.sys.supervision.model.request.EquipmentListRequest;
import com.sys.supervision.model.response.EquipmentListResponse;
import com.sys.supervision.service.IEquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.sys.supervision.enums.EquipmentStatusEnum.getValueByCode;

@Service
@Slf4j
public class EquipmentServiceImpl implements IEquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public String create(String id) {
        Equipment equipment = new Equipment();
        equipment.setEquipmentStatus(EquipmentStatusEnum.OK.getCode());
        equipment.setDevCode(id);
        equipment.setCreateAt(new Date());
        equipment.setModifyAt(new Date());
        equipment.setCreater("sys");
        equipment.setModifier("sys");
        equipmentMapper.insert(equipment);
        return null;
    }

    @Override
    public BaseListResponse<EquipmentListResponse> getList(EquipmentListRequest request) {

        request.paramCheck();

        Integer total = equipmentMapper.count(request);
        if (total < 1) {
            return BaseListResponse.okWithList(new ArrayList(0), true, 0);
        }

        List<EquipmentListResponse> list = equipmentMapper.query(request);
        for (EquipmentListResponse e : list) {
            e.setEquipmentStatusValue(getValueByCode(e.getEquipmentStatus()));
            try {
                e.setParams(JSON.parseObject(e.getParameter()));
            } catch (Exception var) {
                // 解析失败 就让他去
            }
        }


        return BaseListResponse.okWithList(list, list.size() == request.getPageSize(), total);
    }

    @Override
    public void delete(Integer id) {
        if (id == null || id < 1) {
            throw new IllegalArgumentException(Constant.ParamErrorMessage);
        }
        equipmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public BaseResponse getEquipGroupByProject() {
        List<EquipGroupByProject> list = equipmentMapper.getAllGroupByProject();
        JSONArray result = new JSONArray();

        Set<String> citySet = new HashSet<>();
        for (EquipGroupByProject e : list) {
            String city = e.getCity();
            citySet.add(city);
        }

        for (String city : citySet) {
            JSONObject cityObject = new JSONObject();
            cityObject.put("value", city);
            cityObject.put("label", city);
            JSONArray cityChild = new JSONArray();

            List<EquipGroupByProject> thisCityList = getByCity(city, list);
            if (thisCityList.isEmpty()) continue;
            String projectName = thisCityList.get(0).getProjectName();
            Set<String> projectSet = getProjectSet(projectName, thisCityList);
            for (String project : projectSet) {
                List<EquipGroupByProject> s = getByCityAndProject(city, project, list);
                JSONObject projectObject = new JSONObject();
                projectObject.put("value", projectName);
                projectObject.put("label", projectName);
                projectObject.put("children", s);
                cityChild.add(projectObject);
            }

            cityObject.put("children", cityChild);
            result.add(cityObject);
        }

        return new BaseResponse().okWithBody(result);
    }

    @Override
    public void updateStatus(String id, EquipmentStatusEnum equipmentStatusEnum) {
        equipmentMapper.updateStatusByDevCode(id, equipmentStatusEnum.getCode());
    }

    public List<EquipGroupByProject> getByCity(String city, List<EquipGroupByProject> source) {
        if (source == null) return new ArrayList<>();
        List<EquipGroupByProject> list = new ArrayList<>();
        for (EquipGroupByProject e : source) {
            if (e.getCity() != null && e.getCity().equals(city)) {
                list.add(e);
            }
        }
        return list;
    }

    public static Set<String> getProjectSet(String project, List<EquipGroupByProject> source) {
        Set<String> set = new HashSet<>();
        for (EquipGroupByProject e : source) {
            set.add(e.getProjectName());
        }
        return set;
    }

    public static List<EquipGroupByProject> getByCityAndProject(String city, String project, List<EquipGroupByProject> list) {
        if (list == null || list.size() < 1) return new ArrayList<>();
        List<EquipGroupByProject> result = new ArrayList<>();
        for (EquipGroupByProject e : list) {
            e.setLabel(e.getId().toString());
            e.setValue(e.getId().toString());

            if (e.getCity().equals(city) && e.getProjectName().equals(project)) {
                result.add(e);
            }
        }
        return result;
    }

}
