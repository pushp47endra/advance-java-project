package com.shopsphere.model;
import java.io.Serializable;
import java.math.BigDecimal;
public class Product implements Serializable {
 private static final long serialVersionUID=1L;
 private int productId,categoryId,stock;private String name,brand,description,imageUrl;private BigDecimal price,discount;private boolean status;
 public int getProductId(){return productId;}public void setProductId(int x){productId=x;}
 public int getCategoryId(){return categoryId;}public void setCategoryId(int x){categoryId=x;}
 public int getStock(){return stock;}public void setStock(int x){stock=x;}
 public String getName(){return name;}public void setName(String x){name=x;}
 public String getBrand(){return brand;}public void setBrand(String x){brand=x;}
 public String getDescription(){return description;}public void setDescription(String x){description=x;}
 public String getImageUrl(){return imageUrl;}public void setImageUrl(String x){imageUrl=x;}
 public BigDecimal getPrice(){return price;}public void setPrice(BigDecimal x){price=x;}
 public BigDecimal getDiscount(){return discount;}public void setDiscount(BigDecimal x){discount=x;}
 public boolean isStatus(){return status;}public void setStatus(boolean x){status=x;}
 public BigDecimal getSalePrice(){return price.multiply(BigDecimal.ONE.subtract(discount.divide(new BigDecimal("100")))).setScale(2,java.math.RoundingMode.HALF_UP);}
}
