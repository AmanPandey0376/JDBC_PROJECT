package com.qsp.gms.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.postgresql.Driver;

import com.qsp.gms.model.Product;

public class ShopController {

	public int addproduct(int id, String name, int price, int quntity, boolean availability) {
		int rowsAffected = 0;
		Connection connection = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/grocery_shop", "postgres",
					"root");

			PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO products VALUES(?,?,?,?,?)");
			prepareStatement.setInt(1, id);
			prepareStatement.setString(2, name);
			prepareStatement.setInt(3, price);
			prepareStatement.setInt(4, quntity);
			prepareStatement.setBoolean(5, availability);
			rowsAffected = prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e2) {
				}
			}
		}
		return rowsAffected;

	}

	public void addMultipleProduct(ArrayList<Product> products) {
		Driver driver = new Driver();
		Connection connection = null;
		try {
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);

			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/grocery_shop", properties);

			PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO products VALUES (?,?,?,?,?)");

			for (Product product : products) {
				System.out.println("Product id : " + product.getP_id());
				System.out.println("Product name : " + product.getP_name());
				System.out.println("Product price : " + product.getP_price());
				System.out.println("Product quntity : " + product.getP_quantity());
				System.out.println("Product available : " + product.isP_availability());
				prepareStatement.setInt(1, product.getP_id());
				prepareStatement.setString(2, product.getP_name());
				prepareStatement.setInt(3, product.getP_price());
				prepareStatement.setInt(4, product.getP_quantity());
				prepareStatement.setBoolean(5, product.isP_availability());
				prepareStatement.addBatch();
			}
			prepareStatement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e2) {
				}
			}
		}
	}

	public ResultSet fetchProduct(int id) {
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/grocery_shop", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM products WHERE p_id=?");
			prepareStatement.setInt(1, id);
			resultSet = prepareStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e2) {
				}
			}
		}
		return resultSet;
	}

	public int removeproduct(int id) {
		Connection connection = null;
		int idDelete = 0;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/grocery_shop", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM products WHERE p_id=?");
			prepareStatement.setInt(1, id);
			idDelete = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e2) {
				}
			}
		}
		return idDelete;
	}

	public int updateProductName(int id, String name) {

		Connection connection = null;
		int idUpdate = 0;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/grocery_shop", properties);
			PreparedStatement prepareStatement = connection
					.prepareStatement("	UPDATE products SET p_name=? WHERE p_id=?");
			prepareStatement.setString(1, name);
			prepareStatement.setInt(2, id);
			idUpdate = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e2) {
				}
			}
		}
		return idUpdate;
	}

	public int updateProducteprice(int id, int price) {
		Connection connection = null;
		int priceUpdate = 0;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/grocery_shop", properties);
			PreparedStatement prepareStatement = connection
					.prepareStatement("	UPDATE products SET p_price=? WHERE p_id=?");
			prepareStatement.setInt(1, price);
			prepareStatement.setInt(2, id);
			priceUpdate = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e2) {
				}
			}
		}
		return priceUpdate;
	}

	public int updateProductQuantity(int id, int quantity) {
		Connection connection = null;
		int quantityUpdate = 0;

		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);

			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/grocery_shop", properties);
			PreparedStatement prepareStatement = connection
					.prepareStatement("UPDATE products SET p_quantity=? WHERE p_id=?");
			prepareStatement.setInt(1, quantity);
			prepareStatement.setInt(2, id);
			quantityUpdate = prepareStatement.executeUpdate();
			
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e2) {
				}
			}
		}
		return quantityUpdate;
	}

}
