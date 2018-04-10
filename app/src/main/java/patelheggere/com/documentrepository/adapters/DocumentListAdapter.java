package patelheggere.com.documentrepository.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import patelheggere.com.documentrepository.R;
import patelheggere.com.documentrepository.datamodels.Document;
import patelheggere.com.documentrepository.datamodels.DocumentCategory;

/**
 * Created by Patel Heggere on 4/8/2018.
 */

public class DocumentListAdapter extends RecyclerView.Adapter<DocumentListAdapter.ViewHolder>{
    private  final String TAG = "DocumentListAdapter";
    public Context mContext;
    public List<Document> mDocument;

    public DocumentListAdapter(Context mContext, List<Document> mDocument)
    {
        this.mContext = mContext;
        this.mDocument = mDocument;
    }

    @Override
    public DocumentListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.document_list_item, parent, false);
        return new DocumentListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DocumentListAdapter.ViewHolder holder, int position)
    {
        Document ob =  mDocument.get(position);
        if(ob!=null)
        {
            if(ob.getDocType()!=null && ob.getDocType().equalsIgnoreCase("pdf"))
            {
               // String doc="<iframe src='http://docs.google.com/viewer?url=http://www.iasted.org/conferences/formatting/presentations-tips.ppt&embedded=true' width='100%' height='100%' style='border: none;'></iframe>";
                String doc="<iframe src=http://docs.google.com/viewer?url="+ob.getDocUrl()+"</iframe>";
                holder.mWv.setVisibility(View.VISIBLE);
                holder.mWv.getSettings().setJavaScriptEnabled(true);
                holder.mWv.getSettings().setAllowFileAccess(true);
                holder.mWv.loadUrl(doc);
                System.out.println("PDF url:"+doc);
                holder.mImage.setVisibility(View.INVISIBLE);
            }
            else
            {
                holder.mImage.setVisibility(View.VISIBLE);
                holder.mWv.setVisibility(View.INVISIBLE);
            }
            if(ob.getDocName()!=null)
                holder.mName.setText(ob.getDocName());
            if(ob.getDocSize()!=null)
                holder.mSize.setText(ob.getDocSize());
            if(ob.getDocUrl()!=null)
                Glide.with(mContext).load(ob.getDocUrl()).into(holder.mImage);
        }

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mDocument.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView mName;
        private TextView mSize;
        private ImageView mImage;
        private WebView mWv;


        public ViewHolder(View v) {
            super(v);
            mName = v.findViewById(R.id.tv_file_name) ;
            mSize = v.findViewById(R.id.tv_file_size);
            mImage = v.findViewById(R.id.iv_document);
            mWv = v.findViewById(R.id.iv_document_pdf);

            //  mNotificationIcon = v.findViewById(R.id.ivnotificationicon);*/
        }
    }
}
