package com.aliano.util;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * @Author Cure
 * @Time 2022/5/22 19:40
 */
public class RandomUtil {
    private static final Random random = new Random();

    private static final DecimalFormat fourdf = new DecimalFormat("0000");

    private static final DecimalFormat sixdf = new DecimalFormat("000000");

    //生成四位的随机数字
    public static String getFourBitRandom() {
        return fourdf.format(random.nextInt(10000));
    }

    //生成六位的随机数字
    public static String getSixBitRandom() {
        return sixdf.format(random.nextInt(1000000));
    }

}
