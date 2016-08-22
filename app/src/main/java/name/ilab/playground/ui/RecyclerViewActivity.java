package name.ilab.playground.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import name.ilab.playground.R;

public class RecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    Adapter adapter;

    List<Item> itemList = Arrays.asList(
            new Item("AAA", "aaa", () -> System.out.println("Action : AAA")),
            new Item("BBB", "bbb", () -> System.out.println("Action : BBB")),
            new Item("CCC", "ccc", () -> System.out.println("Action : CCC")),
            new Item("AAA", "aaa", () -> System.out.println("Action : AAA")),
            new Item("BBB", "bbb", () -> System.out.println("Action : BBB")),
            new Item("CCC", "ccc", () -> System.out.println("Action : CCC")),
            new Item("AAA", "aaa", () -> System.out.println("Action : AAA")),
            new Item("BBB", "bbb", () -> System.out.println("Action : BBB")),
            new Item("CCC", "ccc", () -> System.out.println("Action : CCC")),
            new Item("AAA", "aaa", () -> System.out.println("Action : AAA")),
            new Item("BBB", "bbb", () -> System.out.println("Action : BBB")),
            new Item("CCC", "ccc", () -> System.out.println("Action : CCC")),
            new Item("AAA", "aaa", () -> System.out.println("Action : AAA")),
            new Item("BBB", "bbb", () -> System.out.println("Action : BBB")),
            new Item("CCC", "ccc", () -> System.out.println("Action : CCC")),
            new Item("AAA", "aaa", () -> System.out.println("Action : AAA")),
            new Item("BBB", "bbb", () -> System.out.println("Action : BBB")),
            new Item("CCC", "ccc", () -> System.out.println("Action : CCC")),
            new Item("AAA", "aaa", () -> System.out.println("Action : AAA")),
            new Item("BBB", "bbb", () -> System.out.println("Action : BBB")),
            new Item("CCC", "ccc", () -> System.out.println("Action : CCC")),
            new Item("AAA", "aaa", () -> System.out.println("Action : AAA")),
            new Item("BBB", "bbb", () -> System.out.println("Action : BBB")),
            new Item("CCC", "ccc", () -> System.out.println("Action : CCC")),
            new Item("AAA", "aaa", () -> System.out.println("Action : AAA")),
            new Item("BBB", "bbb", () -> System.out.println("Action : BBB")),
            new Item("CCC", "ccc", () -> System.out.println("Action : CCC"))
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new Adapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.fab)
    void onClickFab(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView_name)
        TextView textViewName;

        @BindView(R.id.textView_description)
        TextView textViewDescription;

        @BindView(R.id.button_action)
        Button buttonAction;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    class Adapter extends RecyclerView.Adapter<ViewHolder> {
        @Override
        public int getItemCount() {
            return itemList.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(RecyclerViewActivity.this)
                    .inflate(R.layout.holder_recycler_view, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Item item = itemList.get(position);
            holder.textViewName.setText(item.name);
            holder.textViewDescription.setText(item.description);
            holder.buttonAction.setOnClickListener(view -> {
                if (item.action == null) {
                    return;
                }
                item.action.onExecute();
            });
        }
    }

    class Item {
        String name;
        String description;
        Action action;

        public Item(String name, String description, Action action) {
            this.name = name;
            this.description = description;
            this.action = action;
        }
    }

    interface Action {
        void onExecute();
    }
}
