package org.september.util;

import java.util.Comparator;
import org.september.entity.ConexionMensual;

public class ConexionMensualComparable implements Comparator<ConexionMensual> {

    public int compare(ConexionMensual o1, ConexionMensual o2) {
        return o1.getMes().compareTo(o2.getMes());
    }

}
