package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import models.Bar;
import models.Entry;
import play.*;
import play.data.Form;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.*;
import services.BarService;

public class Application extends Controller {
	

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
    	return ok(
    	    objectRenderer.render(entries)
    	);
    }
    
    public static Result cpage3() {
        return ok(views.html.vpage3.render("plop"));
    }
    
    public static Result addBar() {
        Form<Bar> form = Form.form(Bar.class).bindFromRequest();
        Bar bar = form.get();
        bar.save();
        return play.mvc.Controller.redirect(controllers.routes.Application.cpage3());
    }

    public static Result getBars() {
    	List<Bar> bars = new Model.Finder(String.class, Bar.class).all();
    	return ok(Json.toJson(bars));
    }

}
