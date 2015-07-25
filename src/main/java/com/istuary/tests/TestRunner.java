package com.istuary.tests;

import com.istuary.utilities.XmlUtil;

/**
 * Created by jliu on 22/07/15.
 */
public class TestRunner {

    public static void main(String[] args) throws Exception {
        System.out.println("This is Test.");
        XmlUtil.createXml();
        XmlUtil.autoRunXml();
        System.out.println("This is End.");
    }

}
