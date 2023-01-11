package song_test;

import java.math.BigDecimal;

import static javafx.scene.input.KeyCode.L;

public class test15 {
    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal("0.02");
        BigDecimal b2 = new BigDecimal("0.01");
        System.out.println(b1.subtract(b2));

        BigDecimal b3 = BigDecimal.valueOf(0.02);
        BigDecimal b4 = BigDecimal.valueOf(0.01);
        System.out.println(b3.subtract(b4));

    }
}
