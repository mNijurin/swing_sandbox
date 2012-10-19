package com.alcodev.sandbox.forms.htmltable2label;

/**
 * User: mnijurin
 * Date: 15.10.12
 * Time: 12:31
 */
public class DataProvider {
    public String provideHtmlTable() {
        String data = "";

        data += "<html>" +
                "<style>" +
                ".abc {color: black; font-size: 14px; font-face: bold; font-family: Arial}" +
                ".number {color: red; font-size: 20px; font-face: bold; font-family: Arial}" +
                "</style>" +
                "<div class='abc'>Title1</div>" +
                "<div><span class='number'> 146 </span><span>England 1:0 France</span></div>";

//        data += "<table width=\"100%\" bgcolor=\"white\">";
//
//        data += "<tr>";
//        data += "<td colspan=\"4\" bgcolor=\"red\">";
//        data += "<font size=+2>" + "Тестовый заголовок" + "</font>";
//        data += "</td>";
//        data += "</tr>";
//
//        data += "<tr>";
//        data += "<td>";
//        data += "110";
//        data += "</td>";
//        data += "<td>";
//        data += "10.12 10:12";
//        data += "</td>";
//        data += "<td>";
//        data += "команда 1 - команда 2";
//        data += "</td>";
//        data += "<td>";
//        data += "аргентина ямайка 5-0";
//        data += "</td>";
//        data += "</tr>";
//
//        data += "</table>";

        data += "</html>";
// что б использовать cellpadding , надо значение без ковычек ставить!!!
        return data;
    }
}
