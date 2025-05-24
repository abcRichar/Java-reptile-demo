package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Set;

public class HtmlParseUtil {
    // 解析网页
    static String url = "http://www.weather.com.cn/weather/101280601.shtml"; // http://www.weather.com.cn/weather15d/101291401.shtml
    static int i = 0;
    public static void parseWeather() throws Exception {
        Document document = Jsoup.parse(new URL(url), 30000);
        Element element = document.getElementById("7d");
        // 获取所有li标签
        Elements elements = element.getElementsByTag("li");
        HashMap<Integer, Weather> hash = new HashMap<>();
        ListIterator<Element> listIter = elements.listIterator(1);
        int j = 1;
        while (listIter.hasNext()) {
            if (j >= 7) {
                break;
            }
            Element e1 = listIter.next();
            String date = e1.getElementsByTag("h1").text();
            String status = e1.getElementsByClass("wea").eq(0).text();
            String tem = e1.getElementsByClass("tem").eq(0).text();
            String windLevel = e1.getElementsByClass("win").eq(0).text();
            String title = e1.getElementsByClass("NNW").eq(0).attr("title"); // 风向

            String[] tems = tem.split("/");
            String maxTem = tems[0];
            String minTem = tems[1];
            Weather weather = new Weather();
            i++;
            weather.setCity("深圳");
            weather.setDate(date.toString());
            weather.setStatus(status.toString());
            weather.setMaxTem(maxTem.toString());
            weather.setMinTem(minTem.toString());
            weather.setTitle(title.toString());
            weather.setWindLevel(windLevel.toString());
            hash.put(i, weather);
            j++;
        }
        Set<Integer> keys = hash.keySet();
        for (Integer key : keys) {
            Weather value = hash.get(key);
            // 时间字段中不包含'明天'字样的则跳过循环
//            if (value.getDate().indexOf("明天") == -1) {
//                continue;
//            }
            System.out.println(value.toString());
        }
    }

    public static void main(String[] args) throws Exception{
        parseWeather();
    }
}