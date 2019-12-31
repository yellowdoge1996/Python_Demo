from android.os import Bundle
from androidx.appcompat.app import AppCompatActivity
from com.chaquo.python.hello import R
from java import jvoid, Override, static_proxy, jint, method

class MainActivityEx(static_proxy(AppCompatActivity)):
    @Override(jvoid, [Bundle])
    def onCreate(self, state):
        AppCompatActivity.onCreate(self, state)
        self.setContentView(R.layout.activity_main)

    @method(jint, [jint])
    def func(self, num):
        return num + 1