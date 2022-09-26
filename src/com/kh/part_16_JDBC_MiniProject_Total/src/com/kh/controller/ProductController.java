package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.ProductService;
import com.kh.model.vo.Product;
import com.kh.view.ProductView;

public class ProductController {

	public void selectAll() {
		
		ArrayList<Product> list = new ProductService().selectAll();
		
		if (list.isEmpty()) {
			new ProductView().displayNodata("아직 등록된 상품이 없습니다.");
		} else {
			new ProductView().displayList(list);
		}
	}
	
	public void insertProduct(String productId, String productName, int price, String description, int stock) {
		
		Product p = new Product(productId, productName, price, description, stock);
		
		int result = new ProductService().insertProduct(p);
		
		if (result > 0) {
			new ProductView().displaySuccess("상품 추가 성공");
		} else {
			new ProductView().displayFail("상품 추가 실패");
		}
	}
	
	public void selectByProductName(String keyword) {
		
		ArrayList<Product> list = new ProductService().selectByProductName(keyword);
		
		if (list.isEmpty()) {
			new ProductView().displayNodata(keyword+" 에 해당하는 상품이 없습니다.");
		} else {
			new ProductView().displayList(list);
		}
	}
	
	public void updateProduct(String productId, int price, String description, int stock) {
		
		Product p = new Product();
		p.setProductId(productId);
		p.setPrice(price);
		p.setDescription(description);
		p.setStock(stock);
		
		int result = new ProductService().updateProduct(p);
		
		if (result > 0) {
			new ProductView().displaySuccess("정보 수정 성공");
		} else {
			new ProductView().displayFail("정보 수정 실패");
		}
	}
	
	public void deleteProduct(String productId) {
		
		int result = new ProductService().deleteProduct(productId);
		
		if (result > 0) {
			new ProductView().displaySuccess("상품 삭제 성공");
		} else {
			new ProductView().displayFail("상품 삭제 실패");
		}
	}
	
	public void selectByPrice(int min, int max) {
		
		ArrayList<Product> list = new ProductService().selectByPrice(min, max);
		
		if(list.size() == 0) {
			new ProductView().displayNodata("해당 가격대의 상품이 없습니다.");
		}
		else {
			new ProductView().displayList(list);
		}
	}
	
	public void selectByKind(String kind) {
		
		ArrayList<Product> list = new ProductService().selectByKind(kind);
		
		if (list.isEmpty()) {
			new ProductView().displayNodata("입력하신 종류의 상품이 없습니다");
		} else {
			new ProductView().displayList(list);
		}
	}
	
	public void selectByRandom() {
		
		ArrayList<Product> list = new ProductService().selectAll();
		
		if (list.isEmpty()) {
			new ProductView().displayNodata("아직 등록된 상품이 없습니다.");
		} else {
			new ProductView().displayRandom(list);
		}
	}
	
	public void selectByStock(int stock) {
		
		ArrayList<Product> list = new ProductService().selectByStock(stock);
		
		if (list.isEmpty()) {
			new ProductView().displayNodata("재고가 "+stock+" 개 이하인 상품이 없습니다.");
		} else {
			new ProductView().displayList(list);
		}
	}
}
