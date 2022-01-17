package com.github.wongoo.alipay.demo;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayFundTransUniTransferModel;
import com.alipay.api.domain.Participant;
import com.alipay.api.request.AlipayFundTransUniTransferRequest;
import com.alipay.api.response.AlipayFundTransUniTransferResponse;

/**
 * @author gelnyang
 */
public class AlipayFundTransfer {

    public void fundTransfer() throws AlipayApiException {
        AlipayFundTransUniTransferModel model = new AlipayFundTransUniTransferModel();
        String bizNo = "" + System.currentTimeMillis();

        String receiveAccount = Alipayer.properties().getProperty("alipay.transfer.receive_account");
        String receiveAccountName = Alipayer.properties().getProperty("alipay.transfer.receive_account_name");

        model.setOutBizNo(bizNo);
        model.setTransAmount("1.00");
        model.setProductCode("TRANS_ACCOUNT_NO_PWD");
        model.setBizScene("DIRECT_TRANSFER");
        model.setOrderTitle("转账");
        Participant payee = new Participant();
        payee.setIdentityType("ALIPAY_LOGON_ID");
        payee.setIdentity(receiveAccount);
        payee.setName(receiveAccountName);
        model.setPayeeInfo(payee);
        model.setRemark("单笔转账");

        AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
        request.setBizModel(model);

        System.out.println("调用请求: " + JSON.toJSONString(request));

        AlipayFundTransUniTransferResponse response = Alipayer.client().certificateExecute(request);

        System.out.println("调用结果: " + JSON.toJSONString(response));
    }
}
