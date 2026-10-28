package com.example.smoothie;

import android.os.Parcel;
import android.os.Parcelable;

// Contains the letters I, M, S (Smoothie).
public class Smoothie implements Parcelable {
    private final String nume;
    private final String fruct;
    private final String marime;
    private final boolean cuGheata;
    private final boolean vegan;
    private final float rating;

    public Smoothie(String nume, String fruct, String marime, boolean cuGheata, boolean vegan, float rating) {
        this.nume = nume;
        this.fruct = fruct;
        this.marime = marime;
        this.cuGheata = cuGheata;
        this.vegan = vegan;
        this.rating = rating;
    }

    protected Smoothie(Parcel in) {
        nume = in.readString();
        fruct = in.readString();
        marime = in.readString();
        cuGheata = in.readByte() != 0;
        vegan = in.readByte() != 0;
        rating = in.readFloat();
    }

    public static final Creator<Smoothie> CREATOR = new Creator<Smoothie>() {
        @Override
        public Smoothie createFromParcel(Parcel in) {
            return new Smoothie(in);
        }

        @Override
        public Smoothie[] newArray(int size) {
            return new Smoothie[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeString(fruct);
        dest.writeString(marime);
        dest.writeByte((byte) (cuGheata ? 1 : 0));
        dest.writeByte((byte) (vegan ? 1 : 0));
        dest.writeFloat(rating);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getNume() { return nume; }
    public String getFruct() { return fruct; }
    public String getMarime() { return marime; }
    public boolean isCuGheata() { return cuGheata; }
    public boolean isVegan() { return vegan; }
    public float getRating() { return rating; }

    @Override
    public String toString() {
        return "Smoothie: " + nume
                + "\nFruct: " + fruct
                + "\nMarime: " + marime
                + "\nCu gheata: " + (cuGheata ? "da" : "nu")
                + "\nVegan: " + (vegan ? "da" : "nu")
                + "\nRating: " + rating;
    }
}
