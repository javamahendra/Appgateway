package com.app.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppExecutorService {

	private ExecutorService executorService;

	public AppExecutorService() {
		executorService = Executors.newFixedThreadPool(25);
	}

	public void execute(Runnable job) {
		executorService.execute(job);
	}
}
