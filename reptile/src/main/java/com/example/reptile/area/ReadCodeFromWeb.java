package com.example.reptile.area;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


/**
 * 从国家统计局网站爬取2013年12位到村级别的行政区划代码
 * @author 杨志龙
 * blog:http://www.cnblogs.com/yangzhilong
 *
 */
public class ReadCodeFromWeb {
    // public static final String baseUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/";
    public static final String baseUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/";
    //设置utf-8发现有部分字符有乱码
    public static final String CHARSET = "GBK";

    public static StringBuffer result = new StringBuffer();


    /**
     * 读省的信息
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String url = baseUrl + "index.html";
        //如果需要设置代理
        //initProxy("10.10.13.200", "80");
        String str = getContent(url).toUpperCase();
        String[] arrs = str.split("<A");

        for (String s : arrs) {
            if (s.indexOf("HREF") != -1 && s.indexOf(".HTML") != -1) {
                String a = s.substring(7, s.indexOf("'>"));
                String name = s.substring(s.indexOf("'>")+2, s.indexOf("<BR/>"));
                System.out.println(name);
//                if(!"河南省".equals(name)){
//                    continue;
//                }

                System.out.println("爬取:"+name);
                boolean success = false;
                while(!success) {
                    try {
                        readShi(a,name);
                        success = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                        success = false;
                        Thread.sleep(60000);
                    }
                }

            }
        }
    }

    /**
     * 读市的数据
     * @param list
     * @throws Exception
     */
    public static void readShi(String url,String paretid) throws Exception{
        String content = getContent(baseUrl+url).toUpperCase();
        String[] citys = content.split("CITYTR");
        //'><TD><A HREF='11/1101.HTML'>110100000000</A></TD><TD><A HREF='11/1101.HTML'>市辖区</A></TD></td><TR CLASS='
        for(int c=1,len=citys.length; c<len; c++){
            String[] strs = citys[c].split("<A HREF='");
            String cityUrl = null;
            String areaid = "";
            String areaname = "";
            for(int si = 1; si < 3; si++){
                if(si==1){//取链接和编码
                    cityUrl = strs[si].substring(0, strs[si].indexOf("'>"));
                    areaid = strs[si].substring(strs[si].indexOf("'>")+2, strs[si].indexOf("</A>"));
                }else{
                    areaname = strs[si].substring(strs[si].indexOf("'>")+2, strs[si].indexOf("</A>"));
                    System.out.println("爬取:"+strs[si].substring(strs[si].indexOf("'>")+2, strs[si].indexOf("</A>")));
                }
            }
            saveCity(areaid, areaname, paretid, 2);
            boolean success = false;
            while(!success) {
                try {
                    readXian(cityUrl.substring(0, cityUrl.indexOf("/")+1),cityUrl,areaid);
                    success = true;
                } catch (IOException e) {
                    e.printStackTrace();
                    success = false;
                    Thread.sleep(60000);
                }
            }
        }
    }

    /**
     * 读县的数据
     * @param url
     * @throws Exception
     */
    public static void readXian(String prix,String url,String paretid) throws Exception{
        String content = getContent(baseUrl+url).toUpperCase();
        String[] citys = content.split("COUNTYTR");
        for(int i=1; i<citys.length; i++){
            String cityUrl = null;
            String areaid = "";
            String areaname = "";
            //发现石家庄有一个县居然没超链接，特殊处理
            if(citys[i].indexOf("<A HREF='")==-1){
                areaid = citys[i].substring(6, 18);
                areaname = citys[i].substring(citys[i].indexOf("</TD><TD>")+9,citys[i].lastIndexOf("</TD>"));
            }else{
                String[] strs = citys[i].split("<A HREF='");
                for(int si = 1; si<3; si++){
                    if(si==1){//取链接和编码
                        // cityUrl = strs[si].substring(0, strs[si].indexOf("'>"));
                        areaid = strs[si].substring(strs[si].indexOf("'>")+2, strs[si].indexOf("</A>"));
                    }else{
                        areaname = strs[si].substring(strs[si].indexOf("'>")+2, strs[si].indexOf("</A>"));
                    }
                }
            }
            saveCity(areaid, areaname, paretid, 3);
            // boolean success = false;
            // while(!success) {
            //     try {
            //         if(null!=cityUrl){
            //             readZhen(prix,cityUrl,areaid);
            //         }
            //         success = true;
            //     } catch (IOException e) {
            //         e.printStackTrace();
            //         success = false;
            //         Thread.sleep(60000);
            //     }
            // }
        }
    }

