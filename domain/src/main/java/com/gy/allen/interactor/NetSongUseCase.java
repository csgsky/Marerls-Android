package com.gy.allen.interactor;

import com.gy.allen.executor.PostExecutionThread;
import com.gy.allen.executor.ThreadExecutor;
import com.gy.allen.model.repository.NetSongRepository;
import com.gy.allen.model.response.NetSongEntity;

import javax.inject.Inject;

import io.reactivex.Observable;

public class NetSongUseCase extends UseCase<NetSongEntity, NetSongUseCase.Params> {

    private NetSongRepository netSongRepository;

    @Inject
    NetSongUseCase(
             NetSongRepository netSongRepository,
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.netSongRepository = netSongRepository;
    }

    @Override
    Observable<NetSongEntity> buildUseCaseObservable(Params params) {
        return netSongRepository.getNetSongData(params.method, params.songId);
    }

    public static final class Params {
        private String method;
        private long songId;
        public Params(String method, long songId) {
            this.method = method;
            this.songId = songId;
        }
    }
}
