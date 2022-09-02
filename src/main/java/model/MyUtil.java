package model;
import java.io.*;

public class MyUtil {
	public static void printHead (PrintWriter pw) {
		pw.println("<html><head>");
		pw.println("<content = \"text/html; charset = Big5\">");
		pw.println("<title>Electric Commercial</title>");
		pw.println("<script language = javascript>");
		pw.println("function checkDel(url) {if (confirm('Sure, y/n ?'))   { location.href=url; } }");
        pw.println("function checkString(s1,s2) { if ((s1==\"\")||(s2==\"\")) {alert(\"Please fill complete\");  return false;}} ");
        pw.println("function checkOneStr(s1) {  if (s1==\"\") { alert(\"Please fill complete\");  return false;  } }  ");        
        pw.println("function checkModify(f) {                "); 
        pw.println("   if (f.length == 0) { alert(\"Please fill complete\");    return false;  }    ");
        pw.println("   if (confirm('Sure, y/n ?'))   {  return true;  }    ");
        pw.println("   return false;   }             ");
        pw.println("</script></head>");
        pw.println("<BODY TEXT=#FFFFFF BGCOLOR=#000000  link=#00FFFF vlink=#CCFF33 alink=#FFCCFF > <font size=2>");
        pw.println("<h3> Welcome To EC2 </h3>"); 
	}
	public static void printMemberHead(PrintWriter pw, String name) {
		pw.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
	    pw.println("<head>");        
	    pw.println("<content=\"text/html; charset=Big5\">");
	    pw.println("<title>EC2</title>");
	    pw.println("<h3>" + name + "</h3>");
	    pw.println("</head>");
	    pw.println("<BODY TEXT=#FFFFFF BGCOLOR=#000000>");
	    pw.println("<h3> Welcome To EC2 </h3>");      
	}    
	public static void printRow(PrintWriter pw, String s1, String s2, String s3) { 
		pw.println("<tr>");
		pw.println("<td width = 100>" + s1 + "</td>");
		pw.println("<td width = 100>" + s2 + "</td>");                   
		pw.println("<td width = 100>" + s3 + "</td>");
		pw.println("</tr>");
	}    
}
