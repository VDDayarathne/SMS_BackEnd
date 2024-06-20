package com.sms.sms.dto;

import com.sms.sms.entity.News;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data

public class ResponseData {
    private String status;
    private List<News> data;

    public ResponseData() {}

    public ResponseData(String status, List<News> data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<News> getData() {
        return data;
    }

    public void setData(List<News> data) {
        this.data = data;
    }
}
