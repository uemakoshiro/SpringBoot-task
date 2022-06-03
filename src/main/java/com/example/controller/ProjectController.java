package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.controller.form.IndexForm;
import com.example.controller.form.InsertForm;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import com.example.service.UserService;

@Controller
public class ProjectController {

    @Autowired
    UserService userService;
    
    @Autowired
    ProductService productService;
    
    @Autowired
    CategoryService categoryService;
    
    @Autowired
    Product pd;
    
    @Autowired
    HttpSession session;
    
    @RequestMapping({ "/", "/index" })
    public String index(@ModelAttribute("index") IndexForm form, Model model) {
        return "index";
    }
    
    @RequestMapping({ "/back" })
    public String back(Model model) {
    	
    	String key = (String) session.getAttribute("keyword");
    	String order = (String) session.getAttribute("order");
    	model.addAttribute("SearchResult", productService.Search(key,order));
        return "menu";
    }
    
    @RequestMapping( value="/login", method=RequestMethod.POST)
    public String login(@Validated @ModelAttribute("index") IndexForm form,BindingResult bindingResult, Model model) {
    	if(bindingResult.hasErrors()) {
            return "index";
        }
    	String id = form.getLoginId();
    	String pass = form.getPass();
    	
    	User user = userService.loginCheck(id, pass);
    	if(user == null) {
    		model.addAttribute("msg", "不正なログインです");
    		return "index";
    	}else {
    		session.setAttribute("userInfo", user);
    		session.setAttribute("keyword", "");
    		session.setAttribute("order", "");
    		model.addAttribute("SearchResult", productService.SelectAll());
            return "menu";
    	}
    }
    
    @RequestMapping( value="login", method=RequestMethod.GET)
    public String loginGet(@ModelAttribute("index") IndexForm form, Model model) {
    	if(session.getAttribute("userInfo") == null) {
        	return "index";
    	}else {
    		model.addAttribute("SearchResult", productService.SelectAll());
    		return "menu";
    	}
    }
    
    @RequestMapping( value="/insert", method=RequestMethod.GET)
    public String insert( @ModelAttribute("index") IndexForm form,@ModelAttribute("insert") InsertForm iform,Model model) {
    	if(session.getAttribute("userInfo") == null) {
        	return "index";
    	}
    	model.addAttribute("CategoryList",categoryService.SelectAll());
    	return "insert";
    }
    
    @RequestMapping( value="/insertexe", method=RequestMethod.POST)
    public String insertExe(@Validated @ModelAttribute("insert") InsertForm form,BindingResult bindingResult, Model model) {
    	
    	if(bindingResult.hasErrors()) {
    		model.addAttribute("CategoryList",categoryService.SelectAll());
            return "insert";
        }
    	pd.setProductId(form.getProductId());
    	pd.setName(form.getProductName());
    	pd.setPrice(form.getPrice());
    	pd.setCategoryId(form.getCategory());
    	pd.setDescription(form.getDescription());
    	pd.setImagePath(form.getImagePath());
    	int result = productService.Insert(pd);
    	if(result == 1) {
    		model.addAttribute("msg", "登録が完了しました");
    	}else {
    		model.addAttribute("msg", "登録出来ませんでした");
    	}
    	return "insert";
    }
    
    @RequestMapping( value="/search", method=RequestMethod.GET)
    public String search(@RequestParam("keyword") String key,@RequestParam("order") String order, Model model) {
    	
    	model.addAttribute("SearchResult",productService.Search(key,order) );
    	session.setAttribute("keyword", key);
    	session.setAttribute("order", order);
    	return "menu";
    }
    
    @RequestMapping( value="/detail", method=RequestMethod.GET)
    public String detail(@ModelAttribute("index") IndexForm form,@RequestParam("id") String id, Model model) {
    	if(session.getAttribute("userInfo") == null) {
        	return "index";
    	}else{
    		model.addAttribute("info",productService.SelectId(id));
    	    return "detail";
    	}
    }
    
    @RequestMapping( value="/update", method=RequestMethod.GET)
    public String update(@ModelAttribute("index") IndexForm iform,@ModelAttribute("update") InsertForm form,@RequestParam("id") String id, Model model) {
    	if(session.getAttribute("userInfo") == null) {
        	return "index";
    	}
    	pd = productService.SelectId(id);
    	form.setProductId(pd.getProductId());
    	form.setProductName(pd.getName());
    	form.setPrice(pd.getPrice());
    	form.setDescription(pd.getDescription());
    	form.setCategory(pd.getCategoryId());
    	session.setAttribute("id",pd.getId());
    	//model.addAttribute("cid",pd.getCategoryId());
    	model.addAttribute("CategoryList",categoryService.SelectAll());
    	return "updateInput";
    }
    
    @RequestMapping( value="/updateexe", method=RequestMethod.POST)
    public String updateExe(@Validated @ModelAttribute("update") InsertForm form,BindingResult bindingResult, Model model) {
    	
    	if(bindingResult.hasErrors()) {
    		model.addAttribute("CategoryList",categoryService.SelectAll());
            return "updateInput";
        }
    	pd.setProductId(form.getProductId());
    	pd.setName(form.getProductName());
    	pd.setPrice(form.getPrice());
    	pd.setCategoryId(form.getCategory());
    	pd.setDescription(form.getDescription());
    	pd.setImagePath(form.getImagePath());
    	pd.setId((String)session.getAttribute("id"));
    	int result = productService.Update(pd);
    	if(result == 1) {
    		model.addAttribute("msg", "更新が完了しました");
    	}else {
    		model.addAttribute("msg", "更新出来ませんでした");
    	}
    	String key = (String) session.getAttribute("keyword");
    	String order = (String) session.getAttribute("order");
    	model.addAttribute("SearchResult", productService.Search(key,order));
    	return "menu";
    }

    @RequestMapping( value="/delete", method=RequestMethod.GET)
    public String delete(@RequestParam("id") String id, Model model) {
    	
    	int result = productService.Delete(id);
    	if(result == 1) {
    		model.addAttribute("msg", "削除しました");
    	}else {
    		model.addAttribute("msg", "削除出来ませんでした");
    	}
    	String key = (String) session.getAttribute("keyword");
    	String order = (String) session.getAttribute("order");
    	model.addAttribute("SearchResult", productService.Search(key,order));
    	return "menu";
    }
    
    @RequestMapping({ "/logout" })
    public String index( Model model) {
    	session.invalidate();
        return "logout";
    }
    
}
