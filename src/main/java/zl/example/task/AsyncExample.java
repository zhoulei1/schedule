package zl.example.task;

import java.util.Calendar;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class AsyncExample {
	
	@Async
	public Future<String> async(){
		try {
			TimeUnit.SECONDS.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("excute async " + Calendar.getInstance().get(Calendar.SECOND));
		return new AsyncResult<>("async accomplished!");  
	}
}
