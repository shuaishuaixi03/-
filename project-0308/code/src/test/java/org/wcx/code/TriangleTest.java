package org.wcx.code;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


public class TriangleTest {

    private final Triangle triangle = new Triangle();

    @Test
    @DisplayName("输入错误")
    public void errorParam() {
        Assert.assertEquals("输入错误", triangle.classify(-1, -1, -1));
    }

    @Test
    @DisplayName("非三角形")
    public void errorTriangle() {
        Assert.assertEquals("非三角形", triangle.classify(3, 3, 6));
    }

    @Test
    @DisplayName("等边三角形")
    public void equilateralTriangle() {
        Assert.assertEquals("等边三角形", triangle.classify(3,3,3));
    }

    @Test
    @DisplayName("不等边三角形")
    public void  scaleneTriangle() {
        Assert.assertEquals("不等边三角形", triangle.classify(3, 4, 5));
    }

    @Test
    @DisplayName("等边三角形")
    public void isoscelesTriangle() {
        Assert.assertEquals("等腰三角形", triangle.classify(3, 3, 5));
    }

}