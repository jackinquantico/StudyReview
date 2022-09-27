package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.model.dao.ProductDao;
import com.kh.model.vo.Product;

public class ProductService {

	public ArrayList<Product> selectAll() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Product> list = new ProductDao().selectAll(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public int insertProduct(Product p) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ProductDao().insertProduct(conn, p);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public ArrayList<Product> selectByProductName(String keyword) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Product> list = new ProductDao().selectByProductName(conn, keyword);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public int updateProduct(Product p) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ProductDao().updateProduct(conn, p);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int deleteProduct(String productId) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ProductDao().deleteProduct(conn, productId);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public ArrayList<Product> selectByPrice(int min, int max) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Product> list = new ProductDao().selectByPrice(conn, min, max);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public ArrayList<Product> selectByKind(String kind) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Product> list = new ProductDao().selectByKind(conn, kind);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public ArrayList<Product> selectByStock(int stock) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Product> list = new ProductDao().selectByStock(conn, stock);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
}
