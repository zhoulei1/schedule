package zl.example.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
@ComponentScan(basePackages="zl.example.task")
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
    	ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);  
         executor.setMaximumPoolSize(100);
         executor.setKeepAliveTime(300, TimeUnit.SECONDS);
         executor.setRejectedExecutionHandler(new ScheduledThreadPoolExecutor.CallerRunsPolicy());  
         return executor;  
         
         //可以满足，至少是个10个任务在运行，30个任务在等待（多余的任务会创建新的线程），空闲线程300s不活动被销毁
   
         // rejection-policy：当pool已经达到max size的时候，如何处理新任务  
         // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行  
      /*   Reject策略预定义有四种： 
         (1)ThreadPoolExecutor.AbortPolicy策略，是默认的策略,处理程序遭到拒绝将抛出运行时 RejectedExecutionException。 
         (2)ThreadPoolExecutor.CallerRunsPolicy策略 ,调用者的线程会执行该任务,如果执行器已关闭,则丢弃. 
         (3)ThreadPoolExecutor.DiscardPolicy策略，不能执行的任务将被丢弃. 
         (4)ThreadPoolExecutor.DiscardOldestPolicy策略，如果执行程序尚未关闭，则位于工作队列头部的任务将被删除，然后重试执行程序（如果再次失败，则重复此过程）.*/
    }

}
