package org.pagooo.maven_class_service.util;

public class PageUtilTest {
    public static void main(String[] args) {
        PageUtil pageUtil = new PageUtil(151,10, 3 , "");
        String pager = pageUtil.pager();

        System.out.println(pager);
    }
}
