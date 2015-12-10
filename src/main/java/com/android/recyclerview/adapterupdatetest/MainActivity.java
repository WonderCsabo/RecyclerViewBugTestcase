package com.android.recyclerview.adapterupdatetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ExampleAdapter exampleAdapter = new ExampleAdapter();
        recyclerView.setAdapter(exampleAdapter);

        exampleAdapter.items.add("first");
        exampleAdapter.notifyItemInserted(0);

        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ExampleAdapter exampleAdapter2 = new ExampleAdapter();
                recyclerView.setAdapter(exampleAdapter2);

                exampleAdapter2.items.add("new");
                exampleAdapter2.notifyItemInserted(0);
            }
        }, 4000);
    }

    private static class ExampleViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }

    private static class ExampleAdapter extends RecyclerView.Adapter<ExampleViewHolder> {

        private List<String> items = new ArrayList<>();

        private ExampleAdapter() {
            setHasStableIds(true);
        }

        @Override
        public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(parent.getContext());

            return new ExampleViewHolder(textView);
        }

        @Override
        public void onBindViewHolder(ExampleViewHolder holder, int position) {
            holder.textView.setText(items.get(position));
        }

        @Override
        public final int getItemCount() {
            return items.size();
        }

        @Override
        public long getItemId(int position) {
            return items.get(position).hashCode();
        }
    }

}
