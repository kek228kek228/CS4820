import java.io.BufferedReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.*;
import java.util.*;
class Main {
	static class Sub{
		ArrayList<Integer> s1;
		ArrayList<Integer> s2;
		int cost;
		Sub(ArrayList<Integer> s1,ArrayList<Integer> s2, int cost) {
			this.s1=s1;
			this.s2=s2;
			this.cost=cost;
		}
	}
	static class Problem{
		int[][] costs;
		int gappen;
		String x;
		String y;
		int[][] arr;
		public Problem(int[][] costs, int gappen, String x, String y, int[][] arr) {
			this.costs=costs;
			this.gappen=gappen;
			this.x=x;
			this.y=y;
			this.arr=arr;
		}
		public void recOPT(int m, int n) {
			for (int j=1;j<=n;j++) {
				//System.out.println(Integer.toString(n));
				for (int i=1;i<=m;i++) {
					int c1=costs[x.charAt(i-1)-'a'][y.charAt(j-1)-'a']+arr[i-1][j-1];
					int c2=arr[i-1][j]+gappen;
					int c3=arr[i][j-1]+gappen;
					//System.out.println("x: "+Character.toString(x.charAt(i-1))+" y: "+Character.toString(y.charAt(j-1))+" c1: "+Integer.toString(c1)+" c2: "+Integer.toString(c2)+" c3: "+Integer.toString(c3));
					if (c1<c2 && c1<c3) {
					arr[i][j]= c1;
					
					}
					else if (c2<c1 && c2<c3) {
						arr[i][j]= c2;
					}
					else {
						arr[i][j]= c3;
					}
				}
			}
			
//			if (arr[m][n]!=null) {
//				return arr[m][n];
//			}
//			Sub s1=recOPT(m-1,n-1);
//			Sub s2=recOPT(m-1,n);
//			Sub s3=recOPT(m,n-1);
//			int t1=s1.cost+costs[x.charAt(m-1)-'a'][y.charAt(n-1)-'a'];
//			int t2=s2.cost+gappen;
//			int t3=s3.cost+gappen;
//			if (t1>t2 && t1>t3) {
//				arr[m][n]=new Sub(,,t1);
//				
//			}
//			else if (t2>t1 && t2>t3) {
//				
//			}
//			else {
//				
//			}
			char[] xarr=new char[m];
			char[] yarr=new char[n];
			int j=n;
			int i=m;
			int matchnums=0;
			while(j>0 || i>0) {
				if (j==0) {
					for (int z=i; z>0; z--) {
						xarr[z-1]=' ';
					}
					i=0;
					
				}
				else if(i==0) {
					for (int z=j; z>0; z--) {
						yarr[z-1]=' ';
					}
					j=0;
					
				}
				else if (arr[i][j]==costs[x.charAt(i-1)-'a'][y.charAt(j-1)-'a']+arr[i-1][j-1]) {
					xarr[i-1]=x.charAt(i-1);
					yarr[j-1]=y.charAt(j-1);
					i--;
					j--;
					matchnums++;
				}
				else if (arr[i-1][j]+gappen==arr[i][j]) {
					xarr[i-1]=' ';
					i--;
				}
				else {
					yarr[j-1]=' ';
					j--;
				}
			}
			System.out.println(Integer.toString(arr[m][n]));
			System.out.println(Integer.toString(matchnums));
			i=0;
			j=0;
			while(i<m&&j<n) {
				if (xarr[i]!=' ') {
					if (yarr[j]!=' ') {
						System.out.println(Integer.toString(i+1)+" "+Integer.toString(j+1));
						i++;
						j++;
					}
					else {
						j++;
					}
				}
				else {
					i++;
				}
			}
			
		}
	}
	public static void main(String [] Args) {
		Scanner scanner = new Scanner(System.in);
		int m=scanner.nextInt();
		int n=scanner.nextInt();
		String x= scanner.next();
		String y= scanner.next();
		int gappen=scanner.nextInt();
		int[][] costs=new int[26][26];
		for (int i=0;i<26;i++) {
			for (int j=0;j<26;j++) {
				String char1= scanner.next();
				String char2= scanner.next();
				int pen=scanner.nextInt();
				costs[i][j]=pen;

			}
		}

		int[][] arr=new int[m+1][n+1];
		for(int i=0; i<=m;i++) {
			arr[i][0]= i*gappen;
		}
		for(int j=0; j<=n;j++) {
			arr[0][j]= j*gappen;
		}
		Problem p1=new Problem(costs,gappen,x,y,arr);
		p1.recOPT(m,n);
	}
}
