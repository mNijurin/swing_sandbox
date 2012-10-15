package com.alcodev.sandbox.forms.htmltable2label;

/**
 * User: mnijurin
 * Date: 15.10.12
 * Time: 12:31
 */
public class DataProvider {
    public String provideHtmlTable() {
        String data = "";

        data += "<html>";

        data += "<table width=\"100%\" bgcolor=\"white\">";

        data += "<tr>";
        data += "<td colspan=\"4\" bgcolor=\"red\">";
        data += "<font size=+2>" + "Тестовый заголовок" + "</font>";
        data += "</td>";
        data += "</tr>";

        data += "<tr>";
        data += "<td>";
        data += "110";
        data += "</td>";
        data += "<td>";
        data += "10.12 10:12";
        data += "</td>";
        data += "<td>";
        data += "команда 1 - команда 2";
        data += "</td>";
        data += "<td>";
        data += "аргентина ямайка 5-0";
        data += "</td>";
        data += "</tr>";

        data += "</table>";

        data += "</html>";

        return data;
    }
}
