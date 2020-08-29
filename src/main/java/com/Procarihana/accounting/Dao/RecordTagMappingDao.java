package com.procarihana.accounting.Dao;

import com.procarihana.accounting.moudle.presistence.RecordTagMapping;
import com.procarihana.accounting.moudle.presistence.Tag;

import java.util.List;

public interface RecordTagMappingDao {
    void insertRecordTagMapping(RecordTagMapping recordTagMapping);

    void bathInsertRecordTagMapping(List<Tag> tags, Long recordId);

    void deleteRecordTagMappingListByRecordId(Long recordId);

    List<Tag> getTagListByRecordId(Long recordId);
}
