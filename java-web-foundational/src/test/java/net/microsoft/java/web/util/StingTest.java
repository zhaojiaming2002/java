package net.microsoft.java.web.util;

import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * @description:
 * @Date on 2022/4/29
 * @author: suche
 **/

public class StingTest {
    public static void main(String[] args) {
        String fieldName = "setId";
        String originColumnName = "id";

        boolean isCompare = compareColumnNameFiledName(originColumnName, fieldName);

        System.out.println(isCompare ? "匹配成功" : "匹配失败");
//            String subPropertyName = filedName.substring(3);
//
//            char firstPropNameWithOutUpperCase = subPropertyName.charAt(0);
//            char firstPropNameWithOutLowerCase = Character.toLowerCase(firstPropNameWithOutUpperCase);
//
//            String propName = subPropertyName.replace(firstPropNameWithOutUpperCase, firstPropNameWithOutLowerCase);
//            System.out.println(propName);

    }

    @Test
    public void testLayOut() {

        Frame frame = new Frame();
        LayoutManager flowLayout = new GridLayout();
        frame.setLayout(flowLayout);

        for (int i = 1; i <= 5; i++) {
            frame.add(new Button("button" + i));
        }
        frame.setSize(300, 300);
        while (true) {
            frame.setVisible(true);
        }


    }

    @Test
    public void testGetAsResource() {
        final URL resource = StingTest.class.getClassLoader().getResource("");
        System.out.println(resource);

    }


    public static boolean compareColumnNameFiledName(String originColumnName, String fieldName) {
        int firstCharIndex = 0;

        if (originColumnName.contains("_")) {
            String filedName = fieldName.substring(3);
            char firstFiledNameUpper = filedName.charAt(firstCharIndex);
            char firstFiledLowerCase = Character.toLowerCase(firstFiledNameUpper);
            String propName = filedName.replace(firstFiledNameUpper, firstFiledLowerCase);
            String[] splitOriginColumnName = originColumnName.split("_");

            StringBuffer columnNameStringBuffer = new StringBuffer(splitOriginColumnName[firstCharIndex]);

            //把列名改成属性名

            for (int i = 1; i < splitOriginColumnName.length; i++) {
                char firstWordWithOutLowerCase = splitOriginColumnName[i].charAt(firstCharIndex);
                char firstWordWithOutUpperCase = Character.toUpperCase(firstWordWithOutLowerCase);
                String replaceColumn = splitOriginColumnName[i].replace(firstWordWithOutLowerCase, firstWordWithOutUpperCase);
                columnNameStringBuffer.append(replaceColumn);
            }

            String columnName = columnNameStringBuffer.toString();

            return columnName.equals(propName);
        } else if (!originColumnName.contains("_") && originColumnName.equals(fieldName)) {

            return true;
        }
        return false;
    }

}






