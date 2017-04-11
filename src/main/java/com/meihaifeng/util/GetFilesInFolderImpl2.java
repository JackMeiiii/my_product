package com.meihaifeng.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/1/11
 * @description
 */
@Component
public class GetFilesInFolderImpl2 {
    private Logger log = LoggerFactory.getLogger(GetFilesInFolderImpl2.class);
    //  headPortraitImageTeam =http://192.168.54.161/images/
    @Value("${headPortrait.imageTeam}")
    private String headPortraitImageTeam;

    private static final String ROOT_ELEMENT_NAME = "body";

    public List<String> gainHeadPortraitImages() {
        StringBuffer sb = analyzeUrlToXMLFile(headPortraitImageTeam);
        if (sb.length() == 0) {
            return null;
        }
        return xmlFileToObjList(sb);
    }

    public List<String> xmlFileToObjList(StringBuffer sb) {
        List<String> arrList = new ArrayList<String>();
        try {
            Document document = DocumentHelper.parseText(sb.toString());
            Element root = document.getRootElement();
            getXmlElementValueList(root.elements(ROOT_ELEMENT_NAME), arrList);
        } catch (DocumentException e) {
            e.printStackTrace();
            log.debug(e.getMessage(), e);
        }
        return arrList;
    }

    public StringBuffer analyzeUrlToXMLFile(String urlPath) {

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        try {//

            URL url = new URL(urlPath);
            URLConnection rulConnection = url.openConnection();
            HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
            is = httpUrlConnection.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String s = null;
            while ((s = br.readLine()) != null) {
                if (!s.contains("DOCTYPE")) {
                    sb.append(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage(), e);
        } finally {

            try {
                br.close();
                isr.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.debug(e.getMessage(), e);
            }
        }
        return sb;
    }

    @SuppressWarnings("rawtypes")
    public void getXmlElementValueList(List ls, List<String> arrs) {
        Iterator it = ls.iterator();
        while (it.hasNext()) {
            Element el = (Element) it.next();
            if (el.getName().equals("a")) {
                // "/"是用来判断是目录还是文件
                if (!el.attribute("href").getData().equals("/")) {
                    arrs.add(headPortraitImageTeam + el.getText().trim());
                }
            }
            if (el.elements().size() > 0) {
                getXmlElementValueList(el.elements(), arrs);
            }
        }
    }

    public String getHeadPortraitImageTeam() {
        return headPortraitImageTeam;
    }

    public void setHeadPortraitImageTeam(String headPortraitImageTeam) {
        this.headPortraitImageTeam = headPortraitImageTeam;
    }
}
