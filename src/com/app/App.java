package com.app;

import com.common.EventPublisher;
import com.common.SourceData;
import com.event.source.DataExtracted;
import com.event.source.Event;
import com.extract.Extracter;
import com.extract.ExtracterImpl;

public class App {
	
	   public static void main(String args[]) {
		
		   // Testing
		Extracter extracter = new ExtracterImpl();
		// Create the Extractor-Event;
		//https://github.com/djarza/football-events/blob/master/football-match/src/main/java/org/djar/football/match/controller/MatchCommandController.java
		// To be replaced by controlleR:
		
		Event event = new DataExtracted();
		// hardcoded...
		EventPublisher eventPublisher = new EventPublisher(null, null, 0);
		
		eventPublisher.fire(event);
				
		
		
		
        SourceData sourceData = new SourceData();
		//extracter.startEtractor(sourceData);
		
		System.out.println(" ELT has ended................");
	}

}
