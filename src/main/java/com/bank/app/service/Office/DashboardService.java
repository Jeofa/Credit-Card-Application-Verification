package com.bank.app.service.Office;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bank.app.model.CardSorted;
import com.bank.app.model.DashboardModel;
import com.bank.app.repository.Office.ClientsRepository;
import com.bank.app.repository.Users.CardRepository;

@Service
public class DashboardService {
	
	@Autowired
	public ClientsRepository clientRepo;
	
	@Autowired
	public CardRepository cardRepo;
	
	public String getDashboardDisplay(Model model, DashboardModel dashboard) {
		String clientno = clientRepo.getNoOfClient();
		String rejects = clientRepo.rejectedApp();
		int diff = Integer.valueOf(clientno) - Integer.valueOf(rejects);
		float results = ((diff/Integer.valueOf(clientno)) );
		
		List<CardSort> cardSort = cardRepo.getCardsData();
		CardSorted sd = new CardSorted();
		sd.setCardName(cardSort.get(0).getcardName());
		sd.setNoOfCards(cardSort.get(0).getnoOfCards());
		
		DashboardModel dashboardHandle = new DashboardModel();
//		dashboardHandle.setCards(cardRepo.findAll());
		
		dashboardHandle.setApplicationRate(results);
		dashboardHandle.setClientNo(Integer.valueOf(clientno));
		dashboardHandle.setRejectedApps(Integer.valueOf(rejects));
		
		model.addAttribute("dashboardData", dashboardHandle);
//		model.addAttribute("dashboardDataCard", cardSort);
		
		return "Office/dashboard";
		
		
	}

}
