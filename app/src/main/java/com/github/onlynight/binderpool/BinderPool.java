package com.github.onlynight.binderpool;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by lion on 2017/9/27.
 */

public class BinderPool {

    public static final int BINDER_ID_HTTP_MANAGER = 1;

    private IBinderPool mBinderPool;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBinderPool = IBinderPool.Stub.asInterface(iBinder);
            try {
                mBinderPool.asBinder().linkToDeath(mDeathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {

        }
    };

    private static BinderPool instance;

    public static BinderPool getInstance(Activity context) {
        if (instance == null) {
            instance = new BinderPool(context);
        }
        return instance;
    }

    public BinderPool(Activity context) {
        connectBinderService(context);
    }

    private void connectBinderService(Activity context) {
        Intent intent = new Intent(context, BinderPoolService.class);
        context.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public IBinder queryBinder(int binderId) {

        IBinder binder = null;

        if (mBinderPool != null) {
            try {
                binder = mBinderPool.queryBinder(binderId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        return binder;
    }

    public static class BinderPoolImpl extends IBinderPool.Stub {

        @Override
        public IBinder queryBinder(int binderId) throws RemoteException {

            IBinder binder = null;

            switch (binderId) {
                case BINDER_ID_HTTP_MANAGER: {
                    binder = new HttpManagerImpl();
                }
                break;
            }

            return binder;
        }

    }
}
