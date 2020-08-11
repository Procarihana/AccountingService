package com.Procarihana.AccountingService.Manager;

import com.Procarihana.AccountingService.Moudle.common.UserInfo;

public interface UserInfoManager {
    /**
     * Get user Information by user id.
     * @param userId the specific user id.
     */

    UserInfo getUserInfoByUserID(Long userId);
}
