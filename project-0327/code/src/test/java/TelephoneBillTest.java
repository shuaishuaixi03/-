import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TelephoneBillTest {

    @DisplayName(value="话费边等价类测试")
    @ParameterizedTest
    @CsvFileSource(resources = "/话费边等价类测试.csv",numLinesToSkip =1)
    public void test(final String start, final String end,
                     final boolean springConversion, final boolean autumnConversion, final double expert) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(simpleDateFormat.parse(start));
        Calendar endTime = Calendar.getInstance();
        endTime.setTime(simpleDateFormat.parse(end));
        TelephoneBill telephoneBillTest = new TelephoneBill(
                startTime,
                endTime,
                springConversion,
                autumnConversion
        );
        try {
            assertEquals(expert, telephoneBillTest.calcuateTelephoneBill());
        } catch (TelephoneBill.TelephoneBillException e) {
            assertEquals("输入参数错误", e.getMessage());
        }

    }
}