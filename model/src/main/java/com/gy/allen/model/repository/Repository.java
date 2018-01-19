package com.gy.allen.model.repository;

import com.gy.allen.model.response.ThreatersResp;

import io.reactivex.Observable;


/**
 * Created by allen on 18/1/19.
 */

public interface Repository {
    Observable<ThreatersResp> getThreatersList(String start);
}
