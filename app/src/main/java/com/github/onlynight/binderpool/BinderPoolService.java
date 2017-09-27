package com.github.onlynight.binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.github.onlynight.binderpool.BinderPool.BinderPoolImpl;

public class BinderPoolService extends Service {

    private BinderPoolImpl mBindPool;

    public BinderPoolService() {
        mBindPool = new BinderPoolImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBindPool;
    }

}
