package com.example.freshhub;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class InventoryFragment extends Fragment {

    public static final String TAG = "Inventory";
    protected RecyclerView rvFoods;
    protected List<Food> allFoods;
    FoodAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.fragment_inventory,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFoods = view.findViewById(R.id.rvFoods);
        allFoods = new ArrayList<>();
        //adapter = new FoodAdapter(getContext(),R.layout.item_food,allFoods);

        rvFoods.setAdapter(adapter);
        rvFoods.setLayoutManager(new LinearLayoutManager(getContext()));

        ParseQuery<Food> foodQuery = new ParseQuery<Food>(Food.class);

        foodQuery.findInBackground(new FindCallback<Food>() {
            @Override
            public void done(List<Food> objects, ParseException e) {
                if (e!=null){
                    Log.e(TAG,"Error with query",e);
                    return;
                }
                Log.i(TAG,"getting with query");
                allFoods.addAll(objects);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
