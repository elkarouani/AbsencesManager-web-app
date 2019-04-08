<%@ page language="java" contentType="text/html; charset=windows-1256" pageEncoding="windows-1256"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Consultation</title>
</head>
<body>
<f:view>
	<div class="container" style="margin-top: 25px;">
		<h:panelGroup rendered="#{consultation.studentNotFound}">
			<div class="alert alert-warning text-center" role="alert">
  				l'étudiant n'est pas existe
			</div>
		</h:panelGroup>
		<div class="card">
			<div class="card-header">
				<h:form>
					<h3 class="float-left">Gestions des absences</h3>
					<span class="float-right">
						<h:commandLink styleClass="btn btn-primary">
							<i class="fas fa-plus"></i> nouvelle absence
						</h:commandLink>
					</span>
				</h:form>
			</div>
  			<div class="card-body">
    			<div class="row">
    				<div class="col">
    					<h:form>
    						<h:outputLabel value="étudiant : "></h:outputLabel>&emsp;
	    					<h:inputText value="#{consultation.student_name}"></h:inputText>&emsp;
	    					<h:commandLink styleClass="btn btn-info" actionListener="#{consultation.findStudent}">
	    						<i class="fas fa-search"></i> Rechercher
	    					</h:commandLink>
    					</h:form>
    				</div>
    			</div>
    			<div class="row">
    				<div class="col">
						<h:form>
							<h:dataTable styleClass="table">
								<h:column>
									<f:facet name="header">Date</f:facet>
									<h:outputText>------</h:outputText>
								</h:column>						
								<h:column>
									<f:facet name="header">Horaire</f:facet>
									<h:outputText>------</h:outputText>
								</h:column>
								<h:column>
									<f:facet name="header">Seance</f:facet>
									<h:outputText>------</h:outputText>
								</h:column>
								<h:column>
									<f:facet name="header">Remarque</f:facet>
									<h:outputText>------</h:outputText>
								</h:column>
								<h:column>
									<f:facet name="header">Actions</f:facet>
									<h:outputText>------</h:outputText>
								</h:column>
							</h:dataTable>
						</h:form>
    				</div>
    			</div>
    			<div class="row">
    				<div class="col">
    					<h:form>
    						<h:outputLabel value="Nombre d'absence total : "></h:outputLabel>&emsp;
	    					<h:inputText></h:inputText>&emsp;
	    					<h:commandLink styleClass="btn btn-info">
	    						<i class="fas fa-print"></i> Imprimer bilan
	    					</h:commandLink>
    					</h:form>
    				</div>
    			</div>
  			</div>
		</div>
	</div>
</f:view>
<script src="https://unpkg.com/ionicons@4.5.5/dist/ionicons.js"></script>
<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.js"></script>
<script type="text/javascript" src="assets/js/popper.js"></script>
</body>
</html>