package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class test {	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ThisIsMethod t1=new ThisIsMethod();
		Thread t2=new Thread(t1,"w");
		t2.start();

	}
}

class ThisIsMethod implements Runnable{


	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<5;i++)
		{
			System.out.println(i);
		}
	}
	
}