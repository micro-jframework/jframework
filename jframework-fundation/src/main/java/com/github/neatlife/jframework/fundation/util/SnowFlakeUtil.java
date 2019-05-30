package com.github.neatlife.jframework.fundation.util;

import com.google.common.base.Preconditions;
import lombok.SneakyThrows;

import java.util.Calendar;

/**
 * @author suxiaolin
 * @date 2019-04-11 16:44
 */
public class SnowFlakeUtil {

    private static long workerId = 0;

    private static SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator;

    private SnowFlakeUtil() {
    }

    public static void setWorkerId(long workerId) {
        SnowFlakeUtil.workerId = workerId;
    }

    public static long next() {
        return getInstance().generateKey();
    }

    private static synchronized SnowflakeShardingKeyGenerator getInstance() {
        if (snowflakeShardingKeyGenerator == null) {
            snowflakeShardingKeyGenerator = new SnowflakeShardingKeyGenerator(workerId);
        }
        return snowflakeShardingKeyGenerator;
    }

    public static class SnowflakeShardingKeyGenerator {

        static final long EPOCH;

        private static final long SEQUENCE_BITS = 12L;

        private static final long WORKER_ID_BITS = 10L;

        private static final long SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;

        private static final long WORKER_ID_LEFT_SHIFT_BITS = SEQUENCE_BITS;

        private static final long TIMESTAMP_LEFT_SHIFT_BITS = WORKER_ID_LEFT_SHIFT_BITS + WORKER_ID_BITS;

        private static final long WORKER_ID_MAX_VALUE = 1L << WORKER_ID_BITS;
        private static final int MAX_TOLERATE_TIME_DIFFERENCE_MILLISECONDS = 10;

        static {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2016, Calendar.NOVEMBER, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            EPOCH = calendar.getTimeInMillis();
        }

        private final long workerId;
        private byte sequenceOffset;
        private long sequence;
        private long lastMilliseconds;

        SnowflakeShardingKeyGenerator(long workerId) {
            this.workerId = workerId;
        }

        synchronized long generateKey() {
            long currentMilliseconds = System.currentTimeMillis();
            if (waitTolerateTimeDifferenceIfNeed(currentMilliseconds)) {
                currentMilliseconds = System.currentTimeMillis();
            }
            if (lastMilliseconds == currentMilliseconds) {
                if (0L == (sequence = (sequence + 1) & SEQUENCE_MASK)) {
                    currentMilliseconds = waitUntilNextTime(currentMilliseconds);
                }
            } else {
                vibrateSequenceOffset();
                sequence = sequenceOffset;
            }
            lastMilliseconds = currentMilliseconds;
            return ((currentMilliseconds - EPOCH) << TIMESTAMP_LEFT_SHIFT_BITS) | (getWorkerId() << WORKER_ID_LEFT_SHIFT_BITS) | sequence;
        }

        @SneakyThrows
        private boolean waitTolerateTimeDifferenceIfNeed(final long currentMilliseconds) {
            if (lastMilliseconds <= currentMilliseconds) {
                return false;
            }
            long timeDifferenceMilliseconds = lastMilliseconds - currentMilliseconds;
            Preconditions.checkState(timeDifferenceMilliseconds < getMaxTolerateTimeDifferenceMilliseconds(),
                    "Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", lastMilliseconds, currentMilliseconds);
            Thread.sleep(timeDifferenceMilliseconds);
            return true;
        }

        private long getWorkerId() {
            long result = workerId;
            Preconditions.checkArgument(result >= 0L && result < WORKER_ID_MAX_VALUE);
            return result;
        }

        private int getMaxTolerateTimeDifferenceMilliseconds() {
            return Integer.valueOf(String.valueOf(MAX_TOLERATE_TIME_DIFFERENCE_MILLISECONDS));
        }

        private long waitUntilNextTime(final long lastTime) {
            long result = System.currentTimeMillis();
            while (result <= lastTime) {
                result = System.currentTimeMillis();
            }
            return result;
        }

        private void vibrateSequenceOffset() {
            sequenceOffset = (byte) (~sequenceOffset & 1);
        }
    }
}
