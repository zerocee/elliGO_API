package com.coresafety.elligo.restapi.eventfile.mapper;

import com.coresafety.elligo.restapi.eventfile.domain.EventFileVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EventFileMapper {

    List<EventFileVO> selectEventFiles(EventFileVO eventFileVO);

    void insertEventFile(EventFileVO eventFileVO);
}
