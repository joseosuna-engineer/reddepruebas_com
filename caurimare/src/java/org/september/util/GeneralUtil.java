

package org.september.util;

public class GeneralUtil {
    
    public static String getStatusName(int status){
        switch(status){
            case 0: return "ESTABLE";
            case 1: return "CRITICO";
            default: return "INESTABLE";            
        }
    }
    
    
}
