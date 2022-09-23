package com.valu.ahmedehabtask.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class RatingModel implements Parcelable {

    public RatingModel(Double rate, Integer count) {
        this.rate = rate;
        this.count = count;
    }

    @SerializedName("rate")
    private Double rate;
    @SerializedName("count")
    private Integer count;

    protected RatingModel(Parcel in) {
        if (in.readByte() == 0) {
            rate = null;
        } else {
            rate = in.readDouble();
        }
        if (in.readByte() == 0) {
            count = null;
        } else {
            count = in.readInt();
        }
    }

    public static final Creator<RatingModel> CREATOR = new Creator<RatingModel>() {
        @Override
        public RatingModel createFromParcel(Parcel in) {
            return new RatingModel(in);
        }

        @Override
        public RatingModel[] newArray(int size) {
            return new RatingModel[size];
        }
    };

    public Double getRate() {
        return rate;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (rate == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(rate);
        }
        if (count == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(count);
        }
    }
}
