package com.procarihana.accounting.Dao.Mapper;

import com.procarihana.accounting.Dao.Provider.RecordSqlProvider;
import com.procarihana.accounting.moudle.presistence.Record;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;


@Mapper
public interface RecordMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO accounting_record(user_id, amount, category, note, status)"
        + "VALUES (#{userId}, #{amount}, #{category}, #{note} ,#{status})")
    int insertRecord(Record record);

    @UpdateProvider(type = RecordSqlProvider.class,method = "updateRecord")
    int updateRecord(Record record);

    @Select("SELECT id, user_id, category, note, amount, status From accounting_record WHERE id = #{id}")
    @Results(
        value = {
            @Result(property = "id",column = "id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "category",column = "category"),
            @Result(property = "status",column = "status"),
            @Result(property = "tagList",javaType = List.class,column = "id",
                many = @Many(select = "com.procarihana.accounting.Dao.Mapper"
                    + ".RecordTagMappingMapper.getTagListByRecordId"))//一定要写绝对路径，相对路径可能找不到
        }
    )
    Record getRecordByRecordId(@Param("id")Long recordId);

}
