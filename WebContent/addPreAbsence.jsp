<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE html>
<html>
<head>
<link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Ajout d'absence préjustifier</title>
</head>
<body>
<div class="navbar navbar-expand-lg navbar-light bg-light">
<a class="navbar-brand" href="#" style="display: ruby;">
    <img src="assets/img/index.png" width="40" height="40" class="d-inline-block align-top" alt="" />
    Gestion d'absence et demande d'absence
  </a>
  <div class="collapse navbar-collapse ml-5" id="navbarNav" style="float:right;">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="consultation.jsp">Consulter absence<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="addAbsence.jsp">Ajouter absence <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="addPreAbsence.jsp">Ajouter absence préjustifier <span class="sr-only">(current)</span></a>
      </li>
    </ul>
  </div>
</div>
<f:view>
	
	<div class="container" style="margin-top: 25px;">
		<div class="card text-center">
			<div class="card-header">
				<h:form>
					<h3 class="float-left">Gestions des absences</h3>
				</h:form>
			</div>
  			<div class="card-body">
				<h:form enctype="multipart/form-data">
					<h:outputLabel for="StudentsSelection" value="Etudiant : "></h:outputLabel>&nbsp;
					<h:selectOneMenu id="StudentsSelection" value="#{ajoutPreAbsence.id_etudiant}">
						<f:selectItems value="#{ajoutPreAbsence.etudiants}"/>
					</h:selectOneMenu>
					<br>
					<h:outputLabel for="startDateInput" value="Date / horaire début : "></h:outputLabel>&nbsp;
					<h:inputText id="startDateInput" value="#{ajoutPreAbsence.startDate}" title="ex : 18-03-2019"  style="width: 6rem;">
						<f:convertDateTime type="date" pattern="dd-MM-yyyy" />
					</h:inputText>&nbsp;
					<h:inputText style="width: 3rem;" title="ex : 00:00" value="#{ajoutPreAbsence.startTime}">
						<f:convertDateTime pattern = "HH:mm" />
					</h:inputText><br>
					<h:outputLabel for="endDateInput" value="Date / horaire fin: "></h:outputLabel>&nbsp;
					<h:inputText id="endDateInput" title="ex : 18-03-2019" style="width: 6rem;" value="#{ajoutPreAbsence.endDate}">
						<f:convertDateTime pattern = "dd-MM-yyyy" timeZone="GMT+1" />
					</h:inputText>&nbsp;
					<h:inputText style="width: 3rem;" title="ex : 00:00" value="#{ajoutPreAbsence.endTime}">
						<f:convertDateTime pattern = "HH:mm" />
					</h:inputText><br>
					<h:outputLabel for="acceptation" value="Acceptation : "></h:outputLabel>&nbsp;
					<h:selectOneMenu id="acceptation" value="#{ajoutPreAbsence.acceptation}">
						<f:selectItem itemValue="oui" itemLabel="Acceptée"/>
						<f:selectItem itemValue="non" itemLabel="Non acceptée"/>
					</h:selectOneMenu><br>
					<h:outputLabel for="ImportPieceJoint" value="Justification : "></h:outputLabel>&nbsp;
					<h:inputFile id="ImportPieceJoint" value="#{ajoutPreAbsence.justification}" /><br><br>
					
					<h:commandButton styleClass="btn btn-primary" value="Valider" actionListener="#{ajoutPreAbsence.AddAbsence}"></h:commandButton>&nbsp;&nbsp;
					<h:commandButton styleClass="btn btn-secondary" value="Effacer" actionListener="#{ajoutPreAbsence.clear}"></h:commandButton>
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