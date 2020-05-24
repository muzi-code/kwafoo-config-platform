package com.github.dev.muzi.kwafoo.config.platform.domain.view;

import com.github.dev.muzi.kwafoo.config.platform.domain.exception.RequestArgumentsException;
import lombok.Data;

/**
 * 【VO实体类】通用的基础BaseQueryVO
 * Create by Muzi Li on 2019-11-27
 */
@Data
public abstract class BaseQueryVO {

    public abstract boolean isValidParams() throws RequestArgumentsException;
}
