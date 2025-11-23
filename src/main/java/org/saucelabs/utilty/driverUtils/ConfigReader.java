package org.saucelabs.utilty.driverUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader
{
    private static Properties props;

    static
    {
        try
        {
            props = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
            props.load(fileInputStream);
        } catch (IOException exception)
        {
            throw new RuntimeException("Invalid File name" + exception.getMessage());
        }
    }

    public static String get(String key)
    {
        return props.getProperty(key);
    }
}
