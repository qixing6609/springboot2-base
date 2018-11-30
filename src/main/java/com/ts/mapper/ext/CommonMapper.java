package com.ts.mapper.ext;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface CommonMapper{

	@Select(" ${sql} ")
	List<Map<String,Object>> execSql(@Param("sql") String sql);
	
	@Update(" ${sql} ")
	int updateSql(@Param("sql") String sql);

	@Select(" ${sql} ")
	Map<String,Object> mapSql(@Param("sql") String sql);
}
