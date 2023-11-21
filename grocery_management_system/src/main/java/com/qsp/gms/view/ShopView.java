package com.qsp.gms.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.qsp.gms.controller.ShopController;
import com.qsp.gms.model.Product;

public class ShopView {
	static Scanner myInput = new Scanner(System.in);
	static Product product = new Product();
	static ShopController shopController = new ShopController();

	public static void main(String[] args) {
		do {
			System.out.println("select operation to perform:");
			System.out.println("\nWhich of the following operations do you want to perform on the Grocery database: ");
			System.out.print(
					"1. Add product\n2. Remove Product\n3. Update Product Details \n4. Fetch Record\n0. exit");
			System.out.print("\nEnter digit respective to desired option : ");

			int userInput = myInput.nextInt();
			myInput.nextLine();

			switch (userInput) {

			case 0:
				myInput.close();
				System.out.println("-------------EXIT--------------------");
				System.exit(0);
				break;

			case 1:
				System.out.println("How many product Do you want to add ?\n1.single product \n2.Multiple product");
				int Productcount = myInput.nextInt();
				myInput.nextLine();
				if (Productcount == 1) {
					System.out.print("Enter product id : ");
					int i_p_id = myInput.nextInt();
					myInput.nextLine();
					System.out.print("Enter product name : ");
					String i_p_name = myInput.nextLine();
					System.out.print("Enter product price : ");
					int i_p_price = myInput.nextInt();
					myInput.nextLine();
					System.out.print("Enter product quantity : ");
					int i_p_quantity = myInput.nextInt();
					myInput.nextLine();
					boolean i_p_availability = false;
					if (i_p_quantity > 0) {
						i_p_availability = true;
					}
					if ((shopController.addproduct(i_p_id, i_p_name, i_p_price, i_p_quantity, i_p_availability)) != 0) {
						System.out.println("Product Added");
					} else {
						System.out.println("product Not Added");
					}
				} else {
					boolean toContinue = true;
					ArrayList<Product> products = new ArrayList<Product>();
					do {
						Product product = new Product();
						System.out.print("Enter product id : ");
						product.setP_id(myInput.nextInt());
						myInput.nextLine();
						System.out.print("Enter product name : ");
						product.setP_name(myInput.nextLine());
						System.out.print("Enter product price : ");
						product.setP_price(myInput.nextInt());
						myInput.nextLine();
						System.out.print("Enter product quantity : ");
						int quantity = myInput.nextInt();
						product.setP_quantity(quantity);
						myInput.nextLine();
						boolean i_p_availability = false;
						if (quantity > 0) {
							i_p_availability = true;
						}
						product.setP_availability(i_p_availability);
						products.add(product);
						System.out.println("Press 1 for continue adding product, Press 0 for stop adding product");
						int toAdd = myInput.nextInt();
						if (toAdd == 0) {
							toContinue = false;
						}
					} while (toContinue);
					shopController.addMultipleProduct(products);
				}

				break;
			case 2:
				System.out.println("Enter product id to remove : ");
				int nextInt = myInput.nextInt();
				myInput.nextLine();
				if (shopController.removeproduct(nextInt) != 0) {
					System.out.println("ha delete ho gaya");
				} else {
					System.out.println("product with given id does not exit , no remove operation performed");
				}
				break;
			case 3:
				System.out.println("Enter product ID to Update");
				int productIdUpdate = myInput.nextInt();
				myInput.nextLine();
				ResultSet product = shopController.fetchProduct(productIdUpdate);
				try {
					if (product.next()) {
						System.out.println("what do you want to update");
						System.out.println("1.Name\n2.Price\n3.Quantity");
						System.out.println("Enter Number respective to desired option :");
						byte updateOption = myInput.nextByte();
						myInput.nextLine();
						switch (updateOption) {
						case 1:
							System.out.println("Enter Name To Update :");
							String nameToUpdate = myInput.nextLine();
							if (shopController.updateProductName(productIdUpdate, nameToUpdate) != 0) {
								System.out.println("------Record Updated-------");
							} else {
								System.out.println("------Record Not Updated-------");
							}
							break;
						case 2:
							System.out.println("Enter Price To Update :");
							int priceToUpdate = myInput.nextInt();
							if (shopController.updateProducteprice(productIdUpdate, priceToUpdate) != 0) {
								System.out.println("------Record Updated-------");
							} else {
								System.out.println("------Record Not Updated-------");
							}

							break;
						case 3:
							System.out.println("Enter Quantity To Update :");
							int quantityTOUpdate = myInput.nextInt();
							if (shopController.updateProductQuantity(productIdUpdate, quantityTOUpdate) != 0) {
							    System.out.println("------Record Updated-------");
							    boolean i_p_availability = false;
							    
							} else {
							    System.out.println("------Record Not Updated-------");
							}

//								ArrayList<Product> products = new ArrayList<Product>();
//								boolean i_p_availability = false;
//								if (shopController.updateProductQuantity(productIdUpdate, quantityTOUpdate) > 0) {
//									i_p_availability = true;
//									
//								}

							break;

						default:
							System.out.println("----Unvalid Option----");
							break;
						}
					} else {
						System.out.println();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				break;
			case 4:
				System.out.print("Enter product id to fetch : ");
				int productIdToFind = myInput.nextInt();
				myInput.nextLine();
				ResultSet fetchproduct = shopController.fetchProduct(productIdToFind);
				try {
					boolean next = fetchproduct.next();
					if (next) {
						System.out.println("PRODUCT DETAILS");
						System.out.println("Id : " + fetchproduct.getInt(1));
						System.out.println("Name : " + fetchproduct.getString(2));
						System.out.println("Price : " + fetchproduct.getInt(3));
						System.out.println("Quantity : " + fetchproduct.getInt(4));
						if (fetchproduct.getBoolean(5)) {
							System.out.println("--------Availability : Available--------------");
						} else {
							System.out.println("-----------------Availability : Not Available------------------");
						}

					} else {
						System.out.println("Product with id : " + "does not exist.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				break;
		
			}

		} while (true);

	}

}