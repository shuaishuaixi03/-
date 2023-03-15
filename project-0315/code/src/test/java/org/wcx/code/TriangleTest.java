package org.wcx.code;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;


public class TriangleTest {

    //内部静态三角形测试工具类，不希望被继承
    final static class TriangleTestUtil {

        //生成笛卡尔积
        private static void generateCartesianProduct(int[] nums) {
            int index = 1;
            for (int i : nums) {
                for (int i1 : nums) {
                    for (int i2 : nums) {
                        System.out.println(index + "," + i + "," + i1 + "," + i2);
                        index ++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] common = {1, 2, 60, 99, 100};
        int[] robust = {0, 1, 2, 60, 99, 100, 101};
        System.out.println("以下是判断最坏边界值测试");
        TriangleTestUtil.generateCartesianProduct(common);
        System.out.println("以下是判断最坏健壮边界值测试");
        TriangleTestUtil.generateCartesianProduct(robust);
    }

    private final Triangle triangle = new Triangle();

    @ParameterizedTest
    @CsvFileSource(resources = "/一般边界值测试用例.csv", numLinesToSkip = 1)
    public void generalBoundaryValueTestCases(int index, int a, int b, int c, String expectedRes) throws Triangle.TriangleException {
        assertEquals(expectedRes, triangle.classify(a, b, c));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/健壮边界值测试用例.csv", numLinesToSkip = 1)
    public void robustBoundaryValueTestCases(int index, int a, int b, int c, String expectedRes) {
        try {
            assertEquals(expectedRes, triangle.classify(a, b, c));
        } catch (Triangle.TriangleException e) {
            assertEquals(expectedRes, e.getMessage());
        }
    }
}