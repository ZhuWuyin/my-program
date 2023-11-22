package HW1;

public class Input {

    /**
     * Get three digits from given string
     * 
     * @param s
     * @return
     * @throws InputError
     */
    public static int[] getDigits(String s) throws InputError {
        int[] result = new int[3];
        int digitCount = 0;
        char[] sArr = s.toCharArray();
        for (char i : sArr) {
            if (i >= '0' && i <= '9') {
                digitCount++;
                if (digitCount == 4) {
                    throw new InputError("Too many digits from input");
                }
                result[digitCount - 1] = i - '0';
            }
        }

        return result;
    }
}
