package zautomate.zadoqa.walkthrough;

import java.util.ArrayList;
import java.util.Arrays;

public class Probability 
{

	/*static void sum_up_recursive(ArrayList<Integer> numbers, int target, ArrayList<Integer> partial) {
	       int s = 0;
	       for (int x: partial) s += x;
	       if (s == target)
	       {
	            System.out.println("sum("+Arrays.toString(partial.toArray())+")="+target);
	       }
	       if (s >= target)
	       {
	            return;
	       }
	       for(int i=0;i<numbers.size();i++) 
	       {
	             ArrayList<Integer> remaining = new ArrayList<Integer>();

	             int n = numbers.get(i);

	             for (int j=i+1; j<numbers.size();j++) remaining.add(numbers.get(j));

	             ArrayList<Integer> partial_rec = new ArrayList<Integer>(partial);

	             partial_rec.add(n);

	             sum_up_recursive(remaining,target,partial_rec);
	       }
	    }
	    static void sum_up(ArrayList<Integer> numbers, int target)
	    {
	        sum_up_recursive(numbers,target,new ArrayList<Integer>());
	    }
	    public static void main(String args[]) {
	        Integer[] numbers = {3,9,8,4,5,7,10};
	        int target = 15;
	        sum_up(new ArrayList<Integer>(Arrays.asList(numbers)),target);
	    }
	 */
	public static void main(String[] args)
	{

		int first = 15;
		int last = 10;
		String tot = "";
		String tot1 = "";
		String tot2 = "";
		

		//for(int i=0;i<=first;i++)
		//{
			//if(i==0)
		//	{
				for(int j=0;j<=first;j++)
				{
					tot1 = tot1 + "*";
				}
					
				System.out.println(tot1);
			//}
			
			for(int j=0;j<=first;j++)
			{
				if(j==0)
				{
					tot = tot + "*";
				}
				
				tot = tot + " ";	
				
				tot = tot + "*";
				
				if(j==first)
				{
					tot = tot + "*";
				}
				
			}
			
			for(int j=0;j<=first;j++)
			{
				System.out.println(tot);
			}
			
			
		//	if(i==10)
			//{
				for(int j=0;j<=first;j++)
				{
					tot2 = tot2 + "*";
				}
					
				System.out.println(tot2);
			//}
			
		//}
		
		
		
		
		
		
		
		
		
		
		
		
		/*Integer[] numbers = {1,2,3,4,5};
	        int target = 6;
	       // sum_up(new ArrayList<Integer>(Arrays.asList(numbers)),target);

	        ArrayList<Integer> numbers1 = new ArrayList<Integer>(Arrays.asList(numbers));
	        ArrayList<Integer> partial1 = new ArrayList<Integer>();


	        int s = 0;
		       for (int x: partial1) s += x;
		       if (s == target)
		       {
		            System.out.println("sum("+Arrays.toString(partial1.toArray())+")="+target);
		       }
		       if (s >= target)
		       {
		            return;
		       }
		       for(int i=0;i<numbers1.size();i++) 
		       {
		             ArrayList<Integer> remaining = new ArrayList<Integer>();

		             int n = numbers1.get(i);

		             for (int j=i+1; j<numbers1.size();j++) remaining.add(numbers1.get(j));

		             ArrayList<Integer> partial_rec = new ArrayList<Integer>(partial1);

		             partial_rec.add(n);

		             //sum_up_recursive(remaining,target,partial_rec);
		       }

		 */


		/*
		ArrayList<Integer> al = new ArrayList<Integer>();

		int[] a = {1,2,3,4,5,6,7,8,9,10};

		int first = 0;
		int second = 0;
		int tot = 0;
		int input = 8;

		for(int i=0;i<=a.length-1;i++)
		{
			for(int j=0;i<=a.length-1;i++)
			{
				int val1 = a[i];
				first = val1;
				second = a[j];
				int three = a[j+1];

				int ttot = first+second;


				int one = first + three + a[j+2];

				if(one==input)
				{
					System.out.println("*******************************");
					System.out.println("Final Value : "+one);
					System.out.println("Probability 1 Value : "+first+" + "+three+ " + " +a[j+2]);					
					System.out.println("*******************************");
					//break;
				}

				//System.out.println("Total Value : "+tot);
				if(first==input)
				{
					System.out.println("Probability Value : "+first);
					//break;
				}
				if(input==ttot)
				{

					System.out.println("*******************************");
					System.out.println("Final Value : "+ttot);
					System.out.println("Probability Value : "+first+" + "+second);					
					System.out.println("*******************************");
					//break;
				}


			}





		}*/


	}

}
