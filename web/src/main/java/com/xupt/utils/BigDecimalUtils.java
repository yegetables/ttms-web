package com.xupt.utils;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class BigDecimalUtils {

  public double addDouble(double number1, double number2) {

    BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(number1));

    BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(number2));

    return bigDecimal1.add(bigDecimal2).doubleValue();
  }

  public double subDouble(double number1, double number2) {

    BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(number1));

    BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(number2));

    return bigDecimal1.subtract(bigDecimal2).doubleValue();
  }
}
