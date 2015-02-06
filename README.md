# 输入法分析教程

欢迎来到输入法的世界，让我们开始探索吧!

因为分析谷歌原生的 atinIME 输入法，已经无从下手，最好的办法还是开始自己写一个输入法，带着问题去看代码.

谷歌输入法很灵活，它的布局是一个view绘制出来的，不是button,通过解析XML的，

所以我的下一步就是开始解析XML绘制键盘布局，然后就是显示字符了，等等操作，代码我已经注释的很详细了，慢慢探索吧!!!... ...

## 目录

> androidtest - 一个测试DEMO，主要是一些编辑框，测试inputtype属性.

> IMEasy - 主程序

>>res

>>src

>>>com.android.ime

>>>>InputMethodEasyService.java

>>>interfaces

>>>>KeyboardActionListener.java

>>>keyboard

>>>>InputView.java

>>>>KeyBoard.java

>>>>KeyBoardID.java

>>>>KeyBoardParse.java

>>>>KeyboardSwitcher.java 

>>>>KeyBoardUtils.java

>>>>MainKeyBoardView.java   # 键盘主要绘制view.

## 注意

具体输入法的分析，请详细查看代码中的注释，因为本输入法是分析谷歌原生输入法一点点写成的.
