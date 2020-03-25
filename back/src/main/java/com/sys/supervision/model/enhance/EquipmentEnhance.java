package com.sys.supervision.model.enhance;

import com.sys.supervision.entity.db.Equipment;

public class EquipmentEnhance extends Equipment {

    private String equipmentStatusValue;

    private String projectName;


    public String getEquipmentStatusValue() {
        return equipmentStatusValue;
    }

    public void setEquipmentStatusValue(String equipmentStatusValue) {
        this.equipmentStatusValue = equipmentStatusValue;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
