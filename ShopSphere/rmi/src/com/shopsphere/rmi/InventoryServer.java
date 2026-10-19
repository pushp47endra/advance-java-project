package com.shopsphere.rmi;
import java.rmi.registry.*;
public class InventoryServer {public static void main(String[] args)throws Exception {Registry r=LocateRegistry.createRegistry(1099);r.rebind("ShopSphereInventory",new InventoryServiceImpl());System.out.println("RMI inventory service ready: localhost:1099");}}
