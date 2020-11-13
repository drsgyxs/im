package com.drsg.demo.v1.utils;

public class IdWorker{
    /** 开始时间截 (2020-8-28) */
    private final long twepoch = 1598595990351L;

    /** 序列在id中占的位数 */
    private final long sequenceBits = 20L;

    /** 时间截向左移20位 */
    private final long timestampLeftShift = sequenceBits;

    /** 生成序列的掩码，这里为1048575 */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /** 秒内序列(0~1048575) */
    private long sequence = 0L;

    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1;

    // ==============================Methods==========================================
    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        // 如果是同一时间生成的，则进行秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个豪秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        // 时间戳改变，秒内序列重置
        else {
            sequence = 0L;
        }
        // 上次生成ID的时间截
        lastTimestamp = timestamp;
        // 移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift) | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp
     *            上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以豪秒为单位的当前时间
     *
     * @return 当前时间(豪秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    private static IdWorker instance = new IdWorker();

    public static Long generateId() {
        return instance.nextId();
    }
}