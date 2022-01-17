package com.github.wongoo.alipay.demo;

import com.alipay.api.AlipayApiException;

/**
 * @author wongoo
 */
public class AlipayDemo {

    public static void main(String[] args) throws AlipayApiException {
        // new AlipayBizMock().bizMock();
        // new AlipayFundQuery().queryFund();
        new AlipayFundTransfer().fundTransfer();
    }

}
