package com.shopsphere.network;
import java.net.*;import java.io.*;
public class InventoryClient {public static void main(String[] args)throws Exception{String command=args.length>0?String.join(" ",args):"GET_STOCK 1";try(Socket s=new Socket("localhost",5050);BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));PrintWriter out=new PrintWriter(s.getOutputStream(),true)){out.println(command);System.out.println(in.readLine());}}}
