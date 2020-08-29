package com.procarihana.accounting.Manager;

import com.procarihana.accounting.moudle.common.Record;

public interface RecordManager {
    Record createRecord(Record record);

    Record getRecordByRecordId(Long recordId);

    Record updateRecord(Record record);
}
