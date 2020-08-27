package com.procarihana.accounting.Dao.Provider;

import com.procarihana.accounting.moudle.presistence.Tag;

import org.apache.ibatis.jdbc.SQL;

/**
 * Update tag item via specific tag.
 *
 * @param tag the tag item need to update.
 * @Return the sql to execute.
 */
public class TagSqlProvider {
    /**
     * Update tag item via specific tag.
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
}
