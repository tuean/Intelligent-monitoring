package com.sys.supervision.entity.db;

import java.util.Date;

public class Snapshot {
    private Integer id;

    private String devCode;

    private String shotImmediately;

    private String taskId;

    private String every;

    private String on;

    private Date createAt;

    private String creater;

    private Date modifyAt;

    private String modifier;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDevCode() {
        return devCode;
    }

    public void setDevCode(String devCode) {
        this.devCode = devCode == null ? null : devCode.trim();
    }

    public String getShotImmediately() {
        return shotImmediately;
    }

    public void setShotImmediately(String shotImmediately) {
        this.shotImmediately = shotImmediately == null ? null : shotImmediately.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getEvery() {
        return every;
    }

    public void setEvery(String every) {
        this.every = every == null ? null : every.trim();
    }

    public String getOn() {
        return on;
    }

    public void setOn(String on) {
        this.on = on == null ? null : on.trim();
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}