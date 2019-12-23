package COURSE;

public class KMP {

    public int[] getNext(String str) {
        /*
         * @sum 记录当前前n为匹配
         * */
        int i = 0, j = -1;
        int[] next = new int[str.length() + 1];
        next[0] = -1;

        while (i < str.length()) {

            if (j == -1 || str.charAt(i) == str.charAt(j)) {
                ++i;
                ++j;
                next[i] = j;
            } else
                j = next[j];
        }

        return next;
    }

    public int KMPValue(String masterStr, String devStr) {
        int i = 0;
        int j = 0;

        while (i < masterStr.length() && j < devStr.length()) {
            char masterCur = masterStr.charAt(i);
            char devCur = devStr.charAt(j);

            if (j == -1 || masterCur == devCur) {
                i++;
                j++;
            } else {
                j = getNext(devStr)[j];
            }
        }

        if (j == devStr.length())
            return i - j;
        else
            return -1;
    }

    public void compare(String masterStr, String devStr) {

        int startIndex = KMPValue(masterStr, devStr);

        if (startIndex == -1) {
            System.out.println("两字符串不匹配");
        } else {
            System.out.println("从第" + (startIndex + 1) + "个字符开始是匹配的");
        }
    }

    public static void main(String[] args) {
        String masterStr = "abababca"; // 主串
        String devStr = "abababca"; // 模板串

        KMP kmp = new KMP();
        kmp.compare(masterStr, devStr);
    }
}