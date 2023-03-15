package org.wcx.code;


public class Triangle {
    final static class TriangleException extends Exception {
        public TriangleException(String msg) {
            super(msg);
        }
    }
    public String classify(int a, int b, int c) throws TriangleException {
        if (a <1 || a>100 || b<1 || b>100 || c<1 || c> 100) {
            throw new TriangleException("参数异常");
        } 
        if (!((a + b > c) && (a + c > b) && (b + c > a))) {
            return "非三角形";
        } else if (a == b && a == c && b == c) {
            return "等边三角形";
        } else if (a != b && a != c && b != c) {
            return "不等边三角形";
        } else {
            return "等腰三角形";
        }
    }
}