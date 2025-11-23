package org.saucelabs.utilty.report;

import java.io.File;

public class AllureReportCleaner
{

    private static final String REPORT_FOLDER = "allure-results";

    public static void cleanAllureReport()
    {
        File reportDir = new File(REPORT_FOLDER);

        if (reportDir.exists())
        {
            deleteDirectory(reportDir);
            System.out.println("🧹 Old Allure report deleted successfully.");
        } else
        {
            System.out.println("ℹ️ No existing Allure report found.");
        }
    }

    private static void deleteDirectory(File dir)
    {
        File[] files = dir.listFiles();

        if (files != null)
        {
            for (File file : files)
            {
                if (file.isDirectory())
                {
                    deleteDirectory(file);
                }
                file.delete();
            }
        }
        dir.delete();
    }
}
