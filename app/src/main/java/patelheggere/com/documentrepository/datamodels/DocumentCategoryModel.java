
package patelheggere.com.documentrepository.datamodels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DocumentCategoryModel implements Parcelable {

    @SerializedName("DocumentCategories")
    private List<DocumentCategory> mDocumentCategories;

    public DocumentCategoryModel(Parcel in) {
    }
    public DocumentCategoryModel() {
    }

    public static final Creator<DocumentCategoryModel> CREATOR = new Creator<DocumentCategoryModel>() {
        @Override
        public DocumentCategoryModel createFromParcel(Parcel in) {
            return new DocumentCategoryModel(in);
        }

        @Override
        public DocumentCategoryModel[] newArray(int size) {
            return new DocumentCategoryModel[size];
        }
    };

    public List<DocumentCategory> getDocumentCategories() {
        return mDocumentCategories;
    }

    public void setDocumentCategories(List<DocumentCategory> DocumentCategories) {
        mDocumentCategories = DocumentCategories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
