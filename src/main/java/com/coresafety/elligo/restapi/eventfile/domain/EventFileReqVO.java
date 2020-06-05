package com.coresafety.elligo.restapi.eventfile.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
public class EventFileReqVO {

    private List<MultipartFile> files;
    private String deviceId;
    private String eventDateTime;
    private String eventGroup;
    private String orgFileName;
    private String fileName;
    private String filePath;


}
