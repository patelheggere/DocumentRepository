package patelheggere.com.documentrepository.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import patelheggere.com.documentrepository.R;
import patelheggere.com.documentrepository.datamodels.DocumentCategory;

/**
 * Created by Patel Heggere on 4/8/2018.
 */

public class DocumentCategoryAdapter extends RecyclerView.Adapter<DocumentCategoryAdapter.ViewHolder>{
private  final String TAG = "DocumentCategoryAdapter";
public Context mContext;
public List<DocumentCategory> mCategory;

public DocumentCategoryAdapter(Context mContext, List<DocumentCategory> mCategory)
        {
        this.mContext = mContext;
        this.mCategory = mCategory;
        }

@Override
public DocumentCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {

        View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.document_category_item, parent, false);
        return new DocumentCategoryAdapter.ViewHolder(view);
        }

@Override
public void onBindViewHolder(DocumentCategoryAdapter.ViewHolder holder, int position)
        {
            DocumentCategory ob =  mCategory.get(position);
            if(ob!=null)
            {
                if(ob.getCatName()!=null) {
                    if(ob.getCatId().equalsIgnoreCase("form"))
                    {
                     holder.mImageBg.setImageResource(R.mipmap.form_bg);
                     holder.mIcon.setImageResource(R.mipmap.forms_ic);
                    }
                    else if(ob.getCatId().equalsIgnoreCase("image"))
                    {
                        holder.mImageBg.setImageResource(R.mipmap.images_bg);
                        holder.mIcon.setImageResource(R.mipmap.images_ic);
                    }
                    else if(ob.getCatId().equalsIgnoreCase("my_docs"))
                    {
                        holder.mImageBg.setImageResource(R.mipmap.mydocuments_bg);
                        holder.mIcon.setImageResource(R.mipmap.mydocuments_ic);
                    }
                    else if(ob.getCatId().equalsIgnoreCase("bank"))
                    {
                        holder.mImageBg.setImageResource(R.mipmap.bankstatement_bg);
                        holder.mIcon.setImageResource(R.mipmap.bankstatement_ic);
                    }
                    else if(ob.getCatId().equalsIgnoreCase("other"))
                    {
                        holder.mImageBg.setImageResource(R.mipmap.otherdocuments_bg);
                        holder.mIcon.setImageResource(R.mipmap.otherdocuments_ic);
                    }
                    holder.mName.setText(ob.getCatName());
                    if (ob.getNumDocs() != null)
                        holder.mCount.setText("(" + String.valueOf(ob.getNumDocs()) + ")");
                }
            }

        }

@Override
public long getItemId(int position) {
        return super.getItemId(position);
        }

@Override
public int getItemCount() {
        return mCategory.size();
        }


public class ViewHolder extends RecyclerView.ViewHolder
{
    private TextView mName;
    private TextView mCount;
    private ImageView mImageBg;
    private ImageView mIcon;

    public ViewHolder(View v) {
        super(v);
        mName = v.findViewById(R.id.tv_cat_name) ;
        mCount = v.findViewById(R.id.tv_count);
        mImageBg = v.findViewById(R.id.cat_bg);
        mIcon = v.findViewById(R.id.iv_icon);
      //  mNotificationIcon = v.findViewById(R.id.ivnotificationicon);*/
    }
}
}
