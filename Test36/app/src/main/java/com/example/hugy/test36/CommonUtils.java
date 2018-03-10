/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.hugy.test36;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class CommonUtils {

    /**
     * return if str is empty
     *
     * @param str 带判断string
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || str.equalsIgnoreCase("null") || str.isEmpty() || str.equals("");
    }

    /**
     * get format date
     *
     * @param millisTimes 毫秒时间
     * @return 格式化输出时间
     */
    public static String getFormatDate(long millisTimes) {
        return new SimpleDateFormat("yyyy年MM月dd日").format(new Date(millisTimes));
    }

    /**
     * decode Unicode string
     *
     * @param unicode unicode编码字符串
     * @return 普通字符串
     */
    public static String decodeUnicodeStr(String unicode) {
        StringBuilder sb = new StringBuilder(unicode.length());
        char[] chars = unicode.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '\\' && chars[i + 1] == 'u') {
                char cc = 0;
                for (int j = 0; j < 4; j++) {
                    char ch = Character.toLowerCase(chars[i + 2 + j]);
                    if ('0' <= ch && ch <= '9' || 'a' <= ch && ch <= 'f') {
                        cc |= (Character.digit(ch, 16) << (3 - j) * 4);
                    } else {
                        cc = 0;
                        break;
                    }
                }
                if (cc > 0) {
                    i += 5;
                    sb.append(cc);
                    continue;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * encode Unicode string
     *
     * @param s 带转化string
     * @return
     */
    public static String encodeUnicodeStr(String s) {
        StringBuilder sb = new StringBuilder(s.length() * 3);
        for (char c : s.toCharArray()) {
            if (c < 256) {
                sb.append(c);
            } else {
                sb.append("\\u");
                sb.append(Character.forDigit((c >>> 12) & 0xf, 16));
                sb.append(Character.forDigit((c >>> 8) & 0xf, 16));
                sb.append(Character.forDigit((c >>> 4) & 0xf, 16));
                sb.append(Character.forDigit((c) & 0xf, 16));
            }
        }
        return sb.toString();
    }

    /**
     * convert time str
     *
     * @param time 待转时间
     * @return
     */
    public static String convertTime(int time) {

        time /= 1000;
        int minute = time / 60;
        int second = time % 60;
        minute %= 60;
        return String.format("%02d:%02d", minute, second);
    }

    /**
     * url is usable
     *
     * @param url 待判断url
     * @return true if url is usable
     */
    public static boolean isUrlUsable(String url) {
        if (CommonUtils.isEmpty(url)) {
            return false;
        }

        URL urlTemp = null;
        HttpURLConnection connt = null;
        try {
            urlTemp = new URL(url);
            connt = (HttpURLConnection) urlTemp.openConnection();
            connt.setRequestMethod("HEAD");
            int returnCode = connt.getResponseCode();
            if (returnCode == HttpURLConnection.HTTP_OK) {
                return true;
            }
        } catch (Exception e) {
            return false;
        } finally {
            connt.disconnect();
        }
        return false;
    }

    /**
     * is url
     *
     * @param url 待判断url
     * @return true if is url
     */
    public static boolean isUrl(String url) {
        Pattern pattern = Pattern.compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");
        return pattern.matcher(url).matches();
    }

    /**
     * is phone num
     *
     * @param phoneStr 待判断手机号
     * @return true if is phone
     */
    public static boolean isPhone(String phoneStr) {
        String PHONE_PATTERN = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17([0,1,5,6,7,]))|(18[0-4,5-9]))\\d{8}$";
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        return pattern.matcher(phoneStr).matches();
    }

    /**
     * get drawable id
     *
     * @param context      context
     * @param drawableName drawable file's name
     * @return integer value of specified drawable file
     */
    public static int getDrawableIdByName(Context context, String drawableName) {

        return context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
    }

    /**
     * 改变系统状态栏颜色
     *
     * @param activity   activity对象
     * @param colorResId 颜色资源ID
     */
    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colorResId));

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断字符串是否为null或空
     *
     * @param string 待判断string
     * @return true if is null or empty
     */

    public static boolean isNullOrEmpty(String string) {
        return null == string || string.isEmpty() || string.equals("null");
    }

    /**
     * @param email 待判断邮箱
     * @return true if is email
     */

    public static boolean isEmail(String email) {
        String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * @param idCard 待判断身份证
     * @return true if is IDCard
     */

    public static boolean isIDCard(String idCard) {
        String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{17}(\\d|X|x|Y|y)$)";
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }
    /**
     * 判断登录密码（6位以上字母加数字）
     * */
    public static boolean isPwd(String pwd) {
        String REGEX_PWD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$";
        return Pattern.matches(REGEX_PWD, pwd);
    }

    /**
     * 车架号(17位以上)
     * */
    public static boolean isCarNum(String carNum){
        String REGEX_CAR_NUM = "^[a-zA-Z0-9]{17}$";
        return Pattern.matches(REGEX_CAR_NUM, carNum);
    }

    /**
    * 车牌号
    * */
    public static boolean isLicensePlateNum(String licensePlateNum){
        String LICENSE_PLATE_NUM = "^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$";
        return Pattern.matches(LICENSE_PLATE_NUM, licensePlateNum);
    }

    /**
     * 车辆所有人(判断是不是汉字)
     * */
    public static boolean isChinaCharacters(String name){
        String CHINA_CHARACTERS = "^[\\u4e00-\\u9fa5]*$";
        return Pattern.matches(CHINA_CHARACTERS, name);
    }

    /**
     * 判断Emoji 表情
     * */
    public static boolean isEmoji(String emoji){
        String IS_EMOJI = "[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]";
        return Pattern.matches(IS_EMOJI, emoji);
    }

    /**
     *过滤emoji表情
     * */

    public static boolean containsEmoji(String source) {
        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {

        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }

    public static boolean isSixPwd(String num){
        String IS_SIXPWD = "^[0-9]{6}$";
        return Pattern.matches(IS_SIXPWD, num);
    }
}
