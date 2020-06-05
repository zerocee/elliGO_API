package com.coresafety.elligo.restapi.eventfile.controller;

import com.coresafety.elligo.restapi.eventfile.domain.EventFileReqVO;
import com.coresafety.elligo.restapi.eventfile.domain.EventFileResVO;
import com.coresafety.elligo.restapi.eventfile.domain.EventFileVO;
import com.coresafety.elligo.restapi.eventfile.service.EventFileService;
import com.coresafety.elligo.restapi.eventfile.service.EventFileServiceImpl;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value= "/api/v1")
public class EventFileController {


    @Value("${upload.path}")
    private String filePath;

    private EventFileServiceImpl fileService;
    private EventFileService eventFileService;

    @Autowired
    EventFileController(
            EventFileService eventFileService
    ) {
        this.eventFileService = eventFileService;
    }

    @PostMapping(value="/files/event-images")
    @ResponseStatus(HttpStatus.CREATED)
    public List<String> eventFileUpload(EventFileReqVO files) throws Exception{

//        List<String> list = new ArrayList<>();
        DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
//        DateFormat sdFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
        Date nowDate = new Date();
        String todayStr = sdFormat.format(nowDate);
//        fileService = new EventFileServiceImpl();
        try {
            for (MultipartFile file : files.getFiles ()) {

                EventFileVO fileVO = new EventFileVO();
                String orgFileName = file.getOriginalFilename();
                String orgFileExtension = orgFileName.substring(orgFileName.lastIndexOf("."));
                String newFileName =  UUID.randomUUID().toString() + orgFileExtension;
                String newFilePath = filePath + todayStr + "/" + files.getDeviceId() + "/";

//                System.out.println("orgFileName ===>"+orgFileName);
//                System.out.println("orgFileExtension ===>"+orgFileExtension);
//                System.out.println("newFileName ===>"+newFileName);
//                System.out.println("newFilePath ===>"+newFilePath);

                fileVO.setDeviceId(files.getDeviceId());
                fileVO.setEventGroup(files.getEventGroup());
                fileVO.setOrgFileName(orgFileName);
                fileVO.setFileName(newFileName);
                fileVO.setFilePath(newFilePath);

                File tmp = new File(newFilePath + newFileName );

                try {
                    FileUtils.copyInputStreamToFile(file.getInputStream(), tmp);
                } catch (IOException e) {
                    //log.error("Error while copying.", e);
                    e.printStackTrace();
                    throw e;
                }
                // TODO
                eventFileService.insertEventFile(fileVO);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }


    @GetMapping(value="/files/event-images-list")
    @ResponseStatus(HttpStatus.OK)
    public List<EventFileVO> eventFileList(EventFileReqVO files) throws Exception{

        EventFileVO eventFileVO = new EventFileVO();
        EventFileResVO eventFileResVO = new EventFileResVO();
        List resultArray = new ArrayList();
        List<EventFileVO> eventFileList = new ArrayList<EventFileVO>();
        try {
            eventFileVO.setDeviceId(files.getDeviceId());
            eventFileVO.setEventGroup(files.getEventGroup());
            eventFileList = eventFileService.selectEventFiles(eventFileVO);

        } catch (Exception e){
            e.printStackTrace();
        }
        return eventFileList;

    }


//    @GetMapping(value="/files/event-image")
//    @ResponseStatus(HttpStatus.OK)
//    public Resource eventFileDownload(EventFileReqVO files) throws Exception{
//
//        List<String> list = new ArrayList<>();
//        try {
//            for (MultipartFile file : files.getFiles ()) {
//                String originalfileName = file.getOriginalFilename();
//                File dest = new File(filePath + originalfileName);
//                file.transferTo(dest);
//                // TODO
//            }
//        } catch (Exception e){
//
//        }
//        return null;
//
//    }
}
