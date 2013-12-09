package controllers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

	private static String SPORT_TYPE_AUTOMOTO 		    = "automoto";
	private static String SPORT_TYPE_AMER_FOOTBALLL     = "amerFootball";
	private static String SPORT_TYPE_FOOTBALL 	        = "football";
	private static String SPORT_TYPE_FOOTSAL 		    = "footsal";
	private static String SPORT_TYPE_BASKET 			= "basket";
	private static String SPORT_TYPE_VOLLEY 			= "volley";
	private static String SPORT_TYPE_TENNIS 			= "tennis";
	private static String SPORT_TYPE_HOCKEY 			= "hockey";
	
	
	private static String SPORT_NAME_AUTOMOTO 		    = "Авто-мотоспорт";
	private static String SPORT_NAME_AMER_FOOTBALLL     = "Американський футбол";
	private static String SPORT_NAME_FOOTBALL 	        = "Баскетбол";
	private static String SPORT_NAME_FOOTSAL 		    = "Футзал";
	private static String SPORT_NAME_BASKET 			= "Баскетбол";
	private static String SPORT_NAME_VOLLEY 			= "Волейбол";
	private static String SPORT_NAME_TENNIS 			= "Тенic";
	private static String SPORT_NAME_HOCKEY 			= "Хокей";
	
	
	
    public static Result index() 
    {
        return ok(index.render("asdasd"));
    }
    
    
    public static Result perform(String sportType) 
    {
    	Document doc;
    	try 
    	{
    		String content;
    		String sportName = "";
    		
    		if (sportType.equalsIgnoreCase(SPORT_TYPE_AUTOMOTO))
    		{
    			sportName = SPORT_NAME_AUTOMOTO;
    			doc = Jsoup.connect("http://www.parimatch.com/bet.html?hd=1660486%2C1660487").get();
    		}
    		else if (sportType.equalsIgnoreCase(SPORT_TYPE_AMER_FOOTBALLL))
    		{
    			sportName = SPORT_NAME_AMER_FOOTBALLL;
    			doc = Jsoup.connect("http://www.parimatch.com/bet.html?hd=1660559").get();
    		}
    		else if (sportType.equalsIgnoreCase(SPORT_TYPE_FOOTBALL))
    		{
    			sportName = SPORT_NAME_FOOTBALL;
    		}
    		else if (sportType.equalsIgnoreCase(SPORT_TYPE_FOOTSAL))
    		{
    			sportName = SPORT_NAME_FOOTSAL;
    		}
    		else if (sportType.equalsIgnoreCase(SPORT_TYPE_BASKET))
    		{
    			sportName = SPORT_NAME_BASKET;
    		}
    		else if (sportType.equalsIgnoreCase(SPORT_TYPE_VOLLEY))
    		{
    			sportName = SPORT_NAME_VOLLEY;
    		}
    		else if (sportType.equalsIgnoreCase(SPORT_TYPE_TENNIS))
    		{
    			sportName = SPORT_NAME_TENNIS;
    		}
    		else if (sportType.equalsIgnoreCase(SPORT_TYPE_HOCKEY))
    		{
    			sportName = SPORT_NAME_HOCKEY;
    		}
    			    	
    	}
    	catch (Exception e)
    	{
    		return ok(sport.render("Помилка!", "Виникла помилка при обробцi запиту! " + e.getMessage()));
    	}
    	
        return ok(index.render("asdasd"));
    }
       

}
