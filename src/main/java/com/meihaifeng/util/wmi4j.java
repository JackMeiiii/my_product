package com.meihaifeng.util;//package com.drore.cloud.gis.service.impl;
//
///**
// * 浙江卓锐科技股份有限公司
// *
// * @author meihf
// * @create 2017/1/11
// * @description
// */
//public class wmi4j {
//
//    public static void main(String args[]) {
//        // 设定连接参数
//        String server = "192.168.1.201";
//        String username = "administrator";
//        String password = "password";
//        String namespace = "root\\cimv2";
//
//        // 构建连接器
//        SWbemLocator locator = new SWbemLocator(server, username, password, namespace);
//
//        try {
//            // 连接远程服务器
//            SWbemServices wbemServices = locator.connectServer();
//
//            // 遍历服务列表
//            SWbemObjectSet services = wbemServices.instancesOf("Win32_Service");
//            System.out.println("服务数量： " + services.getCount());
//            Iterator<SWbemObject> iterator = services.iterator();
//            while (iterator.hasNext()) {
//                SWbemObject service = iterator.next();
//                System.out.println(service.getObjectText());
//                System.out.println("-----------------------------------------------");
//                break; // 服务很多，就只打一个吧
//            }
//
//            // 查询Windows开头的服务
//            SWbemObjectSet winServices = wbemServices
//                    .execQuery("select * from Win32_Service where DisplayName like 'Windows%'");
//            System.out.println("Windows开头的服务数：" + winServices.getCount());
//
//            // 通过服务名，直接获取服务。
//            // 注意：服务名不是services.msc列表里显示的名称，显示的名称是DisplayName属性，
//            // 而get方法必须使用主键属性Name. 例如：Application Management服务，
//            // 在services.msc是这样显示的, 但它服务名是AppMgmt, 可以通过属性查看。
//            SWbemObject dhcpClient = wbemServices.get("Win32_Service.Name='AppMgmt'");
//            System.out.println("服务名：" + dhcpClient.getPropertyByName("Name").getStringValue());
//            System.out.println("显示名：" + dhcpClient.getPropertyByName("DisplayName").getStringValue());
//            // 获取服务状态
//            System.out.println("状态: " + dhcpClient.getPropertyByName("State").getStringValue());
//
//            // 启动服务
//            dhcpClient.execMethod("StartService");
//            System.out.println("启动后的状态: "
//                    + wbemServices.get("Win32_Service.Name='AppMgmt'").getPropertyByName("State").getStringValue());
//
//            // 停止服务
//            dhcpClient.execMethod("StopService");
//            System.out.println("再次停止后的状态: "
//                    + wbemServices.get("Win32_Service.Name='AppMgmt'").getPropertyByName("State").getStringValue());
//
//        } catch (WMIException e) {
//            e.printStackTrace();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//    }
//}
