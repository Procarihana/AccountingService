package com.procarihana.accounting.Dao.Provider;

import com.procarihana.accounting.moudle.presistence.Record;

import org.apache.ibatis.jdbc.SQL;

public class RecordSqlProvider {
    /**
     * Update record item val specific record.
     *
     * @param record the record need to update
     * @return the sql to execute
     */
    public String updateRecord(final Record record) {
        return new SQL() {
            {
                UPDATE("accounting_record");
                if (record.getAmount() != null) {
                    SET("amount = #{amount}");
                }
                if (record.getCategory() != null) {
                    SET("category = #{category}");
                }
                if (record.getUserId() != null) {
                    SET("user_id= #{userId}");
                }
                if (record.getNote() != null) {
                    SET("note = #{note}");
                }
                if (record.getStatus() != null) {
                    SET("status = #{status}");
                }
            }
        }.toString();
    }

}
