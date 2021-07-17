package com.p5k.simple_rv.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.p5k.simple_rv.R;
import com.p5k.simple_rv.model.ChampDetail;
import com.p5k.simple_rv.presenter.MainPresenter;
import com.p5k.simple_rv.presenter.MainRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ChampDetail> champs = new ArrayList<>();
    RecyclerView recyclerView;
    SimpleAdapter adapter;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupData();
        setupRecyclerView();
        adapter.setItems(champs);
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.simpleRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SimpleAdapter();
        SimpleAdapter.ItemClick itemClick = champ -> {
            champs.remove(champ);
            Log.d("Phucccccc", "remove" + champ.getChamp().getName());
            adapter.setItems(champs);
        };
        SimpleAdapter.EditNote editNote = (champ, note) -> {
            int index = findIndexById(champ);
            ChampDetail newChamp = new ChampDetail(champ.getChamp(), note, champ.getLevel());
            champs.set(index, newChamp);
            adapter.updateItem(newChamp, index);
        };
        SimpleAdapter.MoveItem moveItem = new SimpleAdapter.MoveItem() {

            @Override
            public void onUp(ChampDetail champ) {
                int index = findIndexById(champ);
                if (index > 0) {
                    ChampDetail champs1 = champs.get(index - 1);
                    champs.set(index - 1, champ);
                    champs.set(index, champs1);
                    adapter.setItems(champs);
                }
            }

            @Override
            public void onDown(ChampDetail champ) {
                int index = findIndexById(champ);
                int maxIndex = champs.size() - 1;
                if (index < maxIndex) {
                    ChampDetail champs1 = champs.get(index + 1);
                    champs.set(index + 1, champ);
                    champs.set(index, champs1);
                    adapter.setItems(champs);
                }
            }
        };
        SimpleAdapter.LevelUp levelUp = (ChampDetail champDetail) -> {
            int index = findIndexById(champDetail);
            ChampDetail newChampDetail = new ChampDetail(champDetail.getChamp(), champDetail.getNote(), champDetail.getLevel() + 1);
            champs.set(index, newChampDetail);
            adapter.setItems(champs);
        };
        adapter.setEditNote(editNote);
        adapter.setItemClick(itemClick);
        adapter.setMoveItem(moveItem);
        adapter.setLevelUp(levelUp);
        recyclerView.setAdapter(adapter);
    }

    private int findIndexById(ChampDetail champ) {
        int index = -1;
        for (int i = 0; i < champs.size(); i++) {
            if (champs.get(i).getChamp().getId() == champ.getChamp().getId()) {
                index = i;
                break;
            }
        }
        return index;
    }

    void setupData() {
        mainPresenter = new MainPresenter(new MainRepositoryImpl());
        champs.addAll(mainPresenter.getAllChamp());
    }
}