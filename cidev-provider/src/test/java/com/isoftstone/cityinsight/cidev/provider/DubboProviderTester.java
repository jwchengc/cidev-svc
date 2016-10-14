package com.isoftstone.cityinsight.cidev.provider;

/**
 * Created by xmxu on 16-10-13.
 */
public class DubboProviderTester {
    public static void main(String[] args) {
        // add `javaconfig` to args
        String[] arg = new String[]{"javaconfig"};
        com.alibaba.dubbo.container.Main.main(arg);
    }
}
