package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import models.Quote;
import models.Entry;
import models.UserMessage;
import play.data.Form;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.*;
import services.BarService;

public class Application extends Controller {
	
	static Random rand = new Random();

    @Autowired
    static BarService barService;

    public static Result index() {
        return ok(views.html.index.render("Some String param can eventually appear.."));
    }
    
    public static Result cpage1(String name, int age) {
    	String s = "Extended Tiger Running Space. cpage1";
        return ok(views.html.vpage1.render(name, age));
    }
    
    public static Result displayObject() {
    	ArrayList <Entry> entries = new ArrayList<Entry>();
    	Entry entry1 = new Entry();
    	Entry entry2 = new Entry();
    	entry1.m1 = "entry from m1";
    	entry2.m2 = "entry from m2";
    	entries.add(entry1);
    	entries.add(entry2);
    	return ok(views.html.objectRenderer.render(entries));
    }
    
    //public static Result cpage3() {
    //    return ok(views.html.vpage3.render("fake"));
    //}
    
    public static Result addQuote() {
        return play.mvc.Controller.redirect(controllers.routes.Application.createNewQuoteAction());
    }
    
    public static Result createNewQuoteAction() {
    	Form<Quote> form = Form.form(Quote.class);
    	return ok(views.html.createNewQuote.render(form));
    }
    
    public static Result saveQuote() {
    	Form<Quote> form = Form.form(Quote.class).bindFromRequest();
    	if (form.hasErrors()) {
    		return badRequest(views.html.createNewQuote.render(form));
    							//display the form with errorr
    	} else {
    		Quote quote = form.get();
    		quote.save();
    		flash("success", String.format("New quote %s has been created", quote.name));
    		return play.mvc.Controller.redirect(controllers.routes.Application.createNewQuoteAction());
    	}
    }

    public static Result getQuotes() {
    	List<Quote> quotes = new Model.Finder(String.class, Quote.class).all();
    	return ok(Json.toJson(quotes));
    }
    
    public static Result cpage4() {
    	Integer x = rand.nextInt(10);
    	return ok(views.html.vpage4.render(x));
    }
    
    final static Form<UserMessage> userMess = Form.form(UserMessage.class); 
    
    public static Result cpage5() {
    	return ok(views.html.vpage5.render(userMess));
    }
    
    public static Result showMess() {
    	Form <UserMessage> messFiled = userMess.bindFromRequest();
    	UserMessage filed = messFiled.get();
    	return ok(views.html.vpage5disp.render(filed));
    }
    

}
