package com.BookingServices.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class BodyDTO {

    private FromDTO from;
    private List<FromDTO> to;
    private Map<String,String> answer_url;
    private List<Object> actions;
}
