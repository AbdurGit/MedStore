package com.Projects.MedStore;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.Projects.MedStore.Model.Product;
import com.Projects.MedStore.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	private static Map<String, Product> productRepo = new HashMap<>();
	
	
	
@GetMapping("/medstore")
public ModelAndView home() {
	    
	ModelAndView modelAndView = new ModelAndView();    
	modelAndView.setViewName("Home");        
	return modelAndView;    
}
	@GetMapping("/pdtListPage")    
	public ModelAndView showView()  {    
	ModelAndView modelAndView = new ModelAndView();    
	modelAndView.setViewName("ProductList");        
	return modelAndView;    
	}

	@GetMapping("/pdtUploadPage")    
	public ModelAndView showPdtUploadPage()  {    
	ModelAndView modelAndView = new ModelAndView();    
	modelAndView.setViewName("ProductRegistration");        
	return modelAndView;    
	}


	@GetMapping("/advancedSearchPage")    
	public ModelAndView showAdvSearchPage()  {    
	ModelAndView modelAndView = new ModelAndView();    
	modelAndView.setViewName("AdvancedSearch");        
	return modelAndView;    
	}
	
	

	@GetMapping("/addProduct")
	public String addProduct( @RequestParam String productName,@RequestParam String productDescription,@RequestParam String  mfgDate,
	@RequestParam String expDate, @RequestParam String batch, @RequestParam String mrp, @RequestParam String seller,
	@RequestParam String discount, @RequestParam String medtype, @RequestParam String boxBought, @RequestParam String boxSold,
	@RequestParam String note,@RequestParam("medImage") MultipartFile file) {
		//System.out.println("fuction called");
		Product pdt = new Product();
		pdt.setId(UUID.randomUUID().toString());
		pdt.setProductName(productName);
		pdt.setProductDesc(productDescription);
		pdt.setMfgDate( Date.valueOf(mfgDate));
		pdt.setExpDate(Date.valueOf(expDate));
		pdt.setBatch(batch);
		pdt.setMrp(Double.parseDouble(mrp));
		pdt.setSellerName(seller);
		if(discount.trim().length()>0){
			pdt.setPercentageDiscount(Double.parseDouble(discount.trim()));
		}else{
			pdt.setPercentageDiscount(0);
		}
		
		pdt.setMedType(medtype);
		if(boxBought.trim().length()>0){
			pdt.setNoBoxBought(Integer.parseInt(boxBought.trim()));
		}else{
			pdt.setNoBoxBought(0);
		}

		if(boxSold.trim().length()>0){
			pdt.setNoBoxSold(Integer.parseInt(boxSold.trim()));
		}else{
			pdt.setNoBoxSold(0);
		}
		
		
		pdt.setAdditionalNotes(note);
		String fileName=file.getOriginalFilename();

		String rootuploadPath="C:\\MedStore\\upload\\";
		try {
			file.transferTo( new File( rootuploadPath.concat(fileName)));
		}catch (Exception e){
			e.printStackTrace();
		}


		
		productService.addProduct(pdt);
		return pdt.getId();
	}


	@GetMapping("/getAllProducts")
	public Iterable<Product> getAllProducts() {

		return productService.getAllProducts();
	}


	@GetMapping("/deleteProduct")
	public void deleteProduct( @RequestParam String productId) {
		Product product=new Product();
		product.setId(productId);

		productService.deleteProduct(product);
	}


	@GetMapping("/getProductById/{productId}")
	public Optional<Product> getProductById(@PathVariable String productId) {

		return productService.getProductById(productId);
	}

	
	@GetMapping("/updateProduct")
	public String updateProduct( @RequestParam String productId, @RequestParam String productName,@RequestParam String productDescription,@RequestParam String  mfgDate,
	@RequestParam String expDate, @RequestParam String batch, @RequestParam String mrp, @RequestParam String seller,
	@RequestParam String discount, @RequestParam String medtype, @RequestParam String boxBought, @RequestParam String boxSold,
	@RequestParam String note) {
		//System.out.println("fuction called");
		Product pdt = new Product();
		pdt.setId(productId);
		pdt.setProductName(productName);
		pdt.setProductDesc(productDescription);
		pdt.setMfgDate( Date.valueOf(mfgDate));
		pdt.setExpDate(Date.valueOf(expDate));
		pdt.setBatch(batch);
		pdt.setMrp(Double.parseDouble(mrp));
		pdt.setSellerName(seller);
		pdt.setPercentageDiscount(Double.parseDouble(discount));
		pdt.setMedType(medtype);
		pdt.setNoBoxBought(Integer.parseInt(boxBought));
		pdt.setNoBoxSold(Integer.parseInt(boxSold));
		pdt.setAdditionalNotes(note);
		
		productService.updateProduct(pdt);
		return pdt.getId();
	}
	

	

	

	@GetMapping("/showProductDetails")
	public ResponseEntity<Object> showProductDetails() {

		return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
	}

	@PostMapping( path = "/upload")
	public String upload(@RequestParam("file") MultipartFile file) {
		String fileName=file.getOriginalFilename();

		String rootuploadPath="C:\\MedStore\\upload\\";
		try {
			file.transferTo( new File( rootuploadPath.concat(fileName)));
		}catch (Exception e){
			e.printStackTrace();
		}
		return "success!!";

	}

}
