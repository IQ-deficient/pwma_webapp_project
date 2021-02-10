package pwma.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pwma.model.Item;
import pwma.model.Member;
import pwma.service.ItemService;
import pwma.service.MemberService;

//@Model is a convenience mechanism to make this a request-scoped bean
//allows us to access the class bean through its name on xhtml page
@Named
// defines a state from the beginning of a request until the response has been sent to the client
@ViewScoped
public class MemberView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private MemberService memberService;

	@Inject
	private ItemService itemService;

	// producer method acts as a source of objects to be injected
	@Produces
	// gives access to the bean by using the bean name, with the first letter in lower-case
	@Named
	private Member newMember;

	private List<Member> members;

	private List<Item> items;

	private String searchString;

	private Member targetMember;

	private Item item;

	@PostConstruct
	public void initNewMember() {
		newMember = new Member();
		members = memberService.getAllMembers();
		items = itemService.getAllItems();
	}

	public void addItem() {
		try {
			targetMember.getItems().add(item);
			item.setMember(targetMember);
			memberService.register(targetMember);
//			members = memberService.getAllMembers();
			// TODO update items with queries?
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public void addItem() {
	// try {
	// itemService.insert(item);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	public void findByName() {
		if (searchString.isEmpty()) {
			items = itemService.getAllItems();
		} else {
			items = itemService.getByName(searchString);
		}
	}

	public void register() throws Exception {
		try {
			// submitted form data is sent to service class that works with database
			memberService.register(newMember);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
			facesContext.addMessage(null, m);
			initNewMember();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
		}
	}

	public Member getNewMember() {
		return newMember;
	}

	public void setNewMember(Member newMember) {
		this.newMember = newMember;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public Member getTargetMember() {
		return targetMember;
	}

	public void setTargetMember(Member targetMember) {
		this.targetMember = targetMember;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Registration failed. See server log for more information";
		if (e == null) {
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}
		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null) {
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// This is the root cause message
		return errorMessage;
	}

}
