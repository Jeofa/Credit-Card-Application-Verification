package com.bank.app.service.Office;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bank.app.entity.Office.Clients;
import com.bank.app.repository.Office.ClientReview;
import com.bank.app.repository.Office.ClientsRepository;

@Service	
public class ClientService {
	
	@Autowired
	private ClientsRepository clientRepo;

	public String getAllClient( Clients clients, Model model) {
		model.addAttribute("allclient", clientRepo.findAll());
		return "Office/clients";
	}
	public String getFullDetails(int cid, Clients clients, Model model) {
		ArrayList<ClientReview> values = (ArrayList<ClientReview>) clientRepo.getApplicant(cid);
		System.out.println(":::::::::::::::\nHere is the name\n:::::::::::::::::"+values.get(0).getName());
		ReviewData mydata = new ReviewData(values.get(0).getName(),values.get(0).getPid(),values.get(0).getPhone(),values.get(0).getEmail(),values.get(0).getDob(),values.get(0).getGender(),values.get(0).getCounty(),values.get(0).getBox(),values.get(0).getEmployment(),values.get(0).getSalary(),values.get(0).getBankNumber(),values.get(0).getBank(),values.get(0).getImagename());
		model.addAttribute("clientdata", mydata);
		return "Office/clientsreview";
	}
	public void updateClient(int id, Model model) {
		Optional<Clients> client = clientRepo.findById(id);
		model.addAttribute("clientinfo", client);
	}
	public void deleteClient(int id, Model model) {
		clientRepo.deleteByIdentity(id);
	}
	
	

}
