package com.procarihana.accounting.Manager;

import com.procarihana.accounting.moudle.common.UserInfo;

public interface UserInfoManager {
    /**
     * Get user Information by user id.
     * @param userId the specific user id.
     */

    UserInfo getUserInfoByUserID(Long userId);
}
