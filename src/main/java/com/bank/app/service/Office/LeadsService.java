package com.bank.app.service.Office;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bank.app.DTO.EmployersEmail;
import com.bank.app.DTO.GradClient;
import com.bank.app.entity.Office.Clients;
import com.bank.app.entity.Office.Leads;
import com.bank.app.model.LeadsUpdate;
import com.bank.app.repository.Office.ClientReview;
import com.bank.app.repository.Office.ClientsRepository;
import com.bank.app.repository.Office.LeadsRepository;
import com.bank.app.repository.Office.PersonData;
import com.bank.app.service.Email.EmailsHandler;

@Service
public class LeadsService {

	@Autowired
	private LeadsRepository leadsRepo;

	@Autowired
	private ClientsRepository clientRepo;

	@Autowired
	private EmailsHandler emailHandler;

	public String getAllLeads(Leads leads, Model model) {
		LeadsUpdate update = new LeadsUpdate();

//		get a search for employers email
		
		List<PersonData> listLeads = leadsRepo.getPersonData();

		ListIterator<PersonData> itrLeads = listLeads.listIterator();
		while (itrLeads.hasNext()) {
			int tracker = 0;
			String employersEmail;
			int employeeId;
			PersonData data = itrLeads.next();
			employersEmail = data.getEmployersEmail();
			employeeId = data.getPid();
			

//			check if the lead is already a client
			List<EmployersEmail> checker = clientRepo.checkIfExists(employeeId);
			System.out.println("Checker " + checker.get(tracker).getEmployersEmail());
			System.out.println("Leads "+employersEmail);
			boolean d =(employersEmail != checker.get(tracker).getEmployersEmail());
			System.out.println("Is it? "+d);
			System.out.println("New try:::"+ employersEmail.compareTo(checker.get(tracker).getEmployersEmail()));
			if (employersEmail != checker.get(tracker).getEmployersEmail()) {

				System.out.println("Leads Email: " + employersEmail);

				HashMap<String, String> hm = emailHandler.getGmailData("subject:Re:Credit Card Employer Confirmation!");
//			        this is to check if the received email is from a known employer
				String from = hm.get("from");

				if (from.contains(employersEmail)) {
					String body = hm.get("body");
					if (body.contains("exists")) {
//			             	means that the employee is there :: graduate employee
//			              query employee by employer email and leads id
//			            	 set status to 

						List<GradClient> obj = clientRepo.graduateLeadToClient(employersEmail);
						try {
//			            		String email
							Clients client = new Clients(obj.get(0).getName(), obj.get(0).getEmail(),
									obj.get(0).getPhone(), obj.get(0).getSalary(), obj.get(0).getIdentity(),
									obj.get(0).getEmployment(), obj.get(0).getLocation());
							String das = clientRepo.ConfirmEmail(obj.get(0).getEmail());
							System.out.println(das);
							if(Integer.valueOf(das) <= 0 ) {
								clientRepo.save(client);
							}
							break;
						} catch (Exception e) {
							System.out.println(e);
						}

					} else {
//			             	means that the employee is not around
//			            	 set status to declined
						System.out.println("Error on the email");
//			            	 leadsRepo.updateById("declined",employeeId);

					}
				} else {
					System.out.println("From another source: " + hm.get("from"));
				}
			} else {
				System.out.println("already a client");
			}
			tracker++;
		}

		model.addAttribute("allleads", leadsRepo.getPersonData());
		model.addAttribute("updator", update);
		return "Office/leads";
	}

	public String getFullDetails(int cid, Leads leads, Model model) {
		ArrayList<ClientReview> values = (ArrayList<ClientReview>) leadsRepo.getApplicant(cid);
//		values.get(0).getImagename();
		System.out.println(":::::::::::::::\nHere is the name\n:::::::::::::::::" + values.get(0).getName());
		ReviewData mydata = new ReviewData(values.get(0).getName(), values.get(0).getPid(), values.get(0).getPhone(),
				values.get(0).getEmail(), values.get(0).getDob(), values.get(0).getGender(), values.get(0).getCounty(),
				values.get(0).getBox(), values.get(0).getEmployment(), values.get(0).getSalary(),
				values.get(0).getBankNumber(), values.get(0).getBank(), values.get(0).getImagename());
		model.addAttribute("leadsdata", mydata);
		return "Office/leadsreview";
	}

	public void updateLeads(LeadsUpdate leads, Model model) {
		System.out.println("Status " + leads.getStatus() + " Identity " + leads.getIdentity());
		leadsRepo.updateById(leads.getStatus(), leads.getIdentity());
	}

	public void deleteClient(int id, Model model) {
		leadsRepo.deleteById(id);
	}

}
