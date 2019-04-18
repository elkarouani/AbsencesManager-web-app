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
	<img src="assets/img/index.png" />
	<div class="container" style="margin-top: 25px;">
		<h:panelGroup rendered="#{consultation.studentNotFound == true}">
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
    						<h:outputLabel value="Nom d'étudiant : "></h:outputLabel>&emsp;
	    					<h:inputText value="#{consultation.student_name}"></h:inputText>&emsp;
	    					<h:commandLink styleClass="btn btn-info" actionListener="#{consultation.findStudent}">
	    						<i class="fas fa-search"></i> Rechercher
	    					</h:commandLink>
    					</h:form>
    				</div>
    				<h:panelGroup rendered="#{consultation.displayTable == true}">
	    				<div class="col-5">
	    					<h:form>
	    						<h:outputLabel value="Filtrer par remarque : "></h:outputLabel>&emsp;
		    					<h:selectOneMenu value="#{consultation.filteredRemarque}">
									<f:selectItem itemValue = "#{null}" itemLabel = "-----" /> 
									<f:selectItem itemValue = "R" itemLabel = "R" />
									<f:selectItem itemValue = "E" itemLabel = "E" />
									<f:selectItem itemValue = "A" itemLabel = "A" /> 
								</h:selectOneMenu>
		    					&emsp;
		    					<h:commandLink styleClass="btn btn-info" actionListener="#{consultation.filterByRemarque}">
		    						<i class="fas fa-filter"></i> Filtrer
		    					</h:commandLink>
	    					</h:form>
	    				</div>
    				</h:panelGroup>
    			</div>
    			<br><br>
    			<h:panelGroup rendered="#{consultation.displayTable == true}">
    				<div class="row text-center">
    					<div class="col">
							<h:form>
								<h:dataTable styleClass="table" value="#{consultation.absences}" var="absence">
									<h:column>
										<f:facet name="header"><h:outputText value="Date"/></f:facet>
										<h:inputText binding="#{consultation.dateInput}" value="#{absence.seance.date_horaire}" readonly="true">
											<f:convertDateTime pattern = "dd-MM-YYYY" />
										</h:inputText>
									</h:column>
									<h:column>
										<f:facet name="header"><h:outputText value="Horaire"/></f:facet>
										<h:inputText value="#{absence.seance.date_horaire}" readonly="true">
											<f:convertDateTime pattern = "HH:mm" />
										</h:inputText>
									</h:column>
									<h:column>
										<f:facet name="header"><h:outputText value="Seance"/></f:facet>
										<h:selectOneMenu onchange="changed(this);" value="#{absence.seance.id}">
											<f:selectItems value="#{consultation.seances}"/>
										</h:selectOneMenu>
										<h:inputHidden binding="#{consultation.seanceInput}"></h:inputHidden>
									</h:column>
									<h:column>
										<f:facet name="header"><h:outputText value="Enseignant"/></f:facet>
										<h:inputText value="#{absence.seance.enseignant.nom} #{absence.seance.enseignant.prenom}" readonly="true"></h:inputText>
									</h:column>
									<h:column>
										<f:facet name="header"><h:outputText value="Remarque"/></f:facet>
										<h:selectOneMenu binding="#{consultation.remarqueInput}" value="#{absence.remarque}">
											<f:selectItem itemValue = "#{null}" itemLabel = "-----" /> 
	   										<f:selectItem itemValue = "R" itemLabel = "R" />
	   										<f:selectItem itemValue = "E" itemLabel = "E" />
	   										<f:selectItem itemValue = "A" itemLabel = "A" /> 
										</h:selectOneMenu>
									</h:column>
									<h:column>
										<f:facet name="header"><h:outputText value="Actions"/></f:facet>
										<h:commandLink styleClass="btn btn-warning" actionListener="#{consultation.modifyAbsence}" onclick="return confirm('Vous voulez vraiment modifier cette observation ?');" >
											<i class="fas fa-pen"></i>
											<f:param name="id" value="#{absence.id}"></f:param>
										</h:commandLink><br>
										<h:commandLink styleClass="btn btn-danger" actionListener="#{consultation.deleteAbsence}" onclick="return confirm('Vous voulez vraiment supprimer cette observation ?');">
											<i class="fas fa-trash"></i>
											<f:param name="id" value="#{absence.id}"></f:param>
										</h:commandLink>
									</h:column>
								</h:dataTable>
							</h:form>
	    				</div>
	    			</div>
	    		</h:panelGroup>
    			<h:panelGroup rendered="#{consultation.studentNotFound == false}">
					<div class="row">
	    				<div class="col">
    						<h:outputLabel value="Nombre d'absence total : "></h:outputLabel>&emsp;
	    					<h:outputText value="#{consultation.count}"></h:outputText>&emsp;
	    				</div>
	    				<div class="col-2">
	    					<h:form>
	    						<h:commandLink styleClass="btn btn-info" actionListener="#{consultation.print}">
		    						<i class="fas fa-print"></i> Imprimer bilan
		    					</h:commandLink>
	    					</h:form>
	    				</div>
	    			</div>    			
    			</h:panelGroup>
  			</div>
		</div>
	</div>
</f:view>
<script type="text/javascript">
	function changed(element){
		element.parentElement.childNodes[1].value = element.value;
		console.log(element.parentElement.childNodes[1].value);
	}
</script>
<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.js"></script>
<script type="text/javascript" src="assets/js/popper.js"></script>
</body>
</html>