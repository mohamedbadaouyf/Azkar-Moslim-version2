package com.mohamed_badaouy.azkar_muslim1;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

public class JobServiceClass extends JobService {

	@Override
	public boolean onStartJob(JobParameters p1) {
		Toast.makeText(getApplicationContext(), "صلِ علي محمد", Toast.LENGTH_LONG).show();
		/*JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

		ComponentName componentName = new ComponentName(this, JobServiceClass.class);
		JobInfo jobInfo = new JobInfo.Builder(123, componentName)
			.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY) // تحديد نوع الشبكة المطلوبة
			.setPersisted(true) // لاستمرارية المهمة حتى بعد إعادة تشغيل الجهاز
			.build();

		//jobScheduler.schedule(jobInfo);*/
		Intent intent2 = new Intent(JobServiceClass.this, MyService.class);
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
			startForegroundService(intent2);
		}else{
			startService(intent2);
		}
		
		return true;
	}
 
	@Override
	public boolean onStopJob(JobParameters p1) {
		JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

		ComponentName componentName = new ComponentName(this, JobServiceClass.class);
		JobInfo jobInfo = new JobInfo.Builder(123, componentName)
			.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY) // تحديد نوع الشبكة المطلوبة
			.setPersisted(true) // لاستمرارية المهمة حتى بعد إعادة تشغيل الجهاز
			//.setRequiresCharging(true)
			//.setRequiresDeviceIdle(true)
			.build();

		jobScheduler.schedule(jobInfo);
		return true;
	}
	
    
    
    
}
