package com.ernesto;

public class Certificate implements Comparable<Certificate> {

    private int id;
    private String name;

    public Certificate() {

    }

    public Certificate(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }

        Certificate obj2 = (Certificate) obj;

        if ((this.id == obj2.getId()) && (this.name.equals(obj2.getName()))) {
            return true;
        }

        return false;
    }

    public int hashCode() {
        int tmp = 0;
        tmp = (id + name).hashCode();
        return tmp;
    }

    public int compareTo(Certificate that) {
        final int BEFORE = -1;
        final int AFTER = 1;

        if (that == null) {
            return BEFORE;
        }

        Comparable thisCertificate = this.getName();
        Comparable thatCertificate = that.getName();

        if (thisCertificate == null) {
            return AFTER;
        } else if (thatCertificate == null) {
            return BEFORE;
        } else {
            return thisCertificate.compareTo(thatCertificate);
        }
    }
}