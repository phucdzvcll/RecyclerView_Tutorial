package com.p5k.simple_rv.presenter;

import com.p5k.simple_rv.model.Champ;
import com.p5k.simple_rv.model.ChampDetail;

import java.util.List;

public interface MainRepository {
    List<ChampDetail> getAllChamp();
}
