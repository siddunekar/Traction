
package com.traction.backend.dto;

import java.time.LocalDateTime;

public class ConnectionResponse {

    private Integer connectionId;
    private String status;
    private LocalDateTime createdAt;

    private Integer senderId;
    private String senderUsername;
    private String senderOccupation;
    private String senderLocation;
    private String senderProfilePhotoUrl;

    private Integer receiverId;
    private String receiverUsername;

    public Integer getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(Integer connectionId) {
        this.connectionId = connectionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getSenderOccupation() {
        return senderOccupation;
    }

    public void setSenderOccupation(String senderOccupation) {
        this.senderOccupation = senderOccupation;
    }

    public String getSenderLocation() {
        return senderLocation;
    }

    public void setSenderLocation(String senderLocation) {
        this.senderLocation = senderLocation;
    }

    public String getSenderProfilePhotoUrl() {
        return senderProfilePhotoUrl;
    }

    public void setSenderProfilePhotoUrl(String senderProfilePhotoUrl) {
        this.senderProfilePhotoUrl = senderProfilePhotoUrl;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }
}
