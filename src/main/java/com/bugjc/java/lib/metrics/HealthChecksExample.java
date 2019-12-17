package com.bugjc.java.lib.metrics;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;

import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 集中式检测系统的监控状态，例如数据库连接是否正常。
 *
 * @author aoki
 * @date 2019/12/17
 **/
public class HealthChecksExample extends HealthCheck {

    private DataBase database;

    public HealthChecksExample(DataBase database) {
        this.database = database;
    }

    @Override
    protected Result check() throws Exception {
        if (database.ping()) {
            return Result.healthy();
        }
        return Result.unhealthy("Can't ping database.");
    }

    static class DataBase {
        //模拟ping方法
        public boolean ping() {
            Random r = new Random();
            return r.nextBoolean();
        }
    }

    /**
     * 测试程序
     * @param args
     */
    public static void main(String[] args) {
        //创建健康检查注册中心
        HealthCheckRegistry registry = new HealthCheckRegistry();
        //将被检查的类注册到中心
        registry.register("database1", new HealthChecksExample(new DataBase()));
        registry.register("database2", new HealthChecksExample(new DataBase()));
        //从运行的健康检查注册中心获取被检测的结果
        Set<Map.Entry<String, Result>> entrySet = registry.runHealthChecks().entrySet();
        while (true) {
            for (Map.Entry<String, Result> entry : entrySet) {
                if (entry.getValue().isHealthy()) {
                    System.out.println(entry.getKey() + ": OK");
                } else {
                    System.err.println(entry.getKey() + "FAIL:error message: " + entry.getValue().getMessage());
                    final Throwable e = entry.getValue().getError();
                    if (e != null) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
