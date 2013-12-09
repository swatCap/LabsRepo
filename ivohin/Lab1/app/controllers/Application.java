package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() 
    {
        return ok(index.render("asdasd"));
    }
    
    
    public static Result perform(String sportType) 
    {
        return ok(index.render("asdasd"));
    }
       

}
