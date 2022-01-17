package com.github.wongoo.alipay.demo;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayOpenOperationOpenbizmockBizQueryModel;
import com.alipay.api.request.AlipayOpenOperationOpenbizmockBizQueryRequest;
import com.alipay.api.response.AlipayOpenOperationOpenbizmockBizQueryResponse;

/**
 * @author gelnyang
 */
public class AlipayBizMock {

    public void bizMock() throws AlipayApiException {
        AlipayOpenOperationOpenbizmockBizQueryModel model = new AlipayOpenOperationOpenbizmockBizQueryModel();
        model.setBizNo("test");

        AlipayOpenOperationOpenbizmockBizQueryRequest request = new AlipayOpenOperationOpenbizmockBizQueryRequest();
        request.setBizModel(model);

        System.out.println("调用请求: " + JSON.toJSONString(request));

        AlipayOpenOperationOpenbizmockBizQueryResponse response = Alipayer.client().certificateExecute(request);

        System.out.println("调用结果: " + JSON.toJSONString(response));
    }
}
