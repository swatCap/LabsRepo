package controllers;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    private static String SITE_TO_PARSE 		    = "http://www.parimatch.com";
    
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

private static List<String> getAllTournamentsLinks(Document doc)
{
	if (doc != null)
	{
        Element div = doc.getElementById("z_contentw");                                                             

        Elements tournamentNodes = div != null ? div.select("a[href]") : null;
        if (tournamentNodes != null && !tournamentNodes.isEmpty())
        {
            List<String> res = new ArrayList();
            for (Element dataNode : tournamentNodes)
            {                
                String link = dataNode != null ? dataNode.attr("href") : null;
                if (link != null && !link.isEmpty())
                {
                    res.add(link);
                }
            }

            return res;
        }
	}
	
	return null;
}

private static void remove(Elements e)
{
    if (e != null && !e.isEmpty())
    {
        for (Element img : e)
        {
            img.remove();
        }
    }
}

private static String parseTournamentTable(Document tournamentDoc)
{
     if (tournamentDoc != null)
     {
         Elements formElems = tournamentDoc.getElementsByAttributeValue("name", "f1");
         
         Element form = (formElems != null && !formElems.isEmpty()) ? formElems.first() : null;
         
         if (form != null)
         {
            Elements imgs = form.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
            remove(imgs);
            
            Elements buttons = form.getElementsByClass("buttons");
            remove(buttons);
            
            Elements seocont = form.getElementsByClass("seocont");
            remove(seocont);
            
            Elements bt = form.getElementsByAttributeValue("name", "bt");
            remove(bt);
            
            
            String tournamentTableHtmlCode = form.toString();
            
            tournamentTableHtmlCode = tournamentTableHtmlCode.replaceAll("\\d{1,2}\\.\\d{1,2}", "$0 ");// "$0<br />");
            tournamentTableHtmlCode = tournamentTableHtmlCode.replaceAll("<br />", " - ");
            return tournamentTableHtmlCode;
         }                                       
     }
     
     return null;
}

public static Result perform(String sportType) 
{
	Document doc = null;
	String content = "<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" />";// + 
				//	" <!DOCTYPE html> <html>  <head>sdsd </head><body>";
	String sportName = "";
	
	try 
	{
		
            List<String> allTournamentsLinks;
		
		if (sportType.equalsIgnoreCase(SPORT_TYPE_AUTOMOTO))
		{
			sportName = SPORT_NAME_AUTOMOTO;
			doc = Jsoup.connect(SITE_TO_PARSE + "/sport/avto-motosport").get();    			
		}
		else if (sportType.equalsIgnoreCase(SPORT_TYPE_AMER_FOOTBALLL))
		{
			sportName = SPORT_NAME_AMER_FOOTBALLL;
			doc = Jsoup.connect(SITE_TO_PARSE + "/sport/amerikanskijj-futbol").get();
		}
		else if (sportType.equalsIgnoreCase(SPORT_TYPE_FOOTBALL))
		{
			sportName = SPORT_NAME_FOOTBALL;
                    doc = Jsoup.connect(SITE_TO_PARSE + "/sport/futbol").get();
		}
		else if (sportType.equalsIgnoreCase(SPORT_TYPE_FOOTSAL))
		{
			sportName = SPORT_NAME_FOOTSAL;
                    doc = Jsoup.connect(SITE_TO_PARSE + "/sport/futzal").get();
		}
		else if (sportType.equalsIgnoreCase(SPORT_TYPE_BASKET))
		{
			sportName = SPORT_NAME_BASKET;
                    doc = Jsoup.connect(SITE_TO_PARSE + "/sport/basketbol").get();
		}
		else if (sportType.equalsIgnoreCase(SPORT_TYPE_VOLLEY))
		{
			sportName = SPORT_NAME_VOLLEY;
                    doc = Jsoup.connect(SITE_TO_PARSE + "/sport/volejjbol").get();
		}
		else if (sportType.equalsIgnoreCase(SPORT_TYPE_TENNIS))
		{
			sportName = SPORT_NAME_TENNIS;
                    doc = Jsoup.connect(SITE_TO_PARSE + "/sport/tennis").get();
		}
		else if (sportType.equalsIgnoreCase(SPORT_TYPE_HOCKEY))
		{
			sportName = SPORT_NAME_HOCKEY;
                    doc = Jsoup.connect(SITE_TO_PARSE + "/sport/khokkejj").get();
		}
            
            allTournamentsLinks = getAllTournamentsLinks(doc);
		
            String parsedHtml = "";
            
            if (allTournamentsLinks != null && !allTournamentsLinks.isEmpty())
            {
                for (String tournamentLink : allTournamentsLinks)
                {
                    try
                    {
                        Document tournamentDoc = Jsoup.connect(SITE_TO_PARSE + tournamentLink).userAgent("Mozilla").timeout(10000).get(); 

                        String parsedTable = parseTournamentTable(tournamentDoc);
                        if (parsedTable != null && !parsedTable.isEmpty())
                        {                                                        
                            parsedHtml += parsedTable;
                        }

                        //Thread.sleep(500);
                    }
                    catch (Exception ignore) 
                    { 
                        System.out.println("was error");
                    }
                }
            }
            
            
            content += parsedHtml;
	}
	catch (Exception e)
	{
        e.printStackTrace();
    }
	//content += " </body> </html>";
	return ok(sport.render(sportName, content));
}
    
public static Result index() 
{
    return ok(index.render(null));
}

}
