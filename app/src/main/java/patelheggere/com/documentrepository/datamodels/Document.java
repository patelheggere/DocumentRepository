
package patelheggere.com.documentrepository.datamodels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Document implements Parcelable {

    @SerializedName("doc_name")
    private String mDocName;
    @SerializedName("doc_size")
    private String mDocSize;
    @SerializedName("doc_type")
    private String mDocType;
    @SerializedName("doc_url")
    private String mDocUrl;

    public Document()
    {

    }
    protected Document(Parcel in) {
        mDocName = in.readString();
        mDocSize = in.readString();
        mDocType = in.readString();
        mDocUrl = in.readString();
    }

    public static final Creator<Document> CREATOR = new Creator<Document>() {
        @Override
        public Document createFromParcel(Parcel in) {
            return new Document(in);
        }

        @Override
        public Document[] newArray(int size) {
            return new Document[size];
        }
    };

    public String getDocName() {
        return mDocName;
    }

    public void setDocName(String docName) {
        mDocName = docName;
    }

    public String getDocSize() {
        return mDocSize;
    }

    public void setDocSize(String docSize) {
        mDocSize = docSize;
    }

    public String getDocType() {
        return mDocType;
    }

    public void setDocType(String docType) {
        mDocType = docType;
    }

    public String getDocUrl() {
        return mDocUrl;
    }

    public void setDocUrl(String docUrl) {
        mDocUrl = docUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mDocName);
        parcel.writeString(mDocSize);
        parcel.writeString(mDocType);
        parcel.writeString(mDocUrl);
    }
}
