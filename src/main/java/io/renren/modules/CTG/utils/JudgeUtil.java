package io.renren.modules.CTG.utils;

/**
 * @author tianyi
 * @date 2018-12-16 16:25
 */
public class JudgeUtil {

    /**
     * 由字符串获取判读代号
     * @param judge 文字判断结果
     * @return 结果代号
     */
    public static int getJudgeIntCodeByStr(String judge){
        switch (judge){
            case "不满意":return 0;
            case "有反应":return 1;
            case "可疑":return 2;
            case "无反应":return 3;
            default:return 4;
        }
    }
}
