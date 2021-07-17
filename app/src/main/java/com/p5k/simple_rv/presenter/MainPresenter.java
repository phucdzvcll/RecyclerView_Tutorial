package com.p5k.simple_rv.presenter;

import com.p5k.simple_rv.model.Champ;
import com.p5k.simple_rv.model.ChampDetail;

import java.util.List;

public class MainPresenter {

    private final MainRepository mainInterface;

    public MainPresenter(MainRepository mainInterface) {
        this.mainInterface = mainInterface;
    }

    public List<ChampDetail> getAllChamp(){
        return mainInterface.getAllChamp();
    }
}
