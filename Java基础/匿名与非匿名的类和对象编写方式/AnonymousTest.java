package com.atguigu.interfaceTest;
// 匿名与非匿名 类和对象的使用
public class AnonymousTest {
	public static void main(String[] args) {
		Computer com = new Computer();
		
		// 1. 创建非匿名类和非匿名对象
		Flash flash = new Flash();
		com.transferData(flash);
		// 2. 创建非匿名类和匿名对象
		com.transferData(new Printer());
		// 3. 创建接口的匿名类和非匿名对象
		USB phone = new USB() {
			@Override
			public void start() {
				System.out.println("手机开始工作");
			}

			@Override
			public void stop() {
				System.out.println("手机结束工作");
			}
			
		};
		com.transferData(phone);
		// 4. 创建接口的匿名类和匿名对象（类、对象都是匿名）
		com.transferData(new USB() {
			@Override
			public void start() {
				System.out.println("pm4开始工作");
			}
			@Override
			public void stop() {
				System.out.println("pm4结束工作");
			}
		});
	}
}

interface USB{
	// 开始工作
	public abstract void start();
	// 结束工作
	public abstract void stop();
}

// 电脑
class Computer{
	//数据传输-整理工作流
	public void transferData(USB usb) {
		usb.start();
		System.out.println("具体传输细节~");
		usb.start();
		System.out.println("***********************************");
	}
}

class Flash implements USB{

	@Override
	public void start() {
		System.out.println("flash 开始工作");
	}

	@Override
	public void stop() {
		System.out.println("flash 结束工作");
	}
}

class Printer implements USB{

	@Override
	public void start() {
		System.out.println("打印机开始工作");
	}

	@Override
	public void stop() {
		System.out.println("打印机结束工作");
	}
	
}