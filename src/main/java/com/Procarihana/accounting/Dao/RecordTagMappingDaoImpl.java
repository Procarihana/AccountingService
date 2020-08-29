package com.procarihana.accounting.Dao;

import com.procarihana.accounting.Dao.Mapper.RecordTagMappingMapper;
import com.procarihana.accounting.moudle.presistence.RecordTagMapping;
import com.procarihana.accounting.moudle.presistence.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecordTagMappingDaoImpl implements RecordTagMappingDao {
    private final RecordTagMappingMapper recordTagMappingMapper;

    @Override
    public void insertRecordTagMapping(RecordTagMapping recordTagMapping) {
        recordTagMappingMapper.insertRecordTagMapping(recordTagMapping);
    }

    @Override
    public void bathInsertRecordTagMapping(List<Tag> tags, Long recordId) {
        val recordTagMappingList = tags.stream().map(tag -> RecordTagMapping.builder()
            .status(1)
            .tagId(tag.getId())
            .recordId(recordId)
            .build()
        ).collect(Collectors.toList());
        int rows = recordTagMappingMapper.batchRecordTagMapping(recordTagMappingList);
        log.debug("The row inserted:{}", rows);
    }

    @Override
    public void deleteRecordTagMappingListByRecordId(Long recordId) {
        int rows = recordTagMappingMapper.deleteRecordTagMappingList(recordId);
        log.debug("The affected rows for deleting:{}", rows);
    }

    @Override
    public List<Tag> getTagListByRecordId(Long recordId) {
        return recordTagMappingMapper.getTagListByRecordId(recordId);
    }
}
