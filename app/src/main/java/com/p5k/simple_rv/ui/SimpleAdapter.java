package com.p5k.simple_rv.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.p5k.simple_rv.R;
import com.p5k.simple_rv.common.EditTextWithRemovableTextWatchers;
import com.p5k.simple_rv.model.ChampDetail;

import java.util.ArrayList;
import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {
    final private ArrayList<ChampDetail> champs = new ArrayList<>();

    @Nullable
    private LevelUp levelUp;

    public void setLevelUp(@NonNull LevelUp levelUp) {
        this.levelUp = levelUp;
    }

    @Nullable
    private MoveItem moveItem;

    public void setMoveItem(@NonNull MoveItem moveItem) {
        this.moveItem = moveItem;
    }

    @Nullable
    private ItemClick itemClick;

    public void setEditNote(@NonNull EditNote editNote) {
        this.editNote = editNote;
    }

    @Nullable
    private EditNote editNote;

    public void setItemClick(@NonNull ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    @NonNull

    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleAdapter.SimpleViewHolder holder, int position) {
        //clean
        holder.editText.clearTextChangedListeners();
        //set
        ChampDetail champ = champs.get(position);
        String nameSuffix = "";
        if (champ.getLevel() > 1) {
            nameSuffix = " Level " + champ.getLevel();
        }
        final String name = champ.getChamp().getName() + nameSuffix;
        holder.name.setText(name);
        Glide.with(holder.itemView.getContext()).load(champ.getChamp().getImgUrl()).into(holder.img);
        holder.btnClose.setOnClickListener(v -> {
            if (itemClick != null) {
                itemClick.onRemoveItemClick(champ);
            }
        });
        holder.editText.setText(champ.getNote());
        //listener

        holder.btnDown.setOnClickListener(v -> {
            if (this.moveItem != null) {
                this.moveItem.onDown(champ);
            }
        });

        holder.btnUp.setOnClickListener(v -> {
            if (this.moveItem != null) {
                this.moveItem.onUp(champ);
            }
        });

        holder.btnLevelUp.setOnClickListener(v -> {
            if (this.levelUp != null) {
                this.levelUp.onLevelUp(champ);
            }
        });

        holder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editNote != null) {
                    editNote.onEditItemNote(champ, s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return champs.size();
    }

    public void setItems(List<ChampDetail> champList) {
        champs.clear();
        champs.addAll(champList);
        notifyDataSetChanged();
    }

    public void updateItem(ChampDetail champ, int position) {
        champs.set(position, champ);
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        final ImageView img;
        final TextView name;
        final ImageView btnClose;
        final View btnUp;
        final View btnDown;
        final View btnLevelUp;
        final EditTextWithRemovableTextWatchers editText;

        public SimpleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img = itemView.findViewById(R.id.img);
            this.name = itemView.findViewById(R.id.itemName);
            this.btnClose = itemView.findViewById(R.id.btnRemove);
            this.btnUp = itemView.findViewById(R.id.btnUp);
            this.btnDown = itemView.findViewById(R.id.btnDown);
            this.btnLevelUp = itemView.findViewById(R.id.btnLeverUp);
            this.editText = itemView.findViewById(R.id.editText);
        }
    }

    interface ItemClick {
        void onRemoveItemClick(ChampDetail champ);
    }

    interface EditNote {
        void onEditItemNote(ChampDetail champ, String note);
    }

    interface MoveItem {
        void onUp(ChampDetail champ);

        void onDown(ChampDetail champ);
    }

    interface LevelUp {
        void onLevelUp(ChampDetail champDetail);
    }
}
