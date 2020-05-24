package com.github.dev.muzi.kwafoo.config.platform.domain.view;

import com.github.dev.muzi.kwafoo.config.platform.domain.constant.CommonStatusConstant;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Data
public class PageResponseVO<M> extends BaseResponseVO<M> {

    /*
     * 当前页码
     */
    private Long current = 0L;

    /*
     * 全部数据
     */
    private Long total = 0L;

    /*
     * 单页数量
     */
    private Long size = 0L;

    /*
     * 页数据
     */
    private List<M> records = Lists.newArrayList();


    public static <M> PageResponseVO successPage(BaseDataQueryVO page) {
        if (page == null || CollectionUtils.isEmpty(page.getRecords()) || page.getTotal() == 0) {
            return PageResponseVO.failurePage();
        }
        PageResponseVO baseResponseVO = new PageResponseVO();
        baseResponseVO.setStatus(CommonStatusConstant.SUCCESS.getCode());
        baseResponseVO.setMsg(CommonStatusConstant.SUCCESS.getMsg());
        baseResponseVO.setRecords(page.getRecords());
        baseResponseVO.setTotal(page.getTotal());
        baseResponseVO.setSize(page.getSize());
        baseResponseVO.setCurrent(page.getCurrent());
        return baseResponseVO;
    }

    public static <M> PageResponseVO failurePage() {
        PageResponseVO baseResponseVO = new PageResponseVO();
        baseResponseVO.setStatus(CommonStatusConstant.FAILURE.getCode());
        baseResponseVO.setMsg(CommonStatusConstant.FAILURE.getMsg());
        baseResponseVO.setRecords(Lists.newArrayList());
        baseResponseVO.setTotal(0L);
        baseResponseVO.setSize(0L);
        baseResponseVO.setCurrent(0L);
        return baseResponseVO;
    }
}
