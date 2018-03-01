package zl.example.task;

import java.util.Calendar;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleExample {
	@Autowired
	AsyncExample example;
	
	@Scheduled(fixedRate=1000)
	public void fixedRate(){//自带修正功能
		System.out.println("excute fixedRate " + Calendar.getInstance().get(Calendar.SECOND));
	}
	
	@Scheduled(fixedRate=2000)
	public void initialDelay(){
		Future<String> async = example.async();
		System.out.println("async finished ? "+  async.isDone());
		System.out.println("excute fixedRate " + Calendar.getInstance().get(Calendar.SECOND));
	}
	
	@Scheduled(fixedDelay=1000)
	public void fixedDelay() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("excute fixedDelay " + Calendar.getInstance().get(Calendar.SECOND));
	}
	
	@Scheduled(cron="0 0 0 * 2 ?")
	public void cron() {
		
	}
}
