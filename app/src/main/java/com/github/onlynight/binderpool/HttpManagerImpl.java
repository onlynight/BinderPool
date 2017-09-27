package com.github.onlynight.binderpool;

import android.os.RemoteException;

/**
 * Created by lion on 2017/9/27.
 */

public class HttpManagerImpl extends IHttpManager.Stub {

    @Override
    public void post(int actionId) throws RemoteException {
        System.out.println("actionId = " + actionId);
    }

}
