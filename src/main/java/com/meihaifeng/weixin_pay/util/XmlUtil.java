package com.meihaifeng.weixin_pay.util;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 杭州新苗网络科技有限公司
 *
 * @author meihf
 * @create 2017/5/11
 * @description
 */
public class XmlUtil {
    private static final Log logger = LogFactory.getLog(XmlUtil.class);

    public static Map<String, Object> parseXml(InputStream inputStream) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();
            List<Element> elements = root.elements();

            for (Element e : elements) {
                map.put(e.getName(), e.getText());
            }
        } catch (Exception e) {
            logger.error(String.valueOf(e.getCause()));
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.error(String.valueOf(e.getCause()));
            }
        }
        return map;
    }

    public static Map<String, Object> parseXml(String xml) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            SAXReader builder = new SAXReader();
            Document document = builder.read(new StringReader(xml));
            Element root = document.getRootElement();
            List<Element> elements = root.elements();

            for (Element e : elements) {
                map.put(e.getName(), e.getText());
            }

            return map;
        } catch (Exception e) {
            logger.error(String.valueOf(e.getCause()));
            return null;
        }
    }

    public static String toXml(Map<String, Object> data) {
        Document document = DocumentHelper.createDocument();
        Element nodeElement = document.addElement("xml");
        for (String key : data.keySet()) {
            Element keyElement = nodeElement.addElement(key);
            keyElement.setText(String.valueOf(data.get(key)));
        }
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            OutputFormat format = new OutputFormat("   ", true, "UTF-8");
            XMLWriter writer = new XMLWriter(out, format);
            writer.write(document);
            return out.toString("UTF-8");
        } catch (Exception ex) {
            logger.error(String.valueOf(ex.getCause()));
        }
        return null;
    }


    public static String toXml(Map<String, String> headData, Map<String, String> bodyData) {
        Document document = DocumentHelper.createDocument();
        Element message = document.addElement("message");
        Element head = message.addElement("head");
        for (String key : headData.keySet()) {
            Element keyElement = head.addElement(key);
            keyElement.setText(headData.get(key));
        }
        Element body = message.addElement("body");
        for (String key : bodyData.keySet()) {
            Element keyElement = body.addElement(key);
            keyElement.setText(bodyData.get(key));
        }
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            OutputFormat format = new OutputFormat("", true, "UTF-8");
            XMLWriter writer = new XMLWriter(out, format);
            writer.write(document);
            return out.toString("UTF-8");
        } catch (Exception ex) {
            logger.error(String.valueOf(ex.getCause()));
        }
        return null;
    }


}
