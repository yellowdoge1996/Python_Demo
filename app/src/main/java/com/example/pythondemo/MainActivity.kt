package com.example.pythondemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.chaquo.python.Kwarg
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPython()
        callPythonCode()
    }

    fun initPython(){
        if (!Python.isStarted()){
            Python.start(AndroidPlatform(this))
        }
    }

    fun callPythonCode(){
        val python = Python.getInstance()
        // 调用hello.py模块中的greet函数，并传一个参数
        // 等价用法：python.getModule("hello").get("greet")?.call("Android")
        python.getModule("hello").callAttr("greet", "Android")
        // 调用python内建函数help()，输出了帮助信息
        python.builtins.get("help")?.call()
        val obj1 = python.getModule("hello").callAttr("add", 2, 3)
        // 将Python返回值换为Kotlin中的Integer类型
        val sum = obj1.toJava(Integer.TYPE)
        Log.d(this.localClassName, "add = $sum")

        // 调用python函数，命名式传参，等同 sub(10,b=1,c=3)
        val obj2 = python.getModule("hello").callAttr("sub", 10, Kwarg("b", 1), Kwarg("c", 3))
        val result = obj2.toJava(Integer.TYPE)
        Log.d(this.localClassName, "sub = $result")

        // 调用Python函数，将返回的Python中的list转为Java的list
        val obj3 = python.getModule("hello").callAttr("get_list", 10, "xx", 5.6, 'c')
        val pyList = obj3.asList()
        Log.d(this.localClassName, "get_list = $pyList")

        // 将Java的ArrayList对象传入Python中使用
        val params = ArrayList<PyObject>()
        params.add(PyObject.fromJava("alex"))
        params.add(PyObject.fromJava("bruce"))
        python.getModule("hello").callAttr("print_list", params)

        // Python中调用Java类
        val obj4 = python.getModule("hello").callAttr("get_java_bean")
        val data = obj4.toJava(KotlinBean::class.java)
        data.print()

        //调用python函数
        python.getModule("hello").callAttr("get_http")
        python.getModule("hello").callAttr("print_numpy")
    }
}
