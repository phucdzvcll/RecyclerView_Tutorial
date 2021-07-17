package com.p5k.simple_rv.common;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import java.util.ArrayList;
import java.util.List;

public class EditTextWithRemovableTextWatchers extends AppCompatEditText {


    private final List<TextWatcher> listeners = new ArrayList<>();

    public EditTextWithRemovableTextWatchers(@NonNull Context context) {
        super(context);
    }

    public EditTextWithRemovableTextWatchers(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextWithRemovableTextWatchers(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addTextChangedListener(TextWatcher watcher) {
        listeners.add(watcher);
        super.addTextChangedListener(watcher);
    }

    @Override
    public void removeTextChangedListener(TextWatcher watcher) {
        listeners.remove(watcher);
        super.removeTextChangedListener(watcher);
    }

    public void clearTextChangedListeners() {
        listeners.forEach(super::removeTextChangedListener);
        listeners.clear();
    }

}
