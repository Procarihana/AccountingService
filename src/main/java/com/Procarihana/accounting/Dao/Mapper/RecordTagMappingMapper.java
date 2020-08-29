package com.procarihana.accounting.Dao.Mapper;

import com.procarihana.accounting.moudle.presistence.RecordTagMapping;
import com.procarihana.accounting.moudle.presistence.Tag;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface RecordTagMappingMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO accounting_record_tag_mapping(record_id, tag_id,status)"
        + "VALUES(#{recordId}, #{tagId}, #{status})")
    int insertRecordTagMapping(RecordTagMapping recordTagMapping);

    //INSERT INTO accounting_record_tag_mapping(record_id,tag_id, status) VALUES(1,1,1)(2,1,2)(3,4,8) -> batch insert
    //SELECT  * FROM xx WHERE id in(1,2,5,6) foreach ->  "open='('separator=','close=')'>"
    @Insert({"<script>",
        "INSERT INTO accounting_record_tag_mapping(record_id, tag_id, status) VALUES",
        "<foreach item='item' index='index' collection='recordTagMappings'",
        "open='(' separator='),(' close=')'>",
        "#{item.recordId}, #{item.tagId}, #{item.status}",
        "</foreach>",
        "</script>"})
    //插入XML脚本写复杂的语句（分批操作：数据库特性,而mybatis 的`foreach`就能够支持分批的特性
    int batchRecordTagMapping(@Param("recordTagMappings") List<RecordTagMapping> recordTagMappings);

    @Select("SELECT tag.id, tag.description, tag.status, tag.user_id, artm.record_id "
        + "FROM accounting_tag tag "
        + "LEFT JOIN accounting_record_tag_mapping artm "
        + "ON artm.tag_id = tag.id "
        + "WHERE artm.record_id = #{recordId}")
    List<Tag> getTagListByRecordId(@Param("recordId") Long recordId);

    @Delete("DELETE FROM accounting_record_tag_mapping WHERE record_id = #{recordId}")
    int deleteRecordTagMappingList(@Param("recordId") Long recordId);
}
