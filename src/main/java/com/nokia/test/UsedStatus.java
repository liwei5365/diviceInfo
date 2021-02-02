package com.nokia.test;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * linux cpu mem desk uesd
 */
@Service
public class UsedStatus {

    /**
     * cpu used
     */
    private double getCpuUsage() {
        double cpuUsed = 0;
        Runtime rt = Runtime.getRuntime();
        Process p = null;//  Call the system's "top" command
        try {
            p = rt.exec("top -b -n 1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str = null;
            String[] strArray = null;

            while ((str = in.readLine()) != null) {
                int m = 0;

                if (str.indexOf(" R ") != -1) {// Only running processes are analyzed, except for the top process itself &&

                    strArray = str.split(" ");
                    for (String tmp : strArray) {
                        if (tmp.trim().length() == 0) //string.trim().length() --> the effect is to remove the trailing space
                            continue;
                        if (++m == 9) {// The ninth column is the percentage of CPU used (RedHat)

                            cpuUsed += Double.parseDouble(tmp);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cpuUsed;
    }
    /**
     * mem used
     */
    private double getMemUsage() {
        double memUsed = 0;
        Runtime rt = Runtime.getRuntime();
        Process p = null;// Call the system's "top" command
        try {
            p = rt.exec("top -b -n 1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str = null;
            String[] strArray = null;

            while ((str = in.readLine()) != null) {
                int m = 0;

                if (str.indexOf(" R ") != -1) {// Only running processes are analyzed, except for the top process itself &&
                // System.out.println("------------------3-----------------");
                    strArray = str.split(" ");
                    for (String tmp : strArray) {
                        if (tmp.trim().length() == 0)
                            continue;
                        if (++m == 10) {
                // 9)--The tenth is the percentage of CPU used (RedHat 9)
                            memUsed += Double.parseDouble(tmp);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return memUsed;
    }

    /**
     * desk used:Gets the disk space size
     */
    private double getDeskUsage() {
        double totalHD = 0;
        double usedHD = 0;
        Runtime rt = Runtime.getRuntime();
        Process p = null;//df -hl  view hard disk space
        try {
            p = rt.exec("df -hl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str = null;
            String[] strArray = null;
            int flag = 0;
            while ((str = in.readLine()) != null) {
                int m = 0;
//              if (flag > 0) {
//                  flag++;
                strArray = str.split(" ");
                for (String tmp : strArray) {
                    if (tmp.trim().length() == 0)
                        continue;
                    ++m;
//                      System.out.println("----tmp----" + tmp);
                    if (tmp.indexOf("G") != -1) {
                        if (m == 2) {
//                              System.out.println("---G----" + tmp);
                            if (!tmp.equals("") && !tmp.equals("0"))
                                totalHD += Double.parseDouble(tmp
                                        .substring(0, tmp.length() - 1)) * 1024;
                        }
                        if (m == 3) {
//                              System.out.println("---G----" + tmp);
                            if (!tmp.equals("none") && !tmp.equals("0"))
                                usedHD += Double.parseDouble(tmp.substring(
                                        0, tmp.length() - 1)) * 1024;
                        }
                    }
                    if (tmp.indexOf("M") != -1) {
                        if (m == 2) {
//                              System.out.println("---M---" + tmp);
                            if (!tmp.equals("") && !tmp.equals("0"))
                                totalHD += Double.parseDouble(tmp
                                        .substring(0, tmp.length() - 1));
                        }
                        if (m == 3) {
//                              System.out.println("---M---" + tmp);
                            if (!tmp.equals("none") && !tmp.equals("0"))
                                usedHD += Double.parseDouble(tmp.substring(0, tmp.length() - 1));
                        // System.out.println("----3----" + usedHD);
                        }
                    }

                }
//              }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (usedHD / totalHD) * 100;
    }
    public CamiServiceStatus execute() throws Exception {
        Runtime lRuntime = Runtime.getRuntime();

        CamiServiceStatus usedEntity = new CamiServiceStatus();
        usedEntity.setCpuUsed(getCpuUsage() + "%");
        usedEntity.setMemUsed(getMemUsage() + "%");
        usedEntity.sethDUsed(getDeskUsage() + "%");

        usedEntity.setFreeMomery(lRuntime.freeMemory()+"K");
        usedEntity.setMaxMomery(lRuntime.maxMemory()+"K");
        usedEntity.setTotalMomery(lRuntime.totalMemory()+"K");
        usedEntity.setAvailableProcessors(lRuntime.availableProcessors());

        return usedEntity;
    }
}
