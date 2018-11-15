package taproom.model.service;

import java.util.List;

import taproom.model.InMemoryDataBase;
import taproom.model.Tap;

public class TapMonitor implements Runnable
{

	@Override
	public void run() {
		List<Tap> tapList=InMemoryDataBase.getTapsList();
		while(true)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		for(Tap tap:tapList)
		{
			if(tap.getTotalOuncesAvailable()==0)
			{
					
					tap.setActive(false);
			}
			else
			{
//				stops the for loop
			}
		}
	
		
		
	}
	
	}
	
	
	

}
