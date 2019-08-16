// IMyAidlInterface.aidl
package com.miao.aidl;

import com.miao.aidl.bean.Dog;

interface IMyAidlInterface {
    List<Dog> getDogs(in Dog dog);
}
