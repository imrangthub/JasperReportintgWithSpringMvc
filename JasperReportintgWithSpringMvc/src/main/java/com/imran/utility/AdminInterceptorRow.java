package com.imran.utility;

import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminInterceptorRow extends HandlerInterceptorAdapter {
	
	private int maintenanceStartTime;
	private int maintenanceEndTime;
	private String maintenanceMapping = "redirect:/";

	public void setMaintenanceMapping(String maintenanceMapping) {
		this.maintenanceMapping = maintenanceMapping;
	}

	public void setMaintenanceStartTime(int maintenanceStartTime) {
		this.maintenanceStartTime = maintenanceStartTime;
	}

	public void setMaintenanceEndTime(int maintenanceEndTime) {
		this.maintenanceEndTime = maintenanceEndTime;
	}

	
	
   // Called before handler method invocation
   @Override
   public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
         Object handler) throws Exception {
      System.out.println("From admin preHFanfdelar : Called before handler method");
      req.setAttribute("fname", "Elizabeth");
      LocalTime time = LocalTime.now();
      int hrs = time.getHour();
      if (hrs >= 10 && hrs <= 12) {
    	  return true; 
      }else{
          res.sendRedirect("redirect:/");
          return false; 
      }      
   }

   @Override
   public void postHandle(HttpServletRequest req, HttpServletResponse res,
         Object handler, ModelAndView model)  throws Exception {

      System.out.println("Called after handler method request completion,"
            + " before rendering the view");

      LocalTime time = LocalTime.now();
      int hrs = time.getHour();
      if (hrs >= 0 && hrs <= 12) {
    	  System.out.println("Good morning!");
         model.addObject("greeting", "Good morning!");
      } else if (hrs > 12 && hrs <= 17) {
    	  System.out.println("Good afternoon!");
         model.addObject("greeting", "Good afternoon!");
      } else {
    	  System.out.println("greeting Good evening!");
         model.addObject("greeting", "Good evening!");
      }
   }
}
