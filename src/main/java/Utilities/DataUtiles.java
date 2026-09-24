package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.swing.text.Utilities;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class DataUtiles {
//    public final static String TEST_DATA_PATH = "E:\\the automation test\\COC\\src\\test\\TestData\\";
public final static String TEST_DATA_PATH =
        System.getProperty("user.dir") + "/src/test/TestData/";
    public static String getJsonData(String jsonFilename, String field) {
        try {
            FileReader reader = new FileReader(TEST_DATA_PATH + jsonFilename + ".json");
            JsonElement jsonElement = JsonParser.parseReader(reader);
            return jsonElement.getAsJsonObject().get(field).getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getPropertyValue(String fileName, String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(TEST_DATA_PATH + fileName + ".properties"));
        return properties.getProperty(key);
    }


        // ... باقي الميثودز اللي عندك أصلاً (getJsonData, getPropertyValue...)

        // نفس الـ run كله هياخد نفس الـ suffix
        private static final String RUN_ID = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // بيرجع القيمة من الجيسون + suffix فريد
        public static String getUniqueJsonData (String fileName, String key) {
            String baseValue = getJsonData(fileName, key);
            return baseValue + "_" + RUN_ID;
        }
    }
