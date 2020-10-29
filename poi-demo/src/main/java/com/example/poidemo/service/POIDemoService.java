package com.example.poidemo.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wsHawk
 * @Title: POIDemoService
 * @ProjectName poi-demo
 * @Description: TODO
 * @since 2020/10/28 21:31
 */
public interface POIDemoService {
    void export(HttpServletResponse response);
}
