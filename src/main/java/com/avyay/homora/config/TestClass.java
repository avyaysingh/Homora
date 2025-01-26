package com.avyay.homora.config;

public class TestClass {

    public static void main(String[] args) {
        System.out.println(isSameColor("c3", "f4"));

        System.out.println(isSameColor("b2", "a5"));
    }

    private static boolean isSameColor(String area1, String area2) {
        char char1 = area1.charAt(0);
        String rem1 = area1.substring(1);
        int ar1Int = Integer.parseInt(rem1);

        // second str
        char char2 = area2.charAt(0);
        String rem2 = area2.substring(1);
        int ar2Int = Integer.parseInt(rem2);

        if (char1 != char2) {
            if ((isEven(ar1Int) && !isEven(ar2Int)) || (!isEven(ar1Int) && isEven(ar2Int))) {
                return true;
            }
        } else {
            return isEven(Math.abs(ar2Int - ar1Int));
        }
        return false;
    }

    private static boolean isEven(int num) {
        return num % 2 == 0;
    }

}
