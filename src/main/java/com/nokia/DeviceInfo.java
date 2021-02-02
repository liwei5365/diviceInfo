package com.nokia;

import com.nokia.test.CamiServiceStatus;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class DeviceInfo {
//    private static OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    public CamiServiceStatus execute() throws Exception{
        CamiServiceStatus camiServiceStatus=new CamiServiceStatus();
        SystemInfo systemInfo=new SystemInfo();
        Integer useMem = getUseMem(systemInfo,camiServiceStatus);
//        System.out.println("useMem = "+useMem);
        Double use = getUseStore(systemInfo,camiServiceStatus);
//        System.out.println("useDF = "+use.intValue());
        //
//        do {
            Double cpuUse = getCpu(systemInfo,camiServiceStatus);
//        Double cpuUse = processor.getSystemCpuLoadBetweenTicks(new long[CentralProcessor.TickType.values().length]) * 100;
//            System.out.println("cpuUse = " + cpuUse.intValue());
//        }while (true);


        return camiServiceStatus;
    }

    private Double getCpu(SystemInfo systemInfo,CamiServiceStatus camiServiceStatus) throws InterruptedException {
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        //
        TimeUnit.SECONDS.sleep(2);
//        long[] ticks = processor.getSystemCpuLoadTicks();
//        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
//        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
//        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
//        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
//        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
//        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
//        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
//        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
//        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
//
//        System.out.printf("CPU总数 = {%s},CPU利用率 ={%s}",processor.getLogicalProcessorCount(),new DecimalFormat("#.##%").format(1.0-(idle * 1.0 / totalCpu)));
//        System.out.println();
        //
        //
        Double use=processor.getSystemCpuLoadBetweenTicks(prevTicks) ;
        camiServiceStatus.setCpuUsed(new DecimalFormat("#.##%").format(use));
        return use;
    }

    private Double getUseStore(SystemInfo systemInfo,CamiServiceStatus camiServiceStatus) {
        FileSystem fs = systemInfo.getOperatingSystem().getFileSystem();
        List<OSFileStore> fstores = fs.getFileStores();
        Double totalDf = 0d;
        Double useDf=0d;
        for (int i = 0; i < fstores.size(); i++) {
            OSFileStore osFileStore = fstores.get(i);
            Double subTotal = osFileStore.getTotalSpace() * 1.0 / 1024 / 1024 / 1024;
            Double subUse = osFileStore.getUsableSpace()* 1.0 / 1024 / 1024 / 1024;
            totalDf=totalDf+subTotal;
            useDf=useDf+subUse;
//            System.out.println("totalDf = "+totalDf);
//            System.out.println("useDf = "+useDf);
        }
        Double use=(useDf / totalDf);
        camiServiceStatus.sethDUsed(new DecimalFormat("#.##%").format(use));
        return use;
    }

    private Integer getUseMem(SystemInfo systemInfo,CamiServiceStatus camiServiceStatus) {
        GlobalMemory mem = systemInfo.getHardware().getMemory();
        Double amen = mem.getAvailable() * 1.0 / 1024 / 1024;
        Double tmem = mem.getTotal() * 1.0 / 1024 / 1024;
        Double fmem=tmem-amen;
        Double useMen = (1-amen/tmem);
        camiServiceStatus.setTotalMomery(String.valueOf(tmem.intValue())+"K");
        camiServiceStatus.setFreeMomery((String.valueOf(amen.intValue()))+"K");
        camiServiceStatus.setMaxMomery(String.valueOf(tmem.intValue())+"K");
        camiServiceStatus.setMemUsed(new DecimalFormat("#.##%").format(useMen));
        return useMen.intValue();
    }

    public static void main(String[] args) throws Exception {
        DeviceInfo deviceInfo =new DeviceInfo();
        deviceInfo.execute();

    }
}
