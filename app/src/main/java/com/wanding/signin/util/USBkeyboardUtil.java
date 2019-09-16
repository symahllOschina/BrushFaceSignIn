package com.wanding.signin.util;

import android.util.Log;
import android.view.KeyEvent;

import java.util.Arrays;

/**
 * 读取外接USB键盘值帮助类
 */
public class USBkeyboardUtil {

    private static  String TAG = USBkeyboardUtil.class.getSimpleName();

    /**
     * USB外接键盘输入金额
     */
    public static String getKeyValue(KeyEvent event, StringBuilder pending){
        String value = "";

        if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_NUMPAD_0){
            /*0键*/
            Log.e(TAG,"按下键盘0键");
            value = "0";
            if("0.0".equals(pending.toString())){
                return pending.toString();
            }
            pending = pending.append(value);

            if (pending.toString().contains(".")) {
                if (pending.length() - 1 - pending.toString().indexOf(".") > 2) {
                    //如果内容包含小数点，小数点后大于两位，删除最后最后一个字符
                    pending = pending.deleteCharAt(pending.length()-1);
                }
            }
            //输入内容头为0的情况下，只能输入小数点
            if (pending.toString().startsWith("0") && pending.toString().trim().length() > 1) {
                if (!".".equals(pending.toString().substring(1, 2))) {
                    pending = pending.deleteCharAt(pending.length()-1);
                    return pending.toString();
                }
            }

            return pending.toString();
        }
        if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_NUMPAD_1){
            /*0键*/
            Log.e(TAG,"按下键盘1键");
            value = "1";
            if (!pending.toString().contains(".")) {
                if(pending.toString().length()>=5){
                    return pending.toString();
                }
            }
            if(pending.toString().length()>0){
                if("0".equals(pending.toString())){
                    //清空pending
                    pending.delete( 0, pending.length() );
                }
            }
            pending = pending.append(value);
            if (pending.toString().contains(".")) {
                if (pending.length() - 1 - pending.toString().indexOf(".") > 2) {
                    //如果内容包含小数点，小数点后大于两位，删除最后最后一个字符
                    pending = pending.deleteCharAt(pending.length()-1);
                }
            }
            return pending.toString();
        }
        if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_NUMPAD_2){
            /*0键*/
            Log.e(TAG,"按下键盘2键");
            value = "2";
            if (!pending.toString().contains(".")) {
                if(pending.toString().length()>=5){
                    return pending.toString();
                }
            }
            if(pending.toString().length()>0){
                if("0".equals(pending.toString())){
                    //清空pending
                    pending.delete( 0, pending.length() );
                }
            }
            pending = pending.append(value);
            if (pending.toString().contains(".")) {
                if (pending.length() - 1 - pending.toString().indexOf(".") > 2) {
                    //如果内容包含小数点，小数点后大于两位，删除最后最后一个字符
                    pending = pending.deleteCharAt(pending.length()-1);
                }
            }
            return pending.toString();

        }
        if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_NUMPAD_3){
            /*0键*/
            Log.e(TAG,"按下键盘3键");
            value = "3";
            if (!pending.toString().contains(".")) {
                if(pending.toString().length()>=5){
                    return pending.toString();
                }
            }
            if(pending.toString().length()>0){
                if("0".equals(pending.toString())){
                    //清空pending
                    pending.delete( 0, pending.length() );
                }
            }
            pending = pending.append(value);
            if (pending.toString().contains(".")) {
                if (pending.length() - 1 - pending.toString().indexOf(".") > 2) {
                    //如果内容包含小数点，小数点后大于两位，删除最后最后一个字符
                    pending = pending.deleteCharAt(pending.length()-1);
                }
            }
            return pending.toString();
        }
        if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_NUMPAD_4){
            /*0键*/
            Log.e(TAG,"按下键盘4键");
            value = "4";
            if (!pending.toString().contains(".")) {
                if(pending.toString().length()>=5){
                    return pending.toString();
                }
            }
            if(pending.toString().length()>0){
                if("0".equals(pending.toString())){
                    //清空pending
                    pending.delete( 0, pending.length() );
                }
            }
            pending = pending.append(value);
            if (pending.toString().contains(".")) {
                if (pending.length() - 1 - pending.toString().indexOf(".") > 2) {
                    //如果内容包含小数点，小数点后大于两位，删除最后最后一个字符
                    pending = pending.deleteCharAt(pending.length()-1);
                }
            }

            return pending.toString();

        }
        if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_NUMPAD_5){
            /*0键*/
            Log.e(TAG,"按下键盘5键");
            value = "5";
            if (!pending.toString().contains(".")) {
                if(pending.toString().length()>=5){
                    return pending.toString();
                }
            }
            if(pending.toString().length()>0){
                if("0".equals(pending.toString())){
                    //清空pending
                    pending.delete( 0, pending.length() );
                }
            }
            pending = pending.append(value);
            if (pending.toString().contains(".")) {
                if (pending.length() - 1 - pending.toString().indexOf(".") > 2) {
                    //如果内容包含小数点，小数点后大于两位，删除最后最后一个字符
                    pending = pending.deleteCharAt(pending.length()-1);
                }
            }
            return pending.toString();

        }
        if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_NUMPAD_6){
            /*0键*/
            Log.e(TAG,"按下键盘6键");
            value = "6";
            if (!pending.toString().contains(".")) {
                if(pending.toString().length()>=5){
                    return pending.toString();
                }
            }
            if(pending.toString().length()>0){
                if("0".equals(pending.toString())){
                    //清空pending
                    pending.delete( 0, pending.length() );
                }
            }
            pending = pending.append(value);
            if (pending.toString().contains(".")) {
                if (pending.length() - 1 - pending.toString().indexOf(".") > 2) {
                    //如果内容包含小数点，小数点后大于两位，删除最后最后一个字符
                    pending = pending.deleteCharAt(pending.length()-1);
                }
            }
            return pending.toString();

        }
        if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_NUMPAD_7){
            /*0键*/
            Log.e(TAG,"按下键盘7键");
            value = "7";
            if (!pending.toString().contains(".")) {
                if(pending.toString().length()>=5){
                    return pending.toString();
                }
            }
            if(pending.toString().length()>0){
                if("0".equals(pending.toString())){
                    //清空pending
                    pending.delete( 0, pending.length() );
                }
            }
            pending = pending.append(value);
            if (pending.toString().contains(".")) {
                if (pending.length() - 1 - pending.toString().indexOf(".") > 2) {
                    //如果内容包含小数点，小数点后大于两位，删除最后最后一个字符
                    pending = pending.deleteCharAt(pending.length()-1);
                }
            }
            return pending.toString();

        }
        if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_NUMPAD_8){
            /*0键*/
            Log.e(TAG,"按下键盘8键");
            value = "8";
            if (!pending.toString().contains(".")) {
                if(pending.toString().length()>=5){
                    return pending.toString();
                }
            }
            if(pending.toString().length()>0){
                if("0".equals(pending.toString())){
                    //清空pending
                    pending.delete( 0, pending.length() );
                }
            }
            pending = pending.append(value);
            if (pending.toString().contains(".")) {
                if (pending.length() - 1 - pending.toString().indexOf(".") > 2) {
                    //如果内容包含小数点，小数点后大于两位，删除最后最后一个字符
                    pending = pending.deleteCharAt(pending.length()-1);
                }
            }
            return pending.toString();

        }
        if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_NUMPAD_9){
            /*0键*/
            Log.e(TAG,"按下键盘9键");
            value = "9";
            if (!pending.toString().contains(".")) {
                if(pending.toString().length()>=5){
                    return pending.toString();
                }
            }
            if(pending.toString().length()>0){
                if("0".equals(pending.toString())){
                    //清空pending
                    pending.delete( 0, pending.length() );
                }
            }
            pending = pending.append(value);
            if (pending.toString().contains(".")) {
                if (pending.length() - 1 - pending.toString().indexOf(".") > 2) {
                    //如果内容包含小数点，小数点后大于两位，删除最后最后一个字符
                    pending = pending.deleteCharAt(pending.length()-1);
                }
            }
            return pending.toString();
        }
        if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_NUMPAD_DOT){
            /*0键*/
            Log.e(TAG,"按下键盘.键");
            value = ".";
            if(pending.length()>0){
                if (judje1(pending)) {
                    pending = pending.append(".");
                    if (pending.toString().contains(".")) {
                        if (pending.length() - 1 - pending.toString().indexOf(".") > 2) {
                            //如果内容包含小数点，小数点后大于两位，删除最后最后一个字符
                            pending = pending.deleteCharAt(pending.length()-1);
                        }
                    }
                    return pending.toString();
                }
            }

        }
        if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_TAB){
            /*0键*/
            Log.e(TAG,"按下键盘TAB键");


        }


        return pending.toString();
    }


    public static boolean judje1(StringBuilder pending) {
        String a = "+-*/.";
        int[] b = new int[a.length()];
        int max;
        for (int i = 0; i < a.length(); i++) {
            String c = "" + a.charAt(i);
            b[i] = pending.lastIndexOf(c);
        }
        Arrays.sort(b);
        if (b[a.length() - 1] == -1) {
            max = 0;
        } else {
            max = b[a.length() - 1];
        }
        if (pending.indexOf(".", max) == -1) {
            return true;
        } else {
            return false;
        }
    }


    /** USB扫码盒子字符解析：keyCode转换为字符 */
    public static String keyCodeToChar(int code, boolean isShift){
        switch(code){

            case KeyEvent.KEYCODE_SHIFT_LEFT: return "";

            //数字键10个 + 符号10个
            case KeyEvent.KEYCODE_0: return isShift ? ")" : "0";
            case KeyEvent.KEYCODE_1: return isShift ? "!" : "1";
            case KeyEvent.KEYCODE_2: return isShift ? "@" : "2";
            case KeyEvent.KEYCODE_3: return isShift ? "#" : "3";
            case KeyEvent.KEYCODE_4: return isShift ? "$" : "4";
            case KeyEvent.KEYCODE_5: return isShift ? "%" : "5";
            case KeyEvent.KEYCODE_6: return isShift ? "^" : "6";
            case KeyEvent.KEYCODE_7: return isShift ? "&" : "7";
            case KeyEvent.KEYCODE_8: return isShift ? "*" : "8";
            case KeyEvent.KEYCODE_9: return isShift ? "(" : "9";

            //字母键26个小写 + 26个大写
            case KeyEvent.KEYCODE_A: return isShift ? "A" : "a";
            case KeyEvent.KEYCODE_B: return isShift ? "B" : "b";
            case KeyEvent.KEYCODE_C: return isShift ? "C" : "c";
            case KeyEvent.KEYCODE_D: return isShift ? "D" : "d";
            case KeyEvent.KEYCODE_E: return isShift ? "E" : "e";
            case KeyEvent.KEYCODE_F: return isShift ? "F" : "f";
            case KeyEvent.KEYCODE_G: return isShift ? "G" : "g";
            case KeyEvent.KEYCODE_H: return isShift ? "H" : "h";
            case KeyEvent.KEYCODE_I: return isShift ? "I" : "i";
            case KeyEvent.KEYCODE_J: return isShift ? "J" : "j";
            case KeyEvent.KEYCODE_K: return isShift ? "K" : "k";
            case KeyEvent.KEYCODE_L: return isShift ? "L" : "l";
            case KeyEvent.KEYCODE_M: return isShift ? "M" : "m";
            case KeyEvent.KEYCODE_N: return isShift ? "N" : "n";
            case KeyEvent.KEYCODE_O: return isShift ? "O" : "o";
            case KeyEvent.KEYCODE_P: return isShift ? "P" : "p";
            case KeyEvent.KEYCODE_Q: return isShift ? "Q" : "q";
            case KeyEvent.KEYCODE_R: return isShift ? "R" : "r";
            case KeyEvent.KEYCODE_S: return isShift ? "S" : "s";
            case KeyEvent.KEYCODE_T: return isShift ? "T" : "t";
            case KeyEvent.KEYCODE_U: return isShift ? "U" : "u";
            case KeyEvent.KEYCODE_V: return isShift ? "V" : "v";
            case KeyEvent.KEYCODE_W: return isShift ? "W" : "w";
            case KeyEvent.KEYCODE_X: return isShift ? "X" : "x";
            case KeyEvent.KEYCODE_Y: return isShift ? "Y" : "y";
            case KeyEvent.KEYCODE_Z: return isShift ? "Z" : "z";

            //符号键11个 + 11个
            case KeyEvent.KEYCODE_COMMA: return isShift ? "<" : ",";
            case KeyEvent.KEYCODE_PERIOD: return isShift ? ">" : ".";
            case KeyEvent.KEYCODE_SLASH: return isShift ? "?" : "/";
            case KeyEvent.KEYCODE_BACKSLASH: return isShift ? "|" : "\\";
            case KeyEvent.KEYCODE_APOSTROPHE: return isShift ? "\"" : "'";
            case KeyEvent.KEYCODE_SEMICOLON: return isShift ? ":" : ";";
            case KeyEvent.KEYCODE_LEFT_BRACKET: return isShift ? "{" : "[";
            case KeyEvent.KEYCODE_RIGHT_BRACKET: return isShift ? "}" : "]";
            case KeyEvent.KEYCODE_GRAVE: return isShift ? "~" : "`";
            case KeyEvent.KEYCODE_EQUALS: return isShift ? "+" : "=";
            case KeyEvent.KEYCODE_MINUS: return isShift ? "_" : "-";

            default: return "";
        }
    }

}
