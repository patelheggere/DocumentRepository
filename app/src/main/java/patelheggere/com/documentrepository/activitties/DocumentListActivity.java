package patelheggere.com.documentrepository.activitties;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import patelheggere.com.documentrepository.AppController;
import patelheggere.com.documentrepository.Helper.RecyclerItemClickListener;
import patelheggere.com.documentrepository.R;
import patelheggere.com.documentrepository.adapters.DocumentListAdapter;
import patelheggere.com.documentrepository.datamodels.Document;
import patelheggere.com.documentrepository.datamodels.DocumentCategory;
import patelheggere.com.documentrepository.datamodels.DocumentModel;
import static patelheggere.com.documentrepository.Helper.AppConstants.DOCUMENTS_LIST;

public class DocumentListActivity extends AppCompatActivity {

    private DocumentCategory mDocumentCategory;
    private List<Document> mDocumentList;
    private DocumentModel mDocumentModel;
    private RecyclerView mDocumentListRecyclerView;
    private DocumentListAdapter mDocumentListAdapter;
    private Gson mGson;
    private WebView wv;
    private ImageView mSortImageView;
    private boolean isReverse;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> search_result_arraylist;
    private String keyword;
    private ListView searchListView;
    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_list);
        mDocumentCategory = getIntent().getParcelableExtra("category");
        initializeViewComponents();
        getDocuments();
        searchListView.setAdapter(adapter);
    }

    private void getDocuments() {
        mGson = new Gson();
        mDocumentModel = new DocumentModel();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, DOCUMENTS_LIST + mDocumentCategory.getCatId(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mDocumentModel = mGson.fromJson(response.toString(), DocumentModel.class);
                for (int i = 0; i < mDocumentModel.getDocuments().size(); i++) {
                    mDocumentList.add(mDocumentModel.getDocuments().get(i));
                }
                mDocumentListAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void initializeViewComponents() {
        mActionBar = getSupportActionBar();
        if(mActionBar!=null)
        {
            mActionBar.setTitle(getString(R.string.my_doc));
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
        mDocumentList = new ArrayList<>();
        search_result_arraylist = new ArrayList<>();
        searchListView = findViewById(R.id.listview);
        mSortImageView = findViewById(R.id.iv_sort);
        mDocumentListRecyclerView = findViewById(R.id.rv_doc_list);
        mDocumentListAdapter = new DocumentListAdapter(DocumentListActivity.this, mDocumentList);
        mDocumentListRecyclerView.setAdapter(mDocumentListAdapter);
        // mDocumentCategoryAdapter.notifyDataSetChanged();
        mDocumentListRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mDocumentListRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(DocumentListActivity.this, mDocumentListRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //selectedCategory(categoryModelList.get(position));
                        RecyclerView.ViewHolder holderView = mDocumentListRecyclerView.findViewHolderForLayoutPosition(position);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );

        mSortImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sort();
            }
        });
    }

    private void sort() {
        if (!isReverse) {
            Collections.sort(mDocumentList, new Comparator<Document>() {
                public int compare(Document v1, Document v2) {
                    return v1.getDocName().compareTo(v2.getDocName());
                }
            });
            isReverse=true;
        }
        else
        {
            Collections.sort(mDocumentList, new Comparator<Document>() {
                public int compare(Document v1, Document v2) {
                    return v2.getDocName().compareTo(v1.getDocName());
                }
            });
            isReverse = false;
        }
        mDocumentListAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem search_item = menu.findItem(R.id.mi_search);
        SearchView searchView = (SearchView) search_item.getActionView();
        searchView.setFocusable(false);
        searchView.setQueryHint("Search");
        if(searchView.isEnabled())
            mDocumentListRecyclerView.setVisibility(View.INVISIBLE);
        else
            mDocumentListRecyclerView.setVisibility(View.VISIBLE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                //clear the previous data in search arraylist if exist
                search_result_arraylist.clear();
                keyword = s.toUpperCase();
                for(int i =0 ;i < mDocumentList.size();i++){
                    if(mDocumentList.get(i).getDocName().contains(keyword)){
                        search_result_arraylist.add(mDocumentList.get(i).getDocName().toString());
                    }

                }
                adapter = new ArrayAdapter<String>(DocumentListActivity.this,android.R.layout.simple_list_item_1,search_result_arraylist);
                mDocumentListRecyclerView.setVisibility(View.INVISIBLE);
                searchListView.setAdapter(adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //clear the previous data in search arraylist if exist
                search_result_arraylist.clear();
                keyword = s.toUpperCase();

                for(int i =0 ;i < mDocumentList.size();i++){
                    if(mDocumentList.get(i).getDocName().contains(keyword)){
                        search_result_arraylist.add(mDocumentList.get(i).getDocName().toString());
                    }
                }
                adapter = new ArrayAdapter<String>(DocumentListActivity.this,android.R.layout.simple_list_item_1,search_result_arraylist);
                searchListView.setVisibility(View.VISIBLE);
                mDocumentListRecyclerView.setVisibility(View.INVISIBLE);
                searchListView.setAdapter(adapter);
                return false;
            }
        });
        searchListView.setVisibility(View.INVISIBLE);
       mDocumentListRecyclerView.setVisibility(View.VISIBLE);
        return true;
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
