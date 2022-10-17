/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package regex;

/**
 *
 * @author Anh Thu
 */
public class RegexHelper {
    public static boolean regexCMND(String number) {
        String regex = "[0-9]{9}";
        return number.matches(regex);
    }
    public static boolean regexEmail(String email) {
        String regex = "\\w+.@\\w+.\\w{2,5}";
        return email.matches(regex);
    }
    public static boolean regexGia(String so)
    {
        String regex = "([1-9][0-9]{3,7})(.0)?";
        return  so.matches(regex);
    }
    public static boolean regexPhoneNumber(String number) {
        String regex = "^((09(\\d){8})|(08(\\d){8})|(03(\\d){8})|(07(\\d){8})|(05(\\d){8}))$";
        return number.matches(regex);
    }
    public static boolean regexCustomerName(String name) {
        String firstLetter="[A-EGHIK-VXYÂĐỔÔÚỨ]";
      	String otherLetters="[a-eghik-vxyàáâãèéêìíòóôõùúýỳỹỷỵựửữừứưụủũợởỡờớơộổỗồốọỏịỉĩệểễềếẹẻẽặẳẵằắăậẩẫầấạảđ₫]";
      	String regexString="^"
                 +firstLetter+otherLetters+"+\\s"
                 +"("+firstLetter+otherLetters+"+\\s)*"
                 +firstLetter+otherLetters+"+$";
        
        return name.matches(regexString);
    }
    
}
