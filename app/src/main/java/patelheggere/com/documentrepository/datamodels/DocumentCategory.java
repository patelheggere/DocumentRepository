
package patelheggere.com.documentrepository.datamodels;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class DocumentCategory implements Parcelable{

    @SerializedName("cat_background_img")
    private String mCatBackgroundImg;
    @SerializedName("cat_icon")
    private String mCatIcon;
    @SerializedName("cat_id")
    private String mCatId;
    @SerializedName("cat_name")
    private String mCatName;
    @SerializedName("num_docs")
    private Long mNumDocs;

    public DocumentCategory()
    {

    }
    public DocumentCategory(Parcel in) {
        mCatBackgroundImg = in.readString();
        mCatIcon = in.readString();
        mCatId = in.readString();
        mCatName = in.readString();
        if (in.readByte() == 0) {
            mNumDocs = null;
        } else {
            mNumDocs = in.readLong();
        }
    }

    public static final Creator<DocumentCategory> CREATOR = new Creator<DocumentCategory>() {
        @Override
        public DocumentCategory createFromParcel(Parcel in) {
            return new DocumentCategory(in);
        }

        @Override
        public DocumentCategory[] newArray(int size) {
            return new DocumentCategory[size];
        }
    };

    public String getCatBackgroundImg() {
        return mCatBackgroundImg;
    }

    public void setCatBackgroundImg(String catBackgroundImg) {
        mCatBackgroundImg = catBackgroundImg;
    }

    public String getCatIcon() {
        return mCatIcon;
    }

    public void setCatIcon(String catIcon) {
        mCatIcon = catIcon;
    }

    public String getCatId() {
        return mCatId;
    }

    public void setCatId(String catId) {
        mCatId = catId;
    }

    public String getCatName() {
        return mCatName;
    }

    public void setCatName(String catName) {
        mCatName = catName;
    }

    public Long getNumDocs() {
        return mNumDocs;
    }

    public void setNumDocs(Long numDocs) {
        mNumDocs = numDocs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mCatBackgroundImg);
        parcel.writeString(mCatIcon);
        parcel.writeString(mCatId);
        parcel.writeString(mCatName);
        if (mNumDocs == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(mNumDocs);
        }
    }
}
