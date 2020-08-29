package com.procarihana.accounting.Dao.Mapper;

import com.procarihana.accounting.Dao.Provider.TagSqlProvider;
import com.procarihana.accounting.moudle.presistence.Tag;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;


@Mapper
public interface TagMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id") //表示tagId放回insert里面
    @Insert("INSERT INTO accounting_tag(description, user_id, status)"
        + "VALUES (#{description}, #{userId}, #{status})")
    int insert(Tag tag);  //表示插入多少条数据


    @Options(resultSets = "id, description, user_id, status ,create_time, update_time")
    @UpdateProvider(type = TagSqlProvider.class, method = "updateTag")
    int updateTag(Tag tag);

    @Select("SELECT id, description, user_id, status, create_time, update_time "
        + "FROM accounting_tag WHERE description = #{description} ")
    @Results({
        @Result(column = "id", property = "id"),
        @Result(column = "description", property = "description"),
        @Result(column = "user_id", property = "userId"),
        @Result(column = "status", property = "status"),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "update_time", property = "updateTime"),
    })
    Tag getTagByTagDescription(@Param("description") String description, @Param("userId") Long userId);

    @Select("SELECT id, description, user_id, status, create_time, update_time "
        + "FROM accounting_tag WHERE id = #{id} ")
    @Results({
        @Result(column = "id", property = "id"),
        @Result(column = "description", property = "description"),
        @Result(column = "user_id", property = "userId"),
        @Result(column = "status", property = "status"),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "update_time", property = "updateTime"),
    })
    Tag getTagByTagId(@Param("id") Long tagId);

    @SelectProvider(type = TagSqlProvider.class, method = "getTagListByTagIds")
    @Results({
        @Result(column = "id", property = "id"),
        @Result(column = "description", property = "description"),
        @Result(column = "user_id", property = "userId"),
        @Result(column = "status", property = "status"),
    })
    List<Tag> getTagListByTagIds(@Param("id") List<Long> tagIds);
}
