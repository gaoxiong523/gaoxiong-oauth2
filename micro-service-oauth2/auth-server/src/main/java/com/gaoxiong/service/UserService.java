package com.gaoxiong.service;

import com.gaoxiong.entity.SysUser;

/**
 * @author ChengJianSheng
 * @date 2019-02-12
 */
public interface UserService {

    SysUser getByUsername ( String username );
}
