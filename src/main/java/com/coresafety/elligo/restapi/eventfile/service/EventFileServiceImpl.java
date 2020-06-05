package com.coresafety.elligo.restapi.eventfile.service;


import com.coresafety.elligo.restapi.eventfile.domain.EventFileVO;
import com.coresafety.elligo.restapi.eventfile.mapper.EventFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventFileServiceImpl implements EventFileService {

    private EventFileMapper eventFileMapper;
    @Autowired
    EventFileServiceImpl(
            EventFileMapper eventFileMapper
    ) {
        this.eventFileMapper = eventFileMapper;
    }

    public void insertEventFile (EventFileVO eventFileVO) throws Exception {

        try {
            //필수 파라미터 체크
            if (eventFileVO.getDeviceId() == null || "".equals(eventFileVO.getDeviceId())) {
                throw new Exception("필수값이 포함되지 않았습니다. (deviceId)");
            }
            if (eventFileVO.getEventGroup() == null || "".equals(eventFileVO.getEventGroup())) {
                throw new Exception("필수값이 포함되지 않았습니다. (eventGroup)");
            }
            if (eventFileVO.getOrgFileName() == null || "".equals(eventFileVO.getOrgFileName())) {
                throw new Exception("필수값이 포함되지 않았습니다. (orgFileName)");
            }
            if (eventFileVO.getFileName() == null || "".equals(eventFileVO.getFileName())) {
                throw new Exception("필수값이 포함되지 않았습니다. (fileName)");
            }
            if (eventFileVO.getFilePath() == null || "".equals(eventFileVO.getFilePath())) {
                throw new Exception("필수값이 포함되지 않았습니다. (filePath)");
            }
            System.out.println("=============");
            System.out.println(eventFileMapper);
            System.out.println("=============");
            eventFileMapper.insertEventFile(eventFileVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }


    public List<EventFileVO> selectEventFiles (EventFileVO eventFileVO) throws Exception {

        //필수 파라미터 체크
        if(eventFileVO.getDeviceId() == null || "".equals(eventFileVO.getDeviceId())){
            throw new Exception("필수값이 포함되지 않았습니다. (deviceId");
        }
        List<EventFileVO> resultList = eventFileMapper.selectEventFiles(eventFileVO);

//        EventFileResVO eventFileResVO = new EventFileResVO();
//        if(resultList.size() > 0) {
//            String eventGroup = "";
//            for(int i=0; i<resultList.size(); i++){
//                EventFileVO tmpVO = resultList.get(i);
//                if(eventGroup == "" || !eventGroup.equals(tmpVO.getEventGroup()) ) {
//                    eventGroup = tmpVO.getEventGroup();
//                    eventFileResVO = new EventFileResVO();
//                }
//
//            }
//        }

        return resultList;
    }
}
