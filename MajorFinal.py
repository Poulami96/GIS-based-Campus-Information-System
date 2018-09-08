# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'crawler.ui'
#
# Created by: PyQt4 UI code generator 4.11.4
#
# WARNING! All changes made in this file will be lost!
import urllib
import urllib2
from bs4 import BeautifulSoup
#from BeautifulSoup import BeautifulSoup
import ssl
import os
import requests
import re
import httplib

from PyQt4 import QtCore, QtGui
from PyQt4.QtCore import QStringList
from PyQt4.QtCore import QString
try:
    _fromUtf8 = QtCore.QString.fromUtf8
except AttributeError:
    def _fromUtf8(s):
        return s

try:
    _encoding = QtGui.QApplication.UnicodeUTF8
    def _translate(context, text, disambig):
        return QtGui.QApplication.translate(context, text, disambig, _encoding)
except AttributeError:
    def _translate(context, text, disambig):
        return QtGui.QApplication.translate(context, text, disambig)

class Ui_MainWindow(object):
    global crawl
    words=[]
    link_arr2=[]
    outputfile_path=''
    no_of_pages=1
    depth_val=0
    def setupUi(self, MainWindow):
        MainWindow.setObjectName(_fromUtf8("MainWindow"))
        MainWindow.resize(730, 485)
        MainWindow.setStyleSheet(_fromUtf8("QWidget {background-image: url(K:/major 2/back.png);}\n"
"QDialog{\n"
"color:navy; background-color: white;\n"
"\n"
"                     \n"
"}\n"
"QRadioButton{\n"
"color:navy;\n"
"}\n"
"QLabel{\n"
"color: navy; \n"
"                        background:grey;\n"
"                        selection-color: yellow;\n"
"                        selection-background-color: blue;\n"
"}\n"
"QComboBox {\n"
"    border: 1px solid gray;\n"
"    border-radius: 3px;\n"
"    padding: 1px 18px 1px 3px;\n"
"    min-width: 6em;\n"
"}\n"
"QPushButton {\n"
"    border: 2px solid #8f8f91;\n"
"    border-radius: 6px;\n"
"    background-color: qlineargradient(x1: 0, y1: 0, x2: 0, y2: 1,\n"
"                                      stop: 0 #f6f7fa, stop: 1 #dadbde);\n"
"    min-width: 80px;\n"
"}\n"
"QPushButton:flat {\n"
"    border: none; /* no border for a flat push button */\n"
"}\n"
"\n"
"QPushButton:default {\n"
"    border-color: navy; /* make the default button prominent */\n"
"}\n"
"\n"
"QComboBox {\n"
"    border: 1px solid gray;\n"
"    border-radius: 3px;\n"
"    padding: 1px 18px 1px 3px;\n"
"    min-width: 6em;\n"
" border-color: navy; /* make the default button prominent */\n"
"}\n"
"\n"
"QComboBox:editable {\n"
"    background: white;\n"
"}\n"
"\n"
"QComboBox:!editable, QComboBox::drop-down:editable {\n"
"     background: qlineargradient(x1: 0, y1: 0, x2: 0, y2: 1,\n"
"                                 stop: 0 #E1E1E1, stop: 0.4 #DDDDDD,\n"
"                                 stop: 0.5 #D8D8D8, stop: 1.0 #D3D3D3);\n"
"}\n"
"\n"
"/* QComboBox gets the \"on\" state when the popup is open */\n"
"QComboBox:!editable:on, QComboBox::drop-down:editable:on {\n"
"    background: qlineargradient(x1: 0, y1: 0, x2: 0, y2: 1,\n"
"                                stop: 0 #D3D3D3, stop: 0.4 #D8D8D8,\n"
"                                stop: 0.5 #DDDDDD, stop: 1.0 #E1E1E1);\n"
"}\n"
"\n"
"QComboBox:on { /* shift the text when the popup opens */\n"
"    padding-top: 3px;\n"
"    padding-left: 4px;\n"
"}\n"
"\n"
"QComboBox::drop-down {\n"
"    subcontrol-origin: padding;\n"
"    subcontrol-position: top right;\n"
"    width: 15px;\n"
"\n"
"    border-left-width: 1px;\n"
"    border-left-color: darkgray;\n"
"    border-left-style: solid; /* just a single line */\n"
"    border-top-right-radius: 3px; /* same radius as the QComboBox */\n"
"    border-bottom-right-radius: 3px;\n"
"}\n"
"\n"
"QComboBox::down-arrow {\n"
"    image: url(/usr/share/icons/crystalsvg/16x16/actions/1downarrow.png);\n"
"}\n"
"\n"
"QComboBox::down-arrow:on { /* shift the arrow when popup is open */\n"
"    top: 1px;\n"
"    left: 1px;\n"
"}\n"
""))
        self.centralwidget = QtGui.QWidget(MainWindow)
        self.centralwidget.setObjectName(_fromUtf8("centralwidget"))
        self.label = QtGui.QLabel(self.centralwidget)
        self.label.setGeometry(QtCore.QRect(140, 80, 81, 20))
        self.label.setAutoFillBackground(True)
        self.label.setStyleSheet(_fromUtf8("background:solid\n"
""))
        self.label.setObjectName(_fromUtf8("label"))
        self.lineEdit = QtGui.QLineEdit(self.centralwidget)
        self.lineEdit.setGeometry(QtCore.QRect(260, 80, 261, 20))
        self.lineEdit.setStyleSheet(_fromUtf8("background:solid\n"
""))
        self.lineEdit.setObjectName(_fromUtf8("lineEdit"))
        self.label_2 = QtGui.QLabel(self.centralwidget)
        self.label_2.setGeometry(QtCore.QRect(260, 0, 241, 31))
        self.label_2.setAutoFillBackground(True)
        self.label_2.setStyleSheet(_fromUtf8("background:solid"))
        self.label_2.setObjectName(_fromUtf8("label_2"))
        self.search_btn = QtGui.QPushButton(self.centralwidget)
        self.search_btn.setGeometry(QtCore.QRect(310, 200, 171, 31))
        font = QtGui.QFont()
        font.setBold(True)
        font.setWeight(75)
        self.search_btn.setFont(font)
        self.search_btn.setAutoFillBackground(False)
        self.search_btn.setStyleSheet(_fromUtf8("background:solid\n"
""))
        self.search_btn.setAutoExclusive(False)
        self.search_btn.setAutoDefault(False)
        self.search_btn.setDefault(True)
        self.search_btn.setFlat(False)
        self.search_btn.setObjectName(_fromUtf8("search_btn"))
        self.spinBox = QtGui.QSpinBox(self.centralwidget)
        self.spinBox.setGeometry(QtCore.QRect(280, 120, 41, 21))
        self.spinBox.setMinimum(1)
        self.spinBox.setObjectName(_fromUtf8("spinBox"))
        self.label2 = QtGui.QLabel(self.centralwidget)
        self.label2.setGeometry(QtCore.QRect(140, 120, 121, 21))
        self.label2.setAutoFillBackground(True)
        self.label2.setStyleSheet(_fromUtf8("background:solid\n"
""))
        self.label2.setObjectName(_fromUtf8("label2"))
        self.label_3 = QtGui.QLabel(self.centralwidget)
        self.label_3.setGeometry(QtCore.QRect(140, 160, 91, 16))
        self.label_3.setAutoFillBackground(True)
        self.label_3.setStyleSheet(_fromUtf8("background:solid\n"
""))
        self.label_3.setObjectName(_fromUtf8("label_3"))
        self.lineEdit2 = QtGui.QLineEdit(self.centralwidget)
        self.lineEdit2.setGeometry(QtCore.QRect(260, 160, 261, 20))
        self.lineEdit2.setAutoFillBackground(False)
        self.lineEdit2.setStyleSheet(_fromUtf8("background:solid;\n"
""))
        self.lineEdit2.setObjectName(_fromUtf8("lineEdit2"))
        self.textarea = QtGui.QTextBrowser(self.centralwidget)
        self.textarea.setGeometry(QtCore.QRect(10, 240, 711, 201))
        self.textarea.setAutoFillBackground(True)
        self.textarea.setStyleSheet(_fromUtf8("background:solid\n"
""))
        self.textarea.setObjectName(_fromUtf8("textarea"))
        self.browse_btn = QtGui.QPushButton(self.centralwidget)
        self.browse_btn.setGeometry(QtCore.QRect(560, 160, 84, 21))
        font = QtGui.QFont()
        font.setBold(True)
        font.setWeight(75)
        self.browse_btn.setFont(font)
        self.browse_btn.setAutoFillBackground(False)
        self.browse_btn.setStyleSheet(_fromUtf8("background:solid\n"
""))
        self.browse_btn.setObjectName(_fromUtf8("browse_btn"))
        self.depthspin = QtGui.QSpinBox(self.centralwidget)
        self.depthspin.setGeometry(QtCore.QRect(550, 120, 41, 21))
        self.depthspin.setMinimum(0)
        self.depthspin.setObjectName(_fromUtf8("depthspin"))
        self.label3 = QtGui.QLabel(self.centralwidget)
        self.label3.setGeometry(QtCore.QRect(430, 120, 91, 21))
        self.label3.setAutoFillBackground(True)
        self.label3.setStyleSheet(_fromUtf8("background:solid\n"
""))
        self.label3.setObjectName(_fromUtf8("label3"))
        MainWindow.setCentralWidget(self.centralwidget)
        self.menubar = QtGui.QMenuBar(MainWindow)
        self.menubar.setGeometry(QtCore.QRect(0, 0, 730, 21))
        self.menubar.setObjectName(_fromUtf8("menubar"))
        MainWindow.setMenuBar(self.menubar)
        self.statusbar = QtGui.QStatusBar(MainWindow)
        self.statusbar.setObjectName(_fromUtf8("statusbar"))
        MainWindow.setStatusBar(self.statusbar)

        self.retranslateUi(MainWindow)
        QtCore.QObject.connect(self.menubar, QtCore.SIGNAL(_fromUtf8("triggered(QAction*)")), MainWindow.close)
        QtCore.QObject.connect(self.menubar, QtCore.SIGNAL(_fromUtf8("triggered(QAction*)")), MainWindow.close)
        QtCore.QObject.connect(self.browse_btn,QtCore.SIGNAL("clicked()"),self.output_browse)
        QtCore.QObject.connect(self.search_btn,QtCore.SIGNAL("clicked()"),self.func_keywords)
        self.spinBox.valueChanged[int].connect(self.input_pages)
        self.depthspin.valueChanged[int].connect(self.depth_spin)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        MainWindow.setWindowTitle(_translate("MainWindow", "MainWindow", None))
        self.label.setText(_translate("MainWindow", "Keywords", None))
        self.label_2.setText(_translate("MainWindow", "<html><head/><body><p align=\"center\"><span style=\" font-size:14pt; font-weight:600; text-decoration: underline;\">Crawler &amp; Scraper</span></p></body></html>", None))
        self.search_btn.setText(_translate("MainWindow", "Search", None))
        self.label2.setText(_translate("MainWindow", "Number of Pages:", None))
        self.label_3.setText(_translate("MainWindow", "File Location", None))
        self.browse_btn.setText(_translate("MainWindow", "Browse", None))
        self.label3.setText(_translate("MainWindow", "Crawler Depth:", None))

    def input_pages(self):
        self.no_of_pages = self.spinBox.value()

    def depth_spin(self):
        self.depth_val = self.depthspin.value()

    def output_browse(self):
        print ('selecting folder containing output text file')
        #self.fname = QtGui.QFileDialog.getSaveFileName(None, 'Save file','/home',filter = '*.txt')
        self.fname = QtGui.QFileDialog.getExistingDirectory(None, "Select Directory for saving output files")
        global outputfile_path
        outputfile_path = self.fname.replace('\\','/')
        self.lineEdit2.setText(self.fname)
        print(outputfile_path)

    def crawl(url, prevLevel=0):
        if prevLevel > 1:
            return None
        try:
            page = urllib2.urlopen(url)
        except (urllib2.URLError, ValueError):
            return None

        try:
            soup = BeautifulSoup(page,"lxml")
        except UnicodeEncodeError:
            return None
        root = {}
        root["url"] = url
        root["children"] = []

        anchors = soup.findAll('a')
        for a in anchors:
            global link_arr2
            link = a.get('href')
            if link is not None:
                child = crawl(a['href'], prevLevel + 1)
                if child is not None:
                    link2=child["url"]
                    #for i in words:
                    if 'network' in link2:
                        link_arr2.append(link2)
                        print child["url"]
                        root["children"].append(child)
                            
        root["content"] = soup.renderContents()
        return root

    def func_keywords(self):
        keys=(self.lineEdit.text())
        print(keys)
        self.textarea.append(keys)
        global no_of_pages
        no_pages=(self.no_of_pages)*10
        print(no_pages)
        global depth_val
        dep_val=self.depth_val
        print (dep_val)
        results = no_pages # valid options 10, 20, 30, 40, 50, and 100
        global words
        words=keys.split('')
        #for k in range(len(words)):
         #   self.textarea.append(words[k])
        page = requests.get("https://www.google.com/search?q={}&num={}".format(keys, results))
        soup = BeautifulSoup(page.content,"lxml")
        links = soup.findAll("a")
        global link_arr2
        link_arr=[]
        for link in links :
            link_href = link.get('href')
            if "url?q=" in link_href and not "webcache" in link_href:
                link_arr.append(link.get('href').split("?q=")[1].split("&sa=U")[0])
                #print link.get('href').split("?q=")[1].split("&sa=U")[0]
        link_arr2=link_arr        
        i=0
        for link in range (len(link_arr)) :
            print(link_arr[i])
            self.textarea.append(link_arr[i])
            i=i+1

        #startingURL = "http://ieee.rutgers.edu"
        i=0
        #label:crawling
        try:
            for i in range(len(link_arr)-15):
                try:
                    r=requests.get(link_arr[i])
                    if r.status_code==200:
                        startingURL =link_arr[i]
                        print('Next URL')
                        print(startingURL)
                        self.textarea.append('Next URL')
                        self.textarea.append(startingURL)
                        crawl(startingURL,dep_val)
                    else:
                        return None
                        
                except httplib.BadStatusLine:
                    pass
        finally:
            global outputfile_path
            output = str(outputfile_path)
            name_of_file = 'Wep Pages File'
            completeName = os.path.join(output, name_of_file+".doc")             
            i=0
            print('All the links')
            self.textarea.append('All the links')
            link_arr2=list(set(link_arr2))
            for i in range (len(link_arr2)) :
                self.textarea.append(link_arr2[i])
            #f_webpage = open('webpage3.doc')
            j=0
            for j in range (len(link_arr2)-18) :
                url=link_arr2[j]
                context = ssl._create_unverified_context()
                html = urllib.urlopen(url,context=context).read()
                soup = BeautifulSoup(html,'html.parser')
                self.textarea.append(link_arr2[j])

                #kill all script and style elements
                for script in soup(["script", "style"]):
                    script.decompose()    # rip it out

                # get text
                text = soup.getText()

                # break into lines and remove leading and trailing space on each
                lines = (line.strip() for line in text.splitlines())
                # break multi-headlines into a line each
                chunks = (phrase.strip() for line in lines for phrase in line.split("  "))
                # drop blank lines
                text = '\n'.join(chunk for chunk in chunks if chunk).encode('utf-8')
                f_webpage = open('webpage.txt','a')
                f_webpage.write(text)
                f_webpage = open('webpage.txt')
                f_web = open(completeName, 'a')
                for line in f_webpage:
                    # in python 2
                    # print line
                    # in python 3
                    
                    if "network" in line and len(line)>30:
                        print line
                        self.textarea.append(line)
                        f_web.write(line+'\n')
                    #else:
                    #continue
                print('finish')
                self.textarea.append('finish')



if __name__ == "__main__":
    import sys
    app = QtGui.QApplication(sys.argv)
    MainWindow = QtGui.QMainWindow()
    ui = Ui_MainWindow()
    ui.setupUi(MainWindow)
    MainWindow.show()
    sys.exit(app.exec_())