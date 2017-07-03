package com.meihaifeng.common;

/**
 * Created by Loysen on 2017/5/3.
 *  动态创建表
 */
public class TableNameUtils  {

    /**
     * 获取微信表名
     * */
    public  static  String getChatRecordTableName(String uid){
        return "duoduo_machine_uid"+uid+"_chat_record";
    }

    public  static  String getWxuserTableName(String uid){
        return "duoduo_machine_uid"+uid+"_wxuser";
    }

    public  static  String getMassinformationTableName(String uid){
        return "duoduo_machine_uid"+uid+"_massinformation";
    }

}
