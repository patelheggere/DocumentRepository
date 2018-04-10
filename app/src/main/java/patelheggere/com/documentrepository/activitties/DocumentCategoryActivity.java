package patelheggere.com.documentrepository.activitties;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import patelheggere.com.documentrepository.Helper.RecyclerItemClickListener;
import patelheggere.com.documentrepository.R;
import patelheggere.com.documentrepository.adapters.DocumentCategoryAdapter;
import patelheggere.com.documentrepository.datamodels.DocumentCategory;
import patelheggere.com.documentrepository.datamodels.DocumentCategoryModel;
import patelheggere.com.documentrepository.AppController;

import static android.provider.MediaStore.Video.VideoColumns.CATEGORY;
import static patelheggere.com.documentrepository.Helper.AppConstants.CATEGORY_URL;

public class DocumentCategoryActivity extends AppCompatActivity {

    private RecyclerView mCategoryRecyclerView;
    private List<DocumentCategory> categoryModelList;
    private Gson mGson;
    private DocumentCategoryAdapter mDocumentCategoryAdapter;
    private ProgressBar progressBarNBMessage;
    private DocumentCategoryModel documentCategoryModel = null;
    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionBar = getSupportActionBar();
        if(mActionBar!=null)
        {
            mActionBar.setTitle(getString(R.string.cat_title));
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
        setContentView(R.layout.activity_document_category);
        initializeViewComponents();
        getDocumentCategory();
    }

    private void getDocumentCategory() {
        mGson = new Gson();
      documentCategoryModel = new DocumentCategoryModel();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, CATEGORY_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                documentCategoryModel = mGson.fromJson(response.toString(), DocumentCategoryModel.class);
                for(int i = 0; i<documentCategoryModel.getDocumentCategories().size();i++) {
                    categoryModelList.add(documentCategoryModel.getDocumentCategories().get(i));
                }
                mDocumentCategoryAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void initializeViewComponents() {
        categoryModelList = new ArrayList<>();
        mCategoryRecyclerView = findViewById(R.id.rv_category);
        mDocumentCategoryAdapter = new DocumentCategoryAdapter(DocumentCategoryActivity.this ,categoryModelList);
        mCategoryRecyclerView.setAdapter(mDocumentCategoryAdapter);
       // mDocumentCategoryAdapter.notifyDataSetChanged();
        mCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mCategoryRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(DocumentCategoryActivity.this, mCategoryRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        selectedCategory(categoryModelList.get(position));
                        RecyclerView.ViewHolder holderView = mCategoryRecyclerView.findViewHolderForLayoutPosition(position);

                    }

                    @Override public void onLongItemClick(View view, int position) {

                    }
                })
        );
    }

    private void selectedCategory(DocumentCategory documentCategory) {
        Intent intent = new Intent(getApplicationContext(), DocumentListActivity.class);
        intent.putExtra("category", documentCategory);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
