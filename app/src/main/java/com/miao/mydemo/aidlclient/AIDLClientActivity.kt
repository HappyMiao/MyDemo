package  com.miao.mydemo.aidlclient

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.miao.aidl.IMyAidlInterface
import com.miao.aidl.bean.Dog

import com.miao.mydemo.R

class AIDLClientActivity : AppCompatActivity(),View.OnClickListener,ServiceConnection{

    var tv: TextView? = null
    var myAidlInterface: IMyAidlInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aild_client_demo)
        val btn = findViewById<Button>(R.id.button1)
        val btn2 = findViewById<Button>(R.id.button2)
        btn.setOnClickListener(this)
        btn2.setOnClickListener(this)
        tv = findViewById(R.id.tv_info)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.button1){
            val intent: Intent? = Intent()
            intent?.action = "com.miao.aidl.AIDLService"
            intent?.setPackage("com.miao.aidl")
            bindService(intent,this, Context.BIND_AUTO_CREATE)
        }
        if (v?.id == R.id.button2){
            val dogs: List<Dog> = myAidlInterface!!.getDogs(Dog("小黄","黑色",1))
            tv?.text = dogs.toString()
        }
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        tv?.text = "连接失败/断开连接"
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        myAidlInterface = IMyAidlInterface.Stub.asInterface(service)
        tv?.text = "服务连接成功"
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(this)
    }
}
