import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class TelephoneBilling {

    public static double calculateCallCost(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        double callCost = 0.0;

        // 计算通话时长（分钟），向上进位到最近的一分钟
        long durationInMinutes = ChronoUnit.MINUTES.between(startDateTime, endDateTime) + 1;

        // 计算起始日期和结束日期之间的小时数
        long hoursBetween = ChronoUnit.HOURS.between(startDateTime, endDateTime);

        // 检查通话是否跨越了春季或秋季的夏令时变更
        if (isDuringDaylightSavingTimeChange(startDateTime) || isDuringDaylightSavingTimeChange(endDateTime)) {
            // 对于春季变更，将通话时长减少1小时；对于秋季变更，将通话时长增加1小时
            durationInMinutes += (isDuringDaylightSavingTimeChange(startDateTime) ? -60 : 60);
        }

        // 根据通话时长计算通话费用
        if (durationInMinutes <= 20) {
            callCost = durationInMinutes * 0.05;
        } else {
            callCost = 1.0 + (durationInMinutes - 20) * 0.1;
        }

        // 如果通话时长不足1分钟，按照1分钟计算
        if (durationInMinutes < 1) {
            callCost = 0.05;
        }
        return Double.parseDouble(String.format("%.2f", callCost));
    }

    // 辅助方法，用于检查给定的日期和时间是否处于夏令时变更期间
    private static boolean isDuringDaylightSavingTimeChange(LocalDateTime dateTime) {
        Month month = dateTime.getMonth();
        int dayOfMonth = dateTime.getDayOfMonth();
        int year = dateTime.getYear();

        if ((month == Month.MARCH && dayOfMonth > 7) || (month == Month.NOVEMBER && dayOfMonth < 7)) {
            // 超过3月的第二个星期日或者11月的第一个星期日之后
            return false;
        }

        LocalDateTime nextSunday = LocalDateTime.of(year, month, 1, 2, 0).with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        if ((month == Month.MARCH && dayOfMonth >= nextSunday.getDayOfMonth())
                || (month == Month.NOVEMBER && dayOfMonth < nextSunday.getDayOfMonth())) {
            // 在或之后3月的下一个星期日或者在11月的下一个星期日之前
            return true;
        }

        return false;
    }
}
