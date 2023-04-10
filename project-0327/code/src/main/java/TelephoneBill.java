import java.util.Calendar;

public class TelephoneBill {

    static class TelephoneBillException extends RuntimeException {
        public TelephoneBillException(String msg) {
            super(msg);
        }
    }
    //通话开始时间
    private Calendar startTime;
    //通话结束时间
    private Calendar endTime;
    //通话中是否发生春节的时间转换
    private boolean springConversion;
    //通话中是否发生秋季的时间转换
    private boolean autumnConversion;

    public TelephoneBill(Calendar startTime, Calendar endTime, boolean springConversion, boolean autumnConversion) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.springConversion = springConversion;
        this.autumnConversion = autumnConversion;
    }
    private long calculateTalkTimeBySeconds() {
        if (springConversion == true && autumnConversion == true) {
            throw new TelephoneBillException("输入参数错误");
        }
        int seconds = (endTime.get(Calendar.DAY_OF_MONTH) - startTime.get(Calendar.DAY_OF_MONTH)) * 24 * 60 * 60 +
                (endTime.get(Calendar.HOUR_OF_DAY) - startTime.get(Calendar.HOUR_OF_DAY)) * 60 * 60 +
                (endTime.get(Calendar.MINUTE) - startTime.get(Calendar.MINUTE)) * 60 +
                (endTime.get(Calendar.SECOND) - startTime.get(Calendar.SECOND));
        if (seconds <= 0) {
            throw new TelephoneBillException("输入参数错误");
        }
        //春季，这种转换发生在(3月某个)星期日凌晨2:00点，这时要将时钟设置为凌晨3:00点
        //春节发生转换时，seconds会多算1个小时
        seconds -= springConversion ? 60 * 60 * 60 : 0;
        //秋季， 转换通常在11月的第一个星期日，时钟要从2:59:59调回2:00:00。
        //秋季发生转换时，seconds会少算1个小时
        seconds += autumnConversion ? 60 * 60 * 60 : 0;
        return seconds;
    }
    private long calcuateTalkTimeByMinutes() {
        long seconds = calculateTalkTimeBySeconds();
        long minutes = (long) Math.ceil(seconds * 1.0 / 60);
        return minutes;
    }

    /**
     * 通话时间小于等于20分钟时，每分钟收费0.05美元，通话时间不足1分钟按1分钟计算。
     * 通话时间大于20分钟时，收费1.00美元，外加超过20分钟的部分每分钟0.10美元；
     * 不到1分钟按1分钟计算
     */
    public double calcuateTelephoneBill() {
        long minutes = calcuateTalkTimeByMinutes();
        if (minutes > 30 * 60) {
            throw new TelephoneBillException("通话时间超过30小时");
        }
        double telephoneBill = 0.0;
        for (int i = 0; i < 20; i ++) {
            minutes --;
            if (minutes >= 0) {
                telephoneBill += 0.05;
            } else {
                return telephoneBill;
            }
        }
        if (minutes > 0) {
            telephoneBill += 1.0;
        }
        while (minutes -- > 0) {
            telephoneBill += 0.1;
        }
        return Double.parseDouble(String.format("%.2f", telephoneBill));
    }
}
