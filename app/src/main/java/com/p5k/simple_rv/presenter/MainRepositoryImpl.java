package com.p5k.simple_rv.presenter;

import com.p5k.simple_rv.common.Constant;
import com.p5k.simple_rv.model.Champ;
import com.p5k.simple_rv.model.ChampDetail;

import java.util.ArrayList;
import java.util.List;

public class MainRepositoryImpl implements MainRepository {
    @Override
    public List<ChampDetail> getAllChamp() {
        List<ChampDetail> champs = new ArrayList<>();
        for (Champ champ : Constant.getAllChamp()) {
            champs.add(new ChampDetail(champ, "", 1));
        }
        return champs;
    }
}
