package com.sys.supervision.entity.db;

import java.util.Date;

public class Picture {
    private Integer id;

    private String cameraId;

    private String taskId;

    private Integer projectId;

    private String path;

    private Date picTime;

    private Integer picStatus;

    private Long size;

    private Date createAt;

    private String creater;

    private Date modifyAt;

    private String modifier;

    private String base64;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId == null ? null : cameraId.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Date getPicTime() {
        return picTime;
    }

    public void setPicTime(Date picTime) {
        this.picTime = picTime;
    }

    public Integer getPicStatus() {
        return picStatus;
    }

    public void setPicStatus(Integer picStatus) {
        this.picStatus = picStatus;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
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

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64 == null ? null : base64.trim();
    }
}