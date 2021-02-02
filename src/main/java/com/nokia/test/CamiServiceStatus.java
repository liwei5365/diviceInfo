package com.nokia.test;

import java.io.Serializable;

public class CamiServiceStatus implements Serializable {
    private String messageType = "CamiServiceStatus";
    private String cpuUsed;
    private String memUsed;
    private String hDUsed;
    private String freeMomery;
    private String maxMomery;
    private String totalMomery;
    private Integer availableProcessors;
    private String camiHostIp;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getCpuUsed() {
        return cpuUsed;
    }

    public void setCpuUsed(String cpuUsed) {
        this.cpuUsed = cpuUsed;
    }

    public String getMemUsed() {
        return memUsed;
    }

    public void setMemUsed(String memUsed) {
        this.memUsed = memUsed;
    }

    public String gethDUsed() {
        return hDUsed;
    }

    public void sethDUsed(String hDUsed) {
        this.hDUsed = hDUsed;
    }

    public String getFreeMomery() {
        return freeMomery;
    }

    public void setFreeMomery(String freeMomery) {
        this.freeMomery = freeMomery;
    }

    public String getMaxMomery() {
        return maxMomery;
    }

    public void setMaxMomery(String maxMomery) {
        this.maxMomery = maxMomery;
    }

    public String getTotalMomery() {
        return totalMomery;
    }

    public void setTotalMomery(String totalMomery) {
        this.totalMomery = totalMomery;
    }

    public Integer getAvailableProcessors() {
        return availableProcessors;
    }

    public void setAvailableProcessors(Integer availableProcessors) {
        this.availableProcessors = availableProcessors;
    }

    public String getCamiHostIp() {
        return camiHostIp;
    }

    public void setCamiHostIp(String camiHostIp) {
        this.camiHostIp = camiHostIp;
    }

    @Override
    public String toString() {
        return "CamiServiceStatus{" +
                "messageType='" + messageType + '\'' +
                ", cpuUsed='" + cpuUsed + '\'' +
                ", memUsed='" + memUsed + '\'' +
                ", hDUsed='" + hDUsed + '\'' +
                ", freeMomery='" + freeMomery + '\'' +
                ", maxMomery='" + maxMomery + '\'' +
                ", totalMomery='" + totalMomery + '\'' +
                ", availableProcessors=" + availableProcessors +
                ", camiHostIp='" + camiHostIp + '\'' +
                '}';
    }
}
