package com.procarihana.accounting.Manager;

import com.procarihana.accounting.moudle.common.UserInfo;

public interface UserInfoManager {
    /**
     * Get user Information by user id.
     *
     * @param userId the specific user id.
     */

    UserInfo getUserInfoByUserID(Long userId);

    /**
     * Get user information by user name.
     *
     * @param username
     * <b>must not</b> be null
     */

    UserInfo getUserInfoByUsername(String username);

    /**
     * Login with username and password.
     *
     * @param username
     * <b>must not</b> be null
     * @param password
     * <b>must not</b> be null
     */
    void login(String username, String password);

    /**
     * Register new user with username and password.
     *
     * @param username
     * <b>must not</b> be null
     * @param password
     * <b>must not</b> be null
     * @return
     */
    UserInfo register(String username, String password);

}

