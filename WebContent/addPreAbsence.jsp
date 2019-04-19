<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Ajout d'absence préjustifier</title>
</head>
<body>
<f:view>
	<img src="assets/img/index.png" />
	<div class="container" style="margin-top: 25px;">
		<div class="card">
			<div class="card-header">
				<h:form>
					<h3 class="float-left">Gestions des absences</h3>
				</h:form>
			</div>
  			<div class="card-body">
				<h:outputLabel for="StudentsSelection" value="Etudiant : "></h:outputLabel>&nbsp;
				<h:selectOneMenu id="StudentsSelection">
					<f:selectItems/>
				</h:selectOneMenu>
				<br>
				<h:outputLabel for="startDateInput" value="Date / horaire début : "></h:outputLabel>&nbsp;
				<h:inputText id="startDateInput" title="ex : 18-03-2019"  style="width: 4rem;">
					<f:convertDateTime pattern = "dd-MM-YYYY" />
				</h:inputText>&nbsp;
				<h:inputText style="width: 4rem;" title="ex : 00:00">
					<f:convertDateTime pattern = "HH:mm" />
				</h:inputText><br>
				<h:outputLabel for="endDateInput" value="Date / horaire fin: ">
					<f:convertDateTime pattern = "dd-MM-YYYY" />
				</h:outputLabel>&nbsp;
				<h:inputText id="endDateInput" title="ex : 18-03-2019" style="width: 4rem;">
					<f:convertDateTime pattern = "HH:mm" />
				</h:inputText>&nbsp;
				<h:inputText style="width: 4rem;" title="ex : 00:00"></h:inputText><br>
				<h:outputLabel for="ImportPieceJoint" value="Justification : "></h:outputLabel>&nbsp;
				<h:inputFile id="ImportPieceJoint" /><br><br>
				<h:commandButton value="Valider"></h:commandButton>&nbsp;&nbsp;
				<h:commandButton value="Effacer"></h:commandButton>
  			</div>
		</div>
	</div>
</f:view>
<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.js"></script>
<script type="text/javascript" src="assets/js/popper.js"></script>
</body>
</html>