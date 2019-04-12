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
		<br><br>
		<h:outputLabel value="Justification"/>
		<h:inputFile value="#{ajoutAbsence.justification}" />
		
		<br><br>
		<h:commandButton value="Enregistrer" action="#{ajoutAbsence.AddAbsence()}" />
		&nbsp;&nbsp;&nbsp;
		<%-- <h:commandButton value="Annuler" action="#{ }" /> --%>
		<h:commandButton value="tester" actionListener="#{ajoutAbsence.tester}"></h:commandButton>
</fieldset>		
</h:form>
<h:form enctype="multipart/form-data">
	<h:inputFile value="#{ajoutAbsence.justification}" />
	<h:commandButton value="tester" actionListener="#{ajoutAbsence.tester}"></h:commandButton>
</h:form>
</f:view>
</body>
</html>