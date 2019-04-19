<%@ page language="java" contentType="text/html; charset=windows-1256" pageEncoding="windows-1256"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Ajout absence</title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajout Absence</title>
</head>
<body>
<div class="navbar navbar-expand-lg navbar-light bg-light">
<a class="navbar-brand" href="#" style="display: ruby;">
    <img src="assets/img/index.png" width="40" height="40" class="d-inline-block align-top" alt="" />
    Gestion d'absence et demande d'absence
  </a>
  <div class="collapse navbar-collapse ml-5" id="navbarNav" style="float:right;">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="consultation.jsp">Consulter absence<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="addAbsence.jsp">Ajouter absence <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="addPreAbsence.jsp">Ajouter absence préjustifier <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="notification.jsp">Notification<span class="sr-only">(current)</span></a> 
      </li>

    </ul>
  </div>
</div>
<f:view>
<div class="container" style="margin-top: 25px;">
		<div class="card">
			<div class="card-header">
				<h:form>
					<h3 class="float-left">Gestions des absences</h3>
				</h:form>
			</div>
<div class="card-body">
	<h:form>
		<h:outputLabel value="Etudiant :"/>
		<h:selectOneMenu value="#{ajoutAbsence.id_etudiant}">&nbsp;
			<f:selectItems value="#{ajoutAbsence.etudiants}"/>
		</h:selectOneMenu>
		<br><br>
		<h:outputLabel value="Seance :"/>
		<h:selectOneMenu value="#{ajoutAbsence.id_seance}">&nbsp;
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
</h:form>
</div>
</div>
</div>
</f:view>
<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.js"></script>
<script type="text/javascript" src="assets/js/popper.js"></script>
</body>
</html>