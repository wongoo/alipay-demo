package com.github.wongoo.alipay.demo;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayFundAccountQueryModel;
import com.alipay.api.request.AlipayFundAccountQueryRequest;
import com.alipay.api.response.AlipayFundAccountQueryResponse;

/**
 * @author gelnyang
 */
public class AlipayFundQuery {

    public void queryFund() throws AlipayApiException {
        AlipayFundAccountQueryModel model = new AlipayFundAccountQueryModel();

        String accountUserId = Alipayer.properties().getProperty("alipay.fund.user_id");
        model.setAlipayUserId(accountUserId);
        model.setAccountType("ACCTRANS_ACCOUNT");

        AlipayFundAccountQueryRequest request = new AlipayFundAccountQueryRequest();
        request.setBizModel(model);

        System.out.println("调用请求: " + JSON.toJSONString(request));

        AlipayFundAccountQueryResponse response = Alipayer.client().certificateExecute(request);

        System.out.println("调用结果: " + JSON.toJSONString(response));
    }
}
