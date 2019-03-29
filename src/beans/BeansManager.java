package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name="manager")
@RequestScoped
public class BeansManager {
	private String name = "KDragon";

	public void add(ActionEvent event) {
		System.out.println("clicked");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
