package com.shopsphere.rmi;
import java.rmi.registry.*;
public class InventoryClient {public static void main(String[] args)throws Exception {InventoryService svc=(InventoryService)LocateRegistry.getRegistry("localhost",1099).lookup("ShopSphereInventory");System.out.println("Stock for product 1: "+svc.getStock(1));}}
