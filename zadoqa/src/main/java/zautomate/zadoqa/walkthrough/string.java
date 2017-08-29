package zautomate.zadoqa.walkthrough;

public class string 
{

	public static void main(String[] args)
	{
		String one = "hai&how&are&you";
		String[] two = one.split("&");
		System.out.println("count : "+two.length);
		
		for(int i=0;i<two.length;i++)
		{
			if(two.length==4)
			{
				System.out.println("count 4 : "+i+ " Value 4 :"+two[3]);
			}
			System.out.println("count : "+i+ " Value :"+two[i]);
		}
		
		
	}
	
	
	
}
