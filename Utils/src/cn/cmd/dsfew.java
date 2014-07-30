package cn.cmd;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class dsfew {
	public static void pressKey(Robot robot, int keyvalue) {
		robot.keyPress(keyvalue);
		robot.keyRelease(keyvalue);
	}

	public static void pressKeyWithShift(Robot robot, int keyvalue) {
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(keyvalue);
		robot.keyRelease(keyvalue);
		robot.keyRelease(KeyEvent.VK_SHIFT);
	}

	public static void main(String[] args) {
		try {
			Robot robot = new Robot();
			Runtime.getRuntime().exec("notepad");
			// For linux.
			// Runtime.getRuntime().exec("gedit");
			// 定义5秒的延迟以便你打开notepad 哈哈
			// Robot 开始写
			robot.delay(3000);
			for (int i = 0; i < 100; i++) {
				pressKeyWithShift(robot, KeyEvent.VK_H);
				pressKey(robot, KeyEvent.VK_I);
				pressKey(robot, KeyEvent.VK_SPACE);
				// pressKeyWithShift(robot, KeyEvent.VK_H);
				pressKeyWithShift(robot, KeyEvent.VK_I);
				pressKey(robot, KeyEvent.VK_SPACE);
				pressKey(robot, KeyEvent.VK_A);
				pressKey(robot, KeyEvent.VK_M);

			}
			// closeApplication(robot);
			// robot.keyPress(KeyEvent.VK_SPACE);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
