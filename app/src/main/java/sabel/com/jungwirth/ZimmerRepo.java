package sabel.com.jungwirth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ZimmerRepo implements Serializable{

    // DATA FIELDS
    private List<Zimmer> zimmerpunkte;

    // CONSTRUCTOR
    public ZimmerRepo() {
        zimmerpunkte = new ArrayList<>();
    }

    public void add(Zimmer zimmer) {
        if (zimmer != null) {
            zimmerpunkte.add(zimmer);
        }
    } // END void add

    public Zimmer get(int index) {
        if (index < 0 || index >= zimmerpunkte.size()) {
            return null;
        }
        return zimmerpunkte.get(index);
    }

    public int size() {
        return zimmerpunkte.size();
    }

    public List<Zimmer> getZimmerpunkte() {
        return zimmerpunkte;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Zimmer zimmer : zimmerpunkte) {
            sb.append(zimmer.toString());
            sb.append(String.format("%n"));
        }
        return  sb.toString();
    }
} // END CLASS
