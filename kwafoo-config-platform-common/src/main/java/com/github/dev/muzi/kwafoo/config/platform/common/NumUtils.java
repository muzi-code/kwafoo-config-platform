package com.github.dev.muzi.kwafoo.config.platform.common;

import java.math.BigDecimal;

/**
 * 数字通用工具类
 */
public class NumUtils {

    private NumUtils() {
    }

    public static boolean greatThan(Number num1, Number num2) {
        if (num1 == null || num2 == null) {
            return false;
        }
        if (num1 instanceof Long && num2 instanceof Long) {
            return num1.longValue() > num2.longValue();
        } else if (num1 instanceof Long && num2 instanceof Integer) {
            return num1.longValue() > num2.intValue();
        } else if (num1 instanceof Integer && num2 instanceof Long) {
            return num1.intValue() > num2.longValue();
        } else if (num1 instanceof Integer && num2 instanceof Integer) {
            return num1.intValue() > num2.intValue();
        }
        return false;
    }

    public static boolean lessThan(Number num1, Number num2) {
        if (num1 == null || num2 == null) {
            return false;
        }
        if (num1 instanceof Long && num2 instanceof Long) {
            return num1.longValue() < num2.longValue();
        } else if (num1 instanceof Long && num2 instanceof Integer) {
            return num1.longValue() < num2.intValue();
        } else if (num1 instanceof Integer && num2 instanceof Long) {
            return num1.intValue() < num2.longValue();
        } else if (num1 instanceof Integer && num2 instanceof Integer) {
            return num1.intValue() < num2.intValue();
        }
        return false;
    }

    public static boolean equals(Number num1, Number num2) {
        if (num1 == null || num2 == null) {
            return false;
        }
        if (num1 instanceof Long && num2 instanceof Long) {
            return num1.longValue() == num2.longValue();
        } else if (num1 instanceof Long && num2 instanceof Integer) {
            return num1.longValue() == num2.intValue();
        } else if (num1 instanceof Integer && num2 instanceof Long) {
            return num1.intValue() == num2.longValue();
        } else if (num1 instanceof Integer && num2 instanceof Integer) {
            return num1.intValue() == num2.intValue();
        }
        return false;
    }

    public static boolean notEquals(Number num1, Number num2) {
        return !equals(num1, num2);
    }

    // 判断每个都必须大于第一个数,且不为NULL
    public static boolean greatThanFirstEvery(Number num, Number... nums) {
        boolean result = false;
        if (num != null && nums != null && nums.length > 0) {
            for (Number n : nums) {
                result = greatThan(n, num);
                if (!result) {
                    break;
                }
            }
        }
        return result;
    }

    public static int intValue(Integer num) {
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public static long longValue(Long num) {
        if (num == null) {
            return 0;
        }
        return num.longValue();
    }

    public static double doubleValue(Double num) {
        if (num == null) {
            return 0;
        }
        return num.doubleValue();
    }

    public static int parseInt(Object num) {
        try {
            if (num != null && num instanceof Boolean) {
                return ((Boolean) num).booleanValue() ? 1 : 0;
            } else if (num != null && num instanceof Double) {
                return ((Double) num).intValue();
            } else if (num != null && num instanceof Float) {
                return ((Float) num).intValue();
            } else if (num != null && num instanceof Long) {
                return ((Long) num).intValue();
            }
            return Integer.parseInt(num.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    public static long parseLong(Object num) {
        try {
            if (num != null && num instanceof Double) {
                return ((Double) num).longValue();
            } else if (num != null && num instanceof Float) {
                return ((Float) num).longValue();
            } else if (num != null && num instanceof Long) {
                return ((Long) num).longValue();
            } else if (num != null && num instanceof Integer) {
                return ((Integer) num).longValue();
            }
            return Long.parseLong(num.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    public static double parseDouble(Object num) {
        try {
            return Double.parseDouble(num.toString());
        } catch (Exception e) {
            return 0d;
        }
    }

    public static float parseFloat(Object num) {
        try {
            return Float.parseFloat(num.toString());
        } catch (Exception e) {
            return 0f;
        }
    }

    // 保留两位小数
    public static float divide(long num1, long num2) {
        try {
            float value = new BigDecimal(num1 * 100).divide(new BigDecimal(num2), 2, BigDecimal.ROUND_HALF_UP).floatValue();
            if (value > 100) {
                value = 100;
            } else if (value < 0) {
                value = 0;
            }
            return value;
        } catch (Exception e) {
            return 0;
        }
    }

    public static long divideToLong(long num1, long num2) {
        try {
            long value = new BigDecimal(num1 * 100).divide(
                    new BigDecimal(num2), 2, BigDecimal.ROUND_HALF_UP)
                    .longValue();
            if (value > 100) {
                value = 100;
            } else if (value < 0) {
                value = 0;
            }
            return value;
        } catch (Exception e) {
            return 0;
        }
    }

    public static String dividePercent(int x, int y) {
        String res = "0";
        try {
            if (x <= 0 || y <= 0) {
                return res;
            }
            BigDecimal i = new BigDecimal(x * 100);
            BigDecimal j = new BigDecimal(y);
            res = i.divide(j, 1, BigDecimal.ROUND_FLOOR).toString();
        } catch (Exception e) {
            return res;
        }
        return res.replace(".0", "");
    }

    public static long defaultValue(Number value, long defaultValue) {
        if (value != null) {
            return value.longValue();
        }
        return defaultValue;
    }

    public static int defaultValue(Number value, int defaultValue) {
        if (value != null) {
            return value.intValue();
        }
        return defaultValue;
    }
}
