import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class Framework
{
	int n; // number of applicants (employers)

	int APrefs[][]; // preference list of applicants (n*n)
	int EPrefs[][]; // preference list of employers (n*n)

	ArrayList<MatchedPair> MatchedPairsList; // your output should fill this arraylist which is empty at start

	public class MatchedPair
	{
		int appl; // applicant's number
		int empl; // employer's number

		public MatchedPair(int Appl,int Empl)
		{
			appl=Appl;
			empl=Empl;
		}

		public MatchedPair()
		{
		}
	}

	// reading the input
	void input(String input_name)
	{
		File file = new File(input_name);
		BufferedReader reader = null;

		try
		{
			reader = new BufferedReader(new FileReader(file));

			String text = reader.readLine();

			String [] parts = text.split(" ");
			n=Integer.parseInt(parts[0]);

			APrefs=new int[n][n];
			EPrefs=new int[n][n];

			for (int i=0;i<n;i++)
			{
				text=reader.readLine();
				String [] aList=text.split(" ");
				for (int j=0;j<n;j++)
				{
					APrefs[i][j]=Integer.parseInt(aList[j]);
				}
			}

			for (int i=0;i<n;i++)
			{
				text=reader.readLine();
				String [] eList=text.split(" ");
				for(int j=0;j<n;j++)
				{
					EPrefs[i][j]=Integer.parseInt(eList[j]);
				}
			}

			reader.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// writing the output
	void output(String output_name)
	{
		try
		{
			PrintWriter writer = new PrintWriter(output_name, "UTF-8");

			for(int i=0;i<MatchedPairsList.size();i++)
			{
				writer.println(MatchedPairsList.get(i).empl+" "+MatchedPairsList.get(i).appl);
			}

			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public Framework(String []Args)
	{
		input(Args[0]);

		MatchedPairsList=new ArrayList<MatchedPair>(); // you should put the final stable matching in this array list

		/* NOTE
		 * if you want to declare that man x and woman y will get matched in the matching, you can
		 * write a code similar to what follows:
		 * MatchedPair pair=new MatchedPair(x,y);
		 * MatchedPairsList.add(pair);
		*/

		//YOUR CODE STARTS HERE
		int numpaired=0;
		int ARevPrefs[][];
		int ERevPrefs[][];
		ARevPrefs=new int[n][n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				ARevPrefs[i][APrefs[i][j]]=j;
			}
		}
		ERevPrefs=new int[n][n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				ERevPrefs[i][EPrefs[i][j]]=j;
			}
		}
		int states[];
		states=new int[n];
		for (int i=0; i<n; i++) {
			states[i]=-1; /*shows what employer each 
			*/
		}
		int currentemployer=0;
		int currentpref=0;
		while(numpaired<n) {
			if(states[EPrefs[currentemployer][currentpref]]==-1) {
				states[EPrefs[currentemployer][currentpref]]=currentemployer;
				numpaired=numpaired+1;
				currentemployer=numpaired;
				currentpref=0;
			}
			else {
				if(ARevPrefs[EPrefs[currentemployer][currentpref]][states[EPrefs[currentemployer][currentpref]]]<ARevPrefs[EPrefs[currentemployer][currentpref]][currentemployer]) {
					/*case where old preference was superior proposal rejected
					 * 
					 */
					currentpref=currentpref+1;
				}
				else {
					/*case where proposal accepted
					 */
					
					int tempapp=EPrefs[currentemployer][currentpref];/*applicant getting swapped on*/
					int tempswap=states[tempapp];/*employer getting released*/
					states[tempapp]=currentemployer;/*setting the applicants pairing to new employer*/
					currentpref=ERevPrefs[tempswap][tempapp]+1;
					currentemployer=tempswap;
					
				}
			}
		}
		for (int i=0; i<n; i++) {
			MatchedPair pair=new MatchedPair(i,states[i]);
			MatchedPairsList.add(pair);
		}
		
		
		//YOUR CODE ENDS HERE

		output(Args[1]);
	}

	public static void main(String [] Args) // Strings in Args are the name of the input file followed by the name of the output file
	{
		new Framework(Args);
	}
}
