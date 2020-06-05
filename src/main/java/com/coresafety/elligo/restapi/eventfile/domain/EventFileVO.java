package com.coresafety.elligo.restapi.eventfile.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EventFileVO {
    private String Id;
    private String deviceId;
    private String eventDateTime;
    private String eventGroup;
    private String orgFileName;
    private String fileName;
    private String filePath;
    private String createDateTime;
    private String removeDateTime;
}
