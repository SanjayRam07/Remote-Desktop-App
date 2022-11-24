package test;
import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner sc=new Scanner(System.in);
		int t1=0,t2=0,time=0,n=0;
		int t=sc.nextInt();
		
		for(int i=0;i<t;i++) {
			System.out.println("n");
		    n=sc.nextInt();
			System.out.println("t1");
		    t1=sc.nextInt();
			System.out.println("t2");
		    t2=sc.nextInt();
		    while(n>2) {
				System.out.println(n);
		        time+=(t1+t2);
		        n=n/2;
		    }
		    time+=t1;
		    System.out.println(time);
			time=0;
		}
	}
}
