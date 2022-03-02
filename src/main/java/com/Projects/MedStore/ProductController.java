package com.Projects.MedStore;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import com.Projects.MedStore.Model.Product;
import com.Projects.MedStore.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${application.upload.folder.image}")
    private String uploadFolderPath;

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
	
	

	@PostMapping("/addProduct")
	public String addProduct( @RequestParam ("pageFlag") String backFlag,@RequestParam ("pdtIdInput") String productId,@RequestParam("pdtNameInput") String productName,@RequestParam("pdtDescInput") String productDescription,@RequestParam("pdtMfgInput") String  mfgDate,
	@RequestParam("pdtExpInput") String expDate, @RequestParam("pdtBatchInput")String batch, @RequestParam("pdtMrpInput") String mrp, @RequestParam("pdtSellerInput") String seller,
	@RequestParam("pdtDiscountInput") String discount, @RequestParam("pdtMedTypeInput") String medtype, @RequestParam("boxNoBoughtInput") String boxBought, @RequestParam("boxNoSoldInput") String boxSold,
	@RequestParam("addNoteInput") String note,@RequestParam("medImg") MultipartFile file,HttpServletResponse httpResponse,
	@RequestParam("pdtBonusInput") String bonus,
	@RequestParam("pdtBrandInput") String brand,
	@RequestParam("pdtCompositionInput") String composition )  throws Exception {
		//System.out.println("fuction called");
		Product pdt = new Product();
		String formattedMfgDt=null;
		String formattedExpDt=null;

		if(productId==null||productId.equals("")||productId.trim().length()==0){
			//System.out.println("id present");
			pdt.setId(UUID.randomUUID().toString());
			

		}else{
			//System.out.println("null id");
			pdt.setId(productId);
		}

		
		pdt.setProductName(productName);
		pdt.setProductDesc(productDescription);
		if(mfgDate.length()>0){
			formattedMfgDt=changeDtFormat(mfgDate);
			if(formattedMfgDt!=null){
				pdt.setMfgDate( Date.valueOf(formattedMfgDt));
			}
		}
		
		if(expDate.length()>0){
			formattedExpDt=changeDtFormat(expDate);
			if(formattedExpDt!=null){
				pdt.setExpDate( Date.valueOf(formattedExpDt));
			}
		}
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
		if(bonus.trim().length()>0){
			pdt.setBonus(Integer.parseInt(bonus.trim()));
		}else{
			pdt.setNoBoxSold(0);
		}
		
		
		pdt.setAdditionalNotes(note);
		//pdt.setBonus(Integer.parseInt(bonus));
		pdt.setBrand(brand);
		pdt.setComposition(composition);
		
		String randomFileId=UUID.randomUUID().toString();
		//String fileName=file.getOriginalFilename();


		String fileName=randomFileId.concat("_"+file.getOriginalFilename());
		String fullFileName=uploadFolderPath.concat(fileName);

		 //String rootuploadPath="C:\\MedStore\\upload\\";
		 try {
			file.transferTo( new File( fullFileName));
		 }catch (Exception e){
			e.printStackTrace();
		 }
		pdt.setProductImagePath(fileName);
		productService.addProduct(pdt);
		//return pdt.getId();
		if(backFlag.equals("backToCard")){
			httpResponse.sendRedirect("/medstore");
		} else if(backFlag.equals("backToTable")){
			httpResponse.sendRedirect("/pdtListPage");
		}
		
        return null;
		//return "redirect:/getAllProducts";
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
		String formattedMfgDt=null;
		String formattedExpDt=null;
		Product pdt = new Product();
		pdt.setId(productId);
		pdt.setProductName(productName);
		pdt.setProductDesc(productDescription);

		if(mfgDate.length()>0){
			formattedMfgDt=changeDtFormat(mfgDate);
			if(formattedMfgDt!=null){
				pdt.setMfgDate( Date.valueOf(formattedMfgDt));
			}
		}
		
		if(expDate.length()>0){
			formattedExpDt=changeDtFormat(expDate);
			if(formattedExpDt!=null){
				pdt.setExpDate( Date.valueOf(formattedExpDt));
			}
		}
		
		
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
	

	

	

	@GetMapping("/viewProduct")
	public ResponseEntity<Object> showProductDetails() {

		return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
	}
//not in use
	@PostMapping( "/upload")
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
	public String changeDtFormat(String inputDt){
		String outDt=null;
		String[] dtComponentArray=inputDt.split("-");
		if(dtComponentArray.length==3 ){
			outDt=dtComponentArray[2]+"-"+dtComponentArray[1]+"-"+dtComponentArray[0];
		}
		

		return outDt;

	}

}
