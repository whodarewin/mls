package com.hc.ml.feature.engineering.utils;


/**
 * Bytes
 *
 * @author han.congcong
 * @date 2019/7/8
 */

public class Bytes {

    public static byte[] toBytes(long val) {
        byte[] b = new byte[8];
        for (int i = 7; i > 0; i--) {
            b[i] = (byte) val;
            val >>>= 8;
        }
        b[0] = (byte) val;
        return b;
    }

    public static byte[] toBytes(int val) {
        //int 占用4个字节
        byte[] b = new byte[4];
        for (int i = 3; i > 0; i--) {
            //int 强转byte，丢失高位
            b[i] = (byte) val;
            //无符号右移，无论正负，高位都以0填充
            val >>>= 8;
        }
        b[0] = (byte) val;
        return b;
    }


    public static long toLong(byte[] b) {
        return UnsafeAccess.toLong(b, 0);
    }

    public static int toInt(byte[] b) {
        return UnsafeAccess.toInt(b, 0);
    }

}
