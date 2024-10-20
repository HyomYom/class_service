package org.pagooo.maven_class_service.course.controller;

import org.pagooo.maven_class_service.util.PageUtil;

public class BaseController {
    public String getPagerHtml(long totalCount, long pageSize, long pageIndex, String queryString){
        PageUtil pageUtil = new PageUtil(pageSize, pageIndex, totalCount, queryString);;
        return pageUtil.pager();

    }
}
