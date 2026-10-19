package com.shopsphere.rmi;
import java.rmi.*;
public interface InventoryService extends Remote {int getStock(int productId)throws RemoteException;boolean updateStock(int productId,int newQuantity)throws RemoteException;}
