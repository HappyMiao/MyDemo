package com.miao.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.miao.aidl.bean.Dog;

import java.util.ArrayList;
import java.util.List;

public class AIDLService extends Service {

    private List<Dog> dogs;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private Binder binder = new IMyAidlInterface.Stub() {
        @Override
        public List<Dog> getDogs(Dog dog) throws RemoteException {
            if(dogs != null){
                dogs.add(dog);
            }else {
                dogs = new ArrayList<>();
                dogs.add(dog);
            }
            return dogs;
        }
    };
}
