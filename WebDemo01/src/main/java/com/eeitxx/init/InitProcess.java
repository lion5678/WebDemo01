package com.eeitxx.init;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.eeitxx.base.BaseServer;

public class InitProcess{
	private static final int THREAD_COUNT = 1;
    private static final Executor exec = Executors.newFixedThreadPool(THREAD_COUNT);
    private int port;
    
    
    public void init(){
//    	exec.execute(new BaseServer(9000));
    	new BaseServer(9000);
    }
}
