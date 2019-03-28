package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="manager")
@RequestScoped
public class BeansManager {
	private String name = "KDragon";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
