package com.github.onlynight.binderpool;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private BinderPool mBinderPool;
    private IBinder mHttpManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinderPool = BinderPool.getInstance(this);

        findViewById(R.id.btnDoWork).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doWork();
            }
        });
    }

    private void doWork() {
        mHttpManager = mBinderPool.queryBinder(BinderPool.BINDER_ID_HTTP_MANAGER);
        try {
            HttpManagerImpl.asInterface(mHttpManager).post(12345);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
