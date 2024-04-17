package com.BookingServices.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table
public class Attachment {
    @Id
    private Integer attachment_id;
    private LocalDate upload_at;
    private String  file_url;

    public Attachment() {
    }

    public Attachment(Integer attachment_id, LocalDate upload_at, String file_url) {
        this.attachment_id = attachment_id;
        this.upload_at = upload_at;
        this.file_url = file_url;
    }

    public Integer getAttachment_id() {
        return attachment_id;
    }

    public void setAttachment_id(Integer attachment_id) {
        this.attachment_id = attachment_id;
    }

    public LocalDate getUpload_at() {
        return upload_at;
    }

    public void setUpload_at(LocalDate upload_at) {
        this.upload_at = upload_at;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }
}
