package com.zb.rdb.dbutils;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by bzheng on 2020/1/10.
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {

    private final static Logger LOGGER = LoggerFactory.getLogger(MyRoutingDataSource.class);

    public final static String MASTER = "master";

    // 可能有多个
    public final static String SLAVE_1 = "slave_1";

    private static final ThreadLocal<String> DB_HOLDER =
            new NamedThreadLocal<>("Master or Slave DataSource");

    @Override
    protected Object determineCurrentLookupKey() {
        return DB_HOLDER.get();
    }

    public static void master() {
        DB_HOLDER.set(MASTER);
        LOGGER.info("数据库切换到主库。。。");
    }

    public static void slave_1() {
        DB_HOLDER.set(SLAVE_1);
        LOGGER.info("数据库切换到从库1。。。");
    }


}
