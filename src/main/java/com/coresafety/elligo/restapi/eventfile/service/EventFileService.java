package com.coresafety.elligo.restapi.eventfile.service;


import com.coresafety.elligo.restapi.eventfile.domain.EventFileVO;

import java.util.List;

public interface EventFileService {

    public List<EventFileVO> selectEventFiles(EventFileVO eventFileVO) throws Exception ;
    public void insertEventFile(EventFileVO eventFileVO) throws Exception ;
}
