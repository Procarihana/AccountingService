package com.procarihana.accounting.Dao.Provider;

import com.procarihana.accounting.moudle.presistence.Tag;

import com.google.common.base.Joiner;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;


/**
 * Update tag item via specific tag.
 *
 * @param tag the tag item need to update.
 * @Return the sql to execute.
 */
public class TagSqlProvider {
    /**
     * Update tag item via specific tag.
     *
     * @param tag the tat item need to update
     * @return the sql to execute
     */
    public String updateTag(final Tag tag) {
        return new SQL() {
            {
                UPDATE("accounting_tag");
                if (tag.getDescription() != null) {
                    SET("description = #{description}");
                }
                if (tag.getStatus() != null) {
                    SET("status = #{status}");
                }
                if (tag.getUserId() != null) {
                    SET("user_id = #{userId}");
                }
                WHERE("id = #{id}");
            }

        }.toString();
    }

    /**
     * Get tag list by ids.
     *
     * @param tagIds the specific tag id list
     * @return the SQl to execute
     */
    //select id ,description from table xx where id in (1,2,45,891,12);
    //  select id ,description from table xx where id in(select Aid from tableB )
    public String getTagListByTagIds(@Param("id") List<Long> tagIds) {
        return new SQL() {
            {
                SELECT("id", "description", "user_id", "status");
                FROM("accounting_tag");
                WHERE(String.format("id in (%s)", Joiner.on(",").skipNulls().join(tagIds)));
            }
        }.toString();
    }
}
