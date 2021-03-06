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
<div class="navbar navbar-expand-lg navbar-light bg-light">
<a class="navbar-brand" href="#" style="display: contents;">
    <img src="assets/img/index.png" width="100" height="100" class="d-inline-block align-top" alt="" />
    Gestion d'absence et demande d'absence
  </a>
  <div class="collapse navbar-collapse ml-5" id="navbarNav" style="float:right;">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="consultation.jsp">Consulter absence<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="addAbsence.jsp">Ajouter absence <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="addPreAbsence.jsp">Ajouter absence pr�justifier <span class="sr-only">(current)</span></a>
      </li>
    </ul>
  </div>
</div>
<f:view>
	<div class="container" style="margin-top: 25px;">
		<h:panelGroup rendered="#{consultation.studentNotFound == true}">
			<div class="alert alert-warning text-center" role="alert">
  				l'�tudiant n'est pas existe
			</div>
		</h:panelGroup>
		<div class="card text-center" style="width: 72rem;">
			<div class="card-header">
				<h:form>
					<h3 class="float-left">Gestions des absences</h3>
					<span class="float-right">
						<a class="btn btn-primary" href="addAbsence.jsp">
							<i class="fas fa-plus"></i> nouvelle absence
						</a>
					</span>
				</h:form>
			</div>
  			<div class="card-body">
    			<div class="row">
    				<div class="col">
   						<h:form>
    						<h:outputLabel value="Nom d'�tudiant : "></h:outputLabel>&emsp;
	    					<%-- <h:inputText value="#{consultation.student_name}"></h:inputText>&emsp; --%>
	    					<h:selectOneMenu value="#{consultation.student_name}">
	    						<f:selectItems value="#{consultation.etudiants}" />
	    					</h:selectOneMenu>&emsp;
	    					<h:commandLink styleClass="btn btn-info" actionListener="#{consultation.findStudent}">
	    						<i class="fas fa-search"></i> Consulter
	    					</h:commandLink>
    					</h:form>
    				</div>
    				<h:panelGroup rendered="#{consultation.displayTable == true}">
	    				<div class="col-5">
	    					<h:form>
	    						<h:outputLabel value="Filtrer par remarque : "></h:outputLabel>&emsp;
		    					<h:selectOneMenu value="#{consultation.filteredRemarque}">
									<f:selectItem itemValue = "N" itemLabel = "Tout" /> 
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
										<h:inputText readonly="true" value="#{absence.seance.module.libelle} - #{absence.seance.date_horaire.date} / #{(absence.seance.date_horaire.month + 1)} / #{(absence.seance.date_horaire.year + 1900)}"></h:inputText>
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
	    				<div class="col-8">
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
    			<br><br>
    			<h:panelGroup rendered="#{consultation.document_printed}">
					<div class="row">
	    				<div class="col">
    						<div class="alert alert-success">
							  le document a �t� sauvegard� dans votre D://
							</div>
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