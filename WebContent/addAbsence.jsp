<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajout Absence</title>
</head>
<body>
<f:view>
<h:form enctype="multipart/form-data">
	<fieldset>
		<legend>Ajouter Absence</legend>
		<h:outputLabel value="Etudiant"/>
		<h:selectOneMenu value="#{ajoutAbsence.id_etudiant}">
			<f:selectItems value="#{ajoutAbsence.etudiants}"/>
		</h:selectOneMenu>
		<br><br>
		<h:outputLabel value="Seance"/>
		<h:selectOneMenu value="#{ajoutAbsence.id_seance}">
			<f:selectItems value="#{ajoutAbsence.seances}"/>
		</h:selectOneMenu>
		<!-- <br><br> -->
		<%-- <h:outputLabel value="Justification"/>
		<h:inputFile value="#{ajoutAbsence.justification}" /> --%>
		<br><br>
		<h:selectOneRadio value = "#{ajoutAbsence.remarque}"> 
   			<f:selectItem itemValue = "A" itemLabel = "Absent" /> 
   			<f:selectItem itemValue = "P" itemLabel = "Présent" />
   			<f:selectItem itemValue = "E" itemLabel = "Excusée" />
   			<f:selectItem itemValue = "R" itemLabel = "Retard" />
		</h:selectOneRadio>
		<br><br> 
		<h:commandButton value="Enregistrer" actionListener="#{ajoutAbsence.AddAbsence}" />
		&nbsp;&nbsp;&nbsp;
		<h:commandButton value="Annuler" actionListener="#{ajoutAbsence.clear}" />
	</fieldset>		
</h:form>
</f:view>
</body>
</html>