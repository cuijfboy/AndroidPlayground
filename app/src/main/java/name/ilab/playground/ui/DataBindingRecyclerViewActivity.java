package name.ilab.playground.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import name.ilab.playground.BR;
import name.ilab.playground.R;
import name.ilab.playground.databinding.ActivityDataBindingRecyclerViewBinding;
import rx.Observable;

public class DataBindingRecyclerViewActivity extends AppCompatActivity {

    List<Item> itemList = new ArrayList<>(Arrays.asList(
            new Item("AAA", "aaa"),
            new Item("BBB", "bbb"),
            new Item("CCC", "ccc"),
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
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingRecyclerViewBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_data_binding_recycler_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initFab(binding);
        initRecyclerView(binding);
    }

    private void initFab(ActivityDataBindingRecyclerViewBinding binding) {
        binding.fab.setOnClickListener((view) -> {
            Observable.from(itemList).forEach((item -> {
                item.name.set("XXX");
                item.description.set("xxx");
            }));
            itemList.add(new Item("ZZZ", "zzz"));
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
    }

    private void initRecyclerView(ActivityDataBindingRecyclerViewBinding binding) {
        Adapter adapter = new Adapter();
        RecyclerView recyclerView = binding.included.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    class Adapter extends RecyclerView.Adapter<DataBindingViewHolder> {
        @Override
        public int getItemCount() {
            return itemList.size();
        }

        @SuppressWarnings("unchecked")
        @Override
        public DataBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(DataBindingRecyclerViewActivity.this);
            ViewDataBinding binding = DataBindingUtil.inflate(inflater,
                    R.layout.holder_data_binding_recycler_view, parent, false);
            return new DataBindingViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(DataBindingViewHolder holder, int position) {
            Item item = itemList.get(position);
            holder.getBinding().setVariable(BR.item, item);
            holder.getBinding().executePendingBindings();
        }
    }

    public static class Item {
        public final ObservableField<String> name = new ObservableField<>();
        public final ObservableField<String> description = new ObservableField<>();
        private Action action;

        public Item(String name, String description) {
            this(name, description, null);
            this.action = () -> System.out.println(
                    "name = " + this.name.get() + ", description = " + this.description.get());
        }

        public Item(String name, String description, Action action) {
            this.name.set(name);
            this.description.set(description);
            this.action = action;
        }

        public void onClick(View view) {
            if (action == null) {
                return;
            }
            action.onExecute();
        }

        @Override
        public String toString() {
            return "Item{" +
                    "action=" + action +
                    ", name=" + name.get() +
                    ", description=" + description.get() +
                    '}';
        }

    }

    interface Action {
        void onExecute();
    }

}
