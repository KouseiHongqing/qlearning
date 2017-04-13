package Kousei.aist;

import java.util.Random;
@SuppressWarnings("all")
public class Train {
     private StateAndAction st = new StateAndAction();
     private Random RandomNumber = new Random(System.currentTimeMillis());
     public void TrainMethod(long time){
    	 if(st == null){
    		 return;
    	 }
    	 for(long i = 0; i < time;i++){
    		 this.qLearningTrain(time);
    	 }
    	 for(int i = 0;i < st.stateLength;i++){
    		 for(int j = 0;j < st.actionLength;j++){
    			 System.out.print(st.qValues[i][j] +"    ");
    		 } 
    		 System.out.println();
    	 }
     }
     
     
    
     int getNextActionE_Greedy(int state)
     {
     	int selectAction = -1;
     	boolean random= false;
     	double maxQ = -50000;
     	int[] doubleValues = new int[st.actionLength];
     	for(int i = 0;i < st.actionLength;i++)
     	{
     		doubleValues[i] = 0;
     	}
     	int maxDV = 0;
     	double selectPossbility = RandomNumber.nextDouble();
     	
     	if(selectPossbility < st.epsilon)
     	{
     		selectAction = -1;
     		random = true;
     	}
     	else
     	{
     		for(int action = 0;action < st.actionLength;action ++)
     		{
     
     			if(st.qValues[state][action]> maxQ)
     			{
     				selectAction = action;
     				maxQ = st.qValues[state][action];
     				maxDV = 0;
     				doubleValues[maxDV] = selectAction;
     			}
     			else if(st.qValues[state][action] == maxQ)
     			{
     				maxDV++;
     			    doubleValues[maxDV] = action; 
     			}
     		}
     		if( maxDV > 0 ) 
     		{
     			
     			int randomIndex =RandomNumber.nextInt(maxDV+1);
     			selectAction = doubleValues[randomIndex];
     		}
     	}
     	if(selectAction == -1)
     	{
     		selectAction = RandomNumber.nextInt(st.actionLength);
     	}
     	
     	while(!validAction(state,selectAction))
     	{
     		selectAction = RandomNumber.nextInt(st.actionLength);;
     	}
        return selectAction;
     }

    
     boolean validAction(int state,int action)
     {
    	boolean ret = true;
     	if(action < 0 || action > 5)
     		ret = false;
     	if(st.reward[state][action] < 0)
     		ret = false;
     	return ret;
     }
     
     void qLearningTrain(long time)
     {
    	 int count = 0;
    	 int initState = RandomNumber.nextInt(st.actionLength);;
     	 do
     	 {
     		  double this_Q;
     	      double max_Q;
     	      double new_Q;
     	      double reward = 0;
              int nextAction = this.getNextActionE_SoftMax(initState);
     	      reward = st.getReward(initState, nextAction);
     	      this_Q = st.getqValues(initState, nextAction);
     	      max_Q = st.getMaxQValues(nextAction);
     	      new_Q = reward + (st.alpha) * (max_Q);
     	      st.setqValues(initState, nextAction, new_Q);
     	      initState = nextAction;
     	      
     	 }while(initState != 5);
     }
     
     int getNextActionE_SoftMax(int state)
     {
    	 //https://en.wikipedia.org/wiki/Softmax_function
         //この部分はまだ理解できないので、ここは代用
     	int selectAction = 1;
    
     	
     	return selectAction;
     }
}
