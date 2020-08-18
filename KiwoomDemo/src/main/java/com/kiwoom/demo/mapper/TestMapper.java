package com.kiwoom.demo.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.kiwoom.demo.vo.TestVO;

@Repository
@Mapper 
public interface TestMapper { 
	List<TestVO> selectTest(); 
}

