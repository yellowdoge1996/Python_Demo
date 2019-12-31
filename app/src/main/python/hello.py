from java import jclass
from bs4 import BeautifulSoup
import requests
import numpy as np

def greet(name):
    print ("--- hello,%s ---" % name)

def add(a,b):
    return a+b

def sub(count, a=0, b=0, c=0):
    return count - a - b - c

def get_list(a,b,c,d):
    return [a,b,c,d]

def print_list(data):
    print (type(data))
    for i in range(data.size()):
        print (data.get(i))

def get_java_bean():
    JavaBean = jclass("com.example.pythondemo.KotlinBean")
    jb = JavaBean("python")
    jb.setData("json")
    jb.setData("xml")
    jb.setData("xhtml")
    return jb

# 爬取网页并解析
def get_http():
    r = requests.get("https://www.baidu.com/")
    r.encoding ='utf-8'
    bsObj = BeautifulSoup(r.text,"html.parser")
    for node in bsObj.findAll("a"):
        print("---**--- ", node.text)

# 使用numpy
def print_numpy():
    y = np.zeros((5,), dtype = np.int)
    print(y)