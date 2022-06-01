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
import com.example.service.ProductService;
import com.example.service.UserService;

@Controller
public class ProjectController {

    @Autowired
    UserService userService;
    
    @Autowired
    ProductService productService;
    
    @Autowired
    Product pd;
    
    @Autowired
    HttpSession session;
    
    @RequestMapping({ "/", "/index" })
    public String index(@ModelAttribute("index") IndexForm form, Model model) {
        return "index";
    }
    
    @RequestMapping({ "/back" })
    public String index(Model model) {
    	String key = (String) session.getAttribute("keyword");
    	model.addAttribute("SearchResult", productService.Search(key));
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
    		model.addAttribute("SearchResult", productService.SelectAll());
            return "menu";
    	}
    }
    
    @RequestMapping( value="/insert", method=RequestMethod.GET)
    public String insert( @ModelAttribute("insert") InsertForm form,Model model) {
    	return "insert";
    }
    
    @RequestMapping( value="/insertexe", method=RequestMethod.POST)
    public String insertExe(@Validated @ModelAttribute("insert") InsertForm form,BindingResult bindingResult, Model model) {
    	if(bindingResult.hasErrors()) {
            return "insert";
        }
    	pd.setProductId(form.getProductId());
    	pd.setName(form.getProductName());
    	pd.setPrice(form.getPrice());
    	pd.setCategoryId(form.getCategory());
    	pd.setDescription(form.getDescription());
    	pd.setImg(form.getImg());
    	int result = productService.Insert(pd);
    	if(result == 1) {
    		model.addAttribute("msg", "登録が完了しました");
    	}else {
    		model.addAttribute("msg", "登録出来ませんでした");
    	}
    	return "insert";
    }
    
    @RequestMapping( value="/search", method=RequestMethod.GET)
    public String search(@RequestParam("keyword") String key, Model model) {
    	model.addAttribute("SearchResult",productService.Search(key) );
    	session.setAttribute("keyword", key);
    	return "menu";
    }
    
    @RequestMapping( value="/detail", method=RequestMethod.GET)
    public String detail(@RequestParam("id") String id, Model model) {
    	model.addAttribute("info",productService.SelectId(id));
    	return "detail";
    }
    
    @RequestMapping( value="/update", method=RequestMethod.GET)
    public String update(@ModelAttribute("update") InsertForm form,@RequestParam("id") String id, Model model) {
    	System.out.println(id);
    	pd = productService.SelectId(id);
    	System.out.println(pd.getName());
    	model.addAttribute("data",pd);
    	return "updateInput";
    }
    
    @RequestMapping( value="/updateexe", method=RequestMethod.POST)
    public String updateExe(@Validated @ModelAttribute("update") InsertForm form,BindingResult bindingResult, Model model) {
    	if(bindingResult.hasErrors()) {
            return "updateInput";
        }
    	return "updateInput";
    }

}
