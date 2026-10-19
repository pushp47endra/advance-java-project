package com.shopsphere.extras;
import java.util.*;import java.text.*;import java.net.*;import java.io.*;import javax.naming.*;
public class Demos {
 public static void main(String[] args)throws Exception{for(Locale locale:List.of(Locale.ENGLISH,Locale.forLanguageTag("hi-IN"))){ResourceBundle b=ResourceBundle.getBundle("messages",locale);System.out.println(b.getString("welcome")+" — "+NumberFormat.getCurrencyInstance(locale).format(2499));}
 URL url=new URL("https://example.com");URLConnection conn=url.openConnection();conn.setConnectTimeout(3000);System.out.println("URL protocol: "+url.getProtocol()+" / class="+conn.getClass().getSimpleName());
 try {new InitialContext().lookup("java:comp/env/jdbc/ShopSphereDB");}catch(NamingException ex){System.out.println("JNDI resource not configured (optional): "+ex.getClass().getSimpleName());}
 }
}
