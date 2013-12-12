package com.ernesto;

import com.ernesto.Certificate;
import java.util.Comparator;

public class Sorter implements Comparator<Certificate> {

    public int compare(Certificate c1, Certificate c2) {
        final int BEFORE = -1;
        final int AFTER = 1;

        if (c2 == null) {
            return BEFORE;
        }

        Comparable thiscertificate = c1.getName();
        Comparable thatcertificate = c2.getName();

        if (thiscertificate == null) {
            return AFTER * 1;
        } else if (thatcertificate == null) {
            return BEFORE * -1;
        } else {
            return (thiscertificate.compareTo(thatcertificate) * -1);
        }
    }

}