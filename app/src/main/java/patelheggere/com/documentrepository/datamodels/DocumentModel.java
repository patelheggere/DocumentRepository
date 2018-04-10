
package patelheggere.com.documentrepository.datamodels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class DocumentModel  implements Parcelable{

    @SerializedName("documents")
    private List<Document> mDocuments;

    protected DocumentModel(Parcel in) {
        mDocuments = in.createTypedArrayList(Document.CREATOR);
    }
    public DocumentModel()
    {

    }

    public static final Creator<DocumentModel> CREATOR = new Creator<DocumentModel>() {
        @Override
        public DocumentModel createFromParcel(Parcel in) {
            return new DocumentModel(in);
        }

        @Override
        public DocumentModel[] newArray(int size) {
            return new DocumentModel[size];
        }
    };

    public List<Document> getDocuments() {
        return mDocuments;
    }

    public void setDocuments(List<Document> documents) {
        mDocuments = documents;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(mDocuments);
    }
}
