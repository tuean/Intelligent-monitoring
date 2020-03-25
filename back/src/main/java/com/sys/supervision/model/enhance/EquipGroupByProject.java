package com.sys.supervision.model.enhance;

import com.sys.supervision.entity.db.Equipment;

public class EquipGroupByProject extends Equipment {

    private String projectName;

    private String city;

    private String label;

    private String value;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