    /**
     * 读镇的数据
     * @param url
     * @throws Exception
     */
    // public static void readZhen(String prix,String url,String paretid) throws Exception{
    //     String content = getContent(baseUrl+prix+url).toUpperCase();
    //     String myPrix = (prix+url).substring(0, (prix+url).lastIndexOf("/")+1);
    //     String[] citys = content.split("TOWNTR");
    //     for(int i=1; i<citys.length; i++){
    //         String[] strs = citys[i].split("<A HREF='");
    //         String cityUrl = null;
    //         String areaid = "";
    //         String areaname = "";
    //         for(int si = 1; si<3; si++){
    //             if(si==1){//取链接和编码
    //                 cityUrl = strs[si].substring(0, strs[si].indexOf("'>"));
    //                 areaid = strs[si].substring(strs[si].indexOf("'>")+2, strs[si].indexOf("</A>"));
    //
    //             }else{
    //                 areaname = strs[si].substring(strs[si].indexOf("'>")+2, strs[si].indexOf("</A>"));
    //             }
    //         }
    //         saveCity(areaid, areaname, paretid, 4);
    //     }
    // }

    /**
     * 读村/街道的数据
     * @param url
     * @throws Exception
     */
    // public static void readCun(String prix,String url,BufferedWriter bw) throws Exception{
    //     String content = getContent(baseUrl+prix+url).toUpperCase();
    //     String[] citys = content.split("VILLAGETR");
    //     for(int i=1; i<citys.length; i++){
    //         String[] strs = citys[i].split("<TD>");
    //
    //         bw.write("<tr><td>");
    //         bw.write(strs[1].substring(0, strs[1].indexOf("</TD>")));
    //         bw.write("</td>");
    //
    //         bw.write("<td></td><td></td><td></td><td></td>");
    //         bw.write("<td>");
    //         bw.write(strs[2].substring(0, strs[2].indexOf("</TD>")));
    //         bw.write("</td><td>");
    //         bw.write(strs[3].substring(0, strs[3].indexOf("</TD>")));
    //         bw.write("</td></tr>");
    //     }
    // }


    //设置代理
    public static void initProxy(String host, String port) {
        System.setProperty("http.proxyType", "4");
        System.setProperty("http.proxyPort", port);
        System.setProperty("http.proxyHost", host);
        System.setProperty("http.proxySet", "true");
    }


    //获取网页的内容
    public static String getContent(String strUrl) throws Exception {
        try {
            URL url = new URL(strUrl);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),Charset.forName(CHARSET)));
            String s = "";
            StringBuffer sb = new StringBuffer("");
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }


            br.close();
            return sb.toString();
        } catch (Exception e) {
            System.out.println("can't open url:"+strUrl);
            throw e;
        }
    }


    public static void saveCity(String areaid, String areaname, String paretid, int level) {
        try {
            String URL="jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false&serverTimezone=Asia/Shanghai";
            String USER="root";
            String PASSWORD="root";
            //1.加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获得数据库链接
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
            String s = "insert into area(areaid,areaname,paretid, level) values(?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(s);

            pst.setString(1, areaid);
            pst.setString(2, areaname);
            pst.setString(3, paretid);
            pst.setInt(4, level);

            pst.execute();
            //关闭资源
            pst.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
