package com.bugjc.java.basics.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;

@Slf4j
public class SystemInfoTools {


    public static void main(String[] args) {
        MonitorInfoBean monitorInfoBean = SystemInfoTools.getMonitorInfoBean();
        log.info("CPU:{}",monitorInfoBean.getCpuUsage());
        log.info("RAM:{}",monitorInfoBean.getMemUsage());
        log.info("RAM Size:{}",monitorInfoBean.getMemUseSize());
    }

    private final static boolean isNotWindows = !System.getProperties().getProperty("os.name").toLowerCase().contains("windows");
    private final static BigDecimal DIVISOR = BigDecimal.valueOf(1024);

    public static int getPid(){
        return Integer.parseInt(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
    }

    public static MonitorInfoBean getMonitorInfoBean() {
        MonitorInfoBean monitorInfo = new MonitorInfoBean();
        if(!isNotWindows){
            monitorInfo.setMemUsage(500);
            return monitorInfo;
        }
        Runtime rt = Runtime.getRuntime();
        BufferedReader in = null;
        try {
            int pid = getPid();
            String[] cmd = {
                    "/bin/sh",
                    "-c",
                    "top -b -n 1 | grep " + pid
            };
            Process p = rt.exec(cmd);
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str = null;
            String[] strArray = null;
            while ((str = in.readLine()) != null) {
                log.debug("top: " + str);
                int m = 0;
                strArray = str.split(" ");
                for (String info : strArray) {
                    if (info.trim().length() == 0) {
                        continue;
                    }
                    if (m == 5) {//第5列为进程占用的物理内存值
                        String unit = info.substring(info.length() - 1);
                        if (unit.equalsIgnoreCase("g")) {
                            monitorInfo.setMemUseSize(Double.parseDouble(info));
                        } else if (unit.equalsIgnoreCase("m")) {
                            BigDecimal memUseSize = new BigDecimal(info.substring(0, info.length() - 1));
                            monitorInfo.setMemUseSize(memUseSize.divide(DIVISOR, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        } else {
                            BigDecimal memUseSize = new BigDecimal(info).divide(DIVISOR);
                            monitorInfo.setMemUseSize(memUseSize.divide(DIVISOR, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        }
                    }
                    if (m == 8) {//第9列为CPU的使用百分比
                        monitorInfo.setCpuUsage(Double.parseDouble(info));
                    }
                    if (m == 9) {//第10列为内存的使用百分比
                        monitorInfo.setMemUsage(Double.parseDouble(info));
                    }
                    m++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return monitorInfo;
    }

    public static class MonitorInfoBean {

        /** cpu使用率 */
        private double cpuUsage;

        /** 内存使用率 */
        private double memUsage;

        /** 内存使用的大小 */
        private double memUseSize;

        public double getCpuUsage() {
            return cpuUsage;
        }

        public void setCpuUsage(double cpuUsage) {
            this.cpuUsage = cpuUsage;
        }

        public double getMemUsage() {
            return memUsage;
        }

        public void setMemUsage(double memUsage) {
            this.memUsage = memUsage;
        }

        public double getMemUseSize() {
            return memUseSize;
        }

        public void setMemUseSize(double memUseSize) {
            this.memUseSize = memUseSize;
        }

    }

}
