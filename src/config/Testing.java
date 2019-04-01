package config;

import dao.AbsencesManagerDAO;
import entities.Absence;

public class Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Absence absence = new Absence();
		absence.setTitle("title1");
		AbsencesManagerDAO.addAbsence(absence);
	}

}
