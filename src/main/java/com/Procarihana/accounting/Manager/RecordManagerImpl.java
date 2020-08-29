package com.procarihana.accounting.Manager;

import com.procarihana.accounting.Dao.RecordDao;
import com.procarihana.accounting.Dao.RecordTagMappingDao;
import com.procarihana.accounting.Dao.TagDao;
import com.procarihana.accounting.converter.presisitenceToCommon.RecordP2CConverter;
import com.procarihana.accounting.exception.InvalidParameterException;
import com.procarihana.accounting.exception.ResourceNotFoundException;
import com.procarihana.accounting.moudle.common.Record;
import com.procarihana.accounting.moudle.presistence.Tag;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RecordManagerImpl implements RecordManager {
    private final RecordDao recordDao;
    private final RecordP2CConverter recordP2CConverter;
    private final TagDao tagDao;
    private final RecordTagMappingDao recordTagMappingDao;

    /**
     * The constructor for RecordManagerImpl.
     *
     * @param recordDao           record dao
     * @param recordP2CConverter  record converter
     * @param tagDao              tag dao
     * @param recordTagMappingDao record tag mapping dao
     */
    @Autowired
    public RecordManagerImpl(RecordDao recordDao,
                             RecordP2CConverter recordP2CConverter, TagDao tagDao,
                             RecordTagMappingDao recordTagMappingDao) {
        this.recordDao = recordDao;
        this.recordP2CConverter = recordP2CConverter;
        this.tagDao = tagDao;
        this.recordTagMappingDao = recordTagMappingDao;
    }

    @Override
    public Record createRecord(Record record) {
        val newRecord = recordP2CConverter.reverse().convert(record);
        record.setStatus("ENABLE");
        //check tag list are valid
        assert newRecord != null;
        val tagIds = newRecord.getTagList().stream().map(Tag::getId).collect(Collectors.toList());
        val tags = tagDao.getTagListByTagIds(tagIds);
        if (tags.isEmpty()) {
            throw new InvalidParameterException(String.format("The tag list %s are not existed.", tagIds));
        }
        //recordTagMapping :(id,recordId,tagId)
        tags.forEach(tag -> {
            if (!tag.getUserId().equals(record.getUserId())) {
                throw new InvalidParameterException("The tag is not matched for user");
            }
            /*
            val recordTagMapping = RecordTagMapping.builder()
                .recordId(newRecord.getId())
                .tagId(tag.getId())
                .status(1)
                .build();
            recordTagMappingDao.insertRecordTagMapping(recordTagMapping);
            //mysql request io each 每次都需要mysql io 时间就会增长，而批量输入就能够减少io的耗能
             */
        });
        recordDao.createRecord(newRecord);
        recordTagMappingDao.bathInsertRecordTagMapping(tags, newRecord.getId());
        return getRecordByRecordId(newRecord.getId());
    }

    @Override
    public Record getRecordByRecordId(Long recordId) {
        return Optional.ofNullable(recordDao.getRecordByRecordId(recordId))
            .map(recordP2CConverter::convert)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format("The related record[%s] was not found.", recordId)));
    }


    @Override
    public Record updateRecord(Record record) {
        val updateRecord = recordP2CConverter.reverse().convert(record);
        val existingRecord = Optional.ofNullable(
            recordDao.getRecordByRecordId(updateRecord.getId()))
            .map(recordP2CConverter::convert)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format("The related record id [%s] was not found.", record.getId())));
        if (!record.getUserId().equals(existingRecord.getUserId())) {
            throw new InvalidParameterException(
                String.format("The related id[%s]doesn't belong to user id:[%s]",
                    record.getId(), existingRecord.getId()));
        }
        assert updateRecord != null;
        if (updateRecord.getTagList() != null && !updateRecord.getTagList().equals(existingRecord.getTagList())) {
            val tagIds = updateRecord.getTagList()
                .stream()
                .map(Tag::getId)
                .collect(Collectors.toList());
            //Check tag is  valid

            val tags = tagDao.getTagListByTagIds(tagIds);
            tags.stream().filter(tag -> !tag.getUserId().equals(record.getUserId()))
                .findAny()
                .ifPresent(tag -> {
                    throw new InvalidParameterException(
                        String.format("The tag id [%s] doesn't belong to user id:[%s]",
                            tag.getId(), record.getUserId()));
                });

            //Deleting the existing mappings
            deleteExistingMappings(record);
            //Creating new mappings
            creatingNewMappings(tags, record);
        }
        recordDao.updateRecord(updateRecord);
        return getRecordByRecordId(record.getId());   //写完立刻读只能在SQL里面做，NOSQL的最终一致性并不能满足马上显示更新后结果的要求，除非有强一致性设置


    }

    private void creatingNewMappings(List<Tag> tags, Record record) {
        recordTagMappingDao.bathInsertRecordTagMapping(tags, record.getId());
    }

    private void deleteExistingMappings(Record record) {
        recordTagMappingDao.deleteRecordTagMappingListByRecordId(record.getId());
    }


}
