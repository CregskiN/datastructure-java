package COURSE;

import structuretools.LinkedList;

import java.util.Arrays;

public class KMP {

    public int[] getNext(String str) {
        /*
        * @sum 记录当前前n为匹配
        * */
        int i = 0, j = -1;
        int[] next = new int[str.length() + 1];
        next[0] = -1;

        while (i < str.length())
        {
            if (j == -1  || str.charAt(i) == str.charAt(j))
            {
                ++i;
                ++j;
                next[i] = j;
            }
            else
                j = next[j];
        }

        return next;
    }

    public int KMPValue(String masterStr, String devStr)
    {
        int i = 0;
        int j = 0;

        while (i < masterStr.length() && j < devStr.length())
        {
            if (j == -1 || masterStr.charAt(i) == devStr.charAt(j))
            {
                i++;
                j++;
            }
            else
                j = getNext(devStr)[j];
        }

        if (j == devStr.length())
            return i - j;
        else
            return -1;
    }



    public static void main(String[] args) {
        String masterStr = "abababca"; // 主串
        String devStr = "abababca"; // 模板串

        KMP kmp = new KMP();

        if (kmp.KMPValue(masterStr,devStr) == -1){
            System.out.println("两字符串不匹配");
        }else {
            System.out.println("从第" + (kmp.KMPValue(masterStr,devStr) + 1) + "个字符开始是匹配的");
        }



    }


}
