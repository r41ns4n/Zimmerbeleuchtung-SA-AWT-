package sabel.com.jungwirth;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

class Zimmer implements Serializable {

    // DATA FIELDS
    private Date timestamp;
    private String ort;
    private float wert;

    public Zimmer(Date timestamp, String ort, float wert) {
        this.timestamp = timestamp;
        this.wert = wert;
        this.ort = ort;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public float getWert() {
        return wert;
    }

    public void setWert(float wert) {
        this.wert = wert;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return simpleDateFormat.format(timestamp) + " " + ort + "\n" + wert;
    }


}
