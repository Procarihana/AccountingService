package com.procarihana.accounting.Dao;

import com.procarihana.accounting.moudle.presistence.Record;

public interface RecordDao {
    void createRecord(Record record);

    Record getRecordByRecordId(Long recordId);

    void updateRecord(Record updateRecord);
}
