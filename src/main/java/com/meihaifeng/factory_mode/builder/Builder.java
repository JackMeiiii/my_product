package com.meihaifeng.factory_mode.builder;

import com.meihaifeng.factory_mode.abstract_mode.MailSender;
import com.meihaifeng.factory_mode.abstract_mode.Sender;
import com.meihaifeng.factory_mode.abstract_mode.SmsSender;

import java.util.ArrayList;
import java.util.List;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/2/7
 * @description
 */
public class Builder {

    public Sender produceMailSender(){
       return new MailSender();
    }

    public Sender produceSmsSender(){
       return new SmsSender();
    }

    public static void main(String args[]) {
        Builder builder = new Builder();
        Sender sender = builder.produceMailSender();
        sender.send();
    }
}
