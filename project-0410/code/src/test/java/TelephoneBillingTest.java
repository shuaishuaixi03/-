import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TelephoneBillingTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/test_data.csv", numLinesToSkip = 1)
    public void testCallCost(String startDateTime, String endDateTime, double expectedCallCost) {
        // 将字符串类型的日期时间转换为实际的日期时间对象，根据具体的实现方式进行转换
        // 示例中使用了 LocalDateTime 类，可以根据实际情况选择合适的日期时间类
        LocalDateTime start = LocalDateTime.parse(startDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime end = LocalDateTime.parse(endDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // 调用计算通话费用的方法，计算实际的通话费用
        double actualCallCost = TelephoneBilling.calculateCallCost(start, end);

        // 断言实际的通话费用与预期的通话费用是否相等
        assertEquals(expectedCallCost, actualCallCost);
    }
}
