package com.ts.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.ts.model.Config;
import com.ts.model.ConfigExample;

public interface ConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ydj_config
     *
     * @mbggenerated
     */
    @SelectProvider(type=ConfigSqlProvider.class, method="countByExample")
    int countByExample(ConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ydj_config
     *
     * @mbggenerated
     */
    @DeleteProvider(type=ConfigSqlProvider.class, method="deleteByExample")
    int deleteByExample(ConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ydj_config
     *
     * @mbggenerated
     */
    @Delete({
        "delete from ydj_config",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ydj_config
     *
     * @mbggenerated
     */
    @Insert({
        "insert into ydj_config (category, keyword, ",
        "value, remark, status, ",
        "sort)",
        "values (#{category,jdbcType=VARCHAR}, #{keyword,jdbcType=VARCHAR}, ",
        "#{value,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, ",
        "#{sort,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(Config record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ydj_config
     *
     * @mbggenerated
     */
    @InsertProvider(type=ConfigSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(Config record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ydj_config
     *
     * @mbggenerated
     */
    @SelectProvider(type=ConfigSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="keyword", property="keyword", jdbcType=JdbcType.VARCHAR),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<Config> selectByExample(ConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ydj_config
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "id, category, keyword, value, remark, status, sort",
        "from ydj_config",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="keyword", property="keyword", jdbcType=JdbcType.VARCHAR),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    Config selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ydj_config
     *
     * @mbggenerated
     */
    @UpdateProvider(type=ConfigSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Config record, @Param("example") ConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ydj_config
     *
     * @mbggenerated
     */
    @UpdateProvider(type=ConfigSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Config record, @Param("example") ConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ydj_config
     *
     * @mbggenerated
     */
    @UpdateProvider(type=ConfigSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Config record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ydj_config
     *
     * @mbggenerated
     */
    @Update({
        "update ydj_config",
        "set category = #{category,jdbcType=VARCHAR},",
          "keyword = #{keyword,jdbcType=VARCHAR},",
          "value = #{value,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "sort = #{sort,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Config record);
}