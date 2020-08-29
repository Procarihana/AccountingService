package com.procarihana.accounting.Dao;

import com.procarihana.accounting.Dao.Mapper.RecordMapper;
import com.procarihana.accounting.moudle.presistence.Record;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecordDaoImpl implements RecordDao {
    private final RecordMapper recordMapper;

    @Override
    public void createRecord(Record record) {
        recordMapper.insertRecord(record);
    }

    @Override
    public Record getRecordByRecordId(Long recordId) {
        return recordMapper.getRecordByRecordId(recordId);
    }

    @Override
    public void updateRecord(Record updateRecord) {
        recordMapper.updateRecord(updateRecord);
    }
}
