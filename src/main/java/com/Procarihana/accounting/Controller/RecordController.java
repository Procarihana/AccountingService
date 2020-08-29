package com.procarihana.accounting.controller;

import com.procarihana.accounting.Manager.RecordManager;
import com.procarihana.accounting.Manager.UserInfoManager;
import com.procarihana.accounting.converter.commonToService.RecordC2SConverter;
import com.procarihana.accounting.exception.InvalidParameterException;
import com.procarihana.accounting.moudle.service.Record;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1.0/records")
public class RecordController {
    private final RecordManager recordManager;
    private final RecordC2SConverter recordC2SConverter;
    private final UserInfoManager userInfoManager;

    /**
     * Controller for RecordController.
     *
     * @param recordManager      record manager
     * @param recordC2SConverter record converter
     * @param userInfoManager    user info manager
     */
    @Autowired
    public RecordController(RecordManager recordManager,
                            RecordC2SConverter recordC2SConverter,
                            UserInfoManager userInfoManager) {
        this.recordManager = recordManager;
        this.recordC2SConverter = recordC2SConverter;
        this.userInfoManager = userInfoManager;
    }

    /**
     * Create record with related information.
     *
     * @param record record information to create
     * @return the created record
     */
    @PostMapping(produces = "application/json", consumes = "application/json")
    public Record createRecord(@RequestBody Record record) {
        if (checkRecord(record)) {  //数据插入前一定要在最开始就做筛选
            throw new InvalidParameterException("invalid record to create.");
        }
        val recordInCommon = recordC2SConverter.reverse().convert(record);
        val result = recordManager.createRecord(recordInCommon);
        return recordC2SConverter.convert(result);

    }

    private boolean checkRecord(@RequestBody Record record) {
        return record.getTagList() == null
            || record.getAmount() == null
            || record.getCategory() == null
            || record.getUserId() == null;
    }

    /**
     * Get record info by record id.
     * @param recordId  specific record id
     * @return  teh relate record info
     */
    @GetMapping(path = "/{id}", produces = "application/json")
    public Record getRecordByRecordId(@PathVariable("id") Long recordId) {
        if (recordId == null || recordId <= 0L) {
            throw new InvalidParameterException("The record id must be not empty or positive.");
        }
        val result = recordManager.getRecordByRecordId(recordId);
        return recordC2SConverter.convert(result);
    }

    /**
     * Update record info for specific record.
     * @param recordId  the specific record id
     * @param record  the specific info
     * @return  the update record info
     */

    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public Record updateRecord(@PathVariable("id") Long recordId, @RequestBody Record record) {
        if (recordId == null || recordId <= 0L) {
            throw new InvalidParameterException("The record id must be not empty or positive.");
        }
        if (record.getUserId() == null || record.getUserId() <= 0L) {
            throw new InvalidParameterException("The user id is empty or invalid");
        }
        checkUserIdIsExisting(record);
        record.setId(recordId);
        val recordC = recordC2SConverter.reverse().convert(record);
        val result = recordManager.updateRecord(recordC);
        return recordC2SConverter.convert(recordC);
    }

    private void checkUserIdIsExisting(Record record) {
        userInfoManager.getUserInfoByUserID(record.getUserId());
    }
}
