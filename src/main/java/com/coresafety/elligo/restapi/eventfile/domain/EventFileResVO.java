package com.coresafety.elligo.restapi.eventfile.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class EventFileResVO {

    private String deviceId;
    private String eventGroup;
    private List<EventFileVO> files;
}
