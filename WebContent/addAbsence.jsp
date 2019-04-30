<%@ page language="java" contentType="text/html; charset=windows-1256" pageEncoding="windows-1256"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Ajout d'absence</title>
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
      <li class="nav-item active">
        <a class="nav-link" href="addAbsence.jsp">Ajouter absence <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="addPreAbsence.jsp">Ajouter absence préjustifier <span class="sr-only">(current)</span></a>
      </li>
    </ul>
  </div>
</div>
<f:view>
	<div class="container" style="margin-top: 25px;">
		<div class="card">
			<div class="card-header text-center">
				<h:form>
					<h3 class="float-left">Ajout des absences</h3>
				</h:form>
			</div>
  			<div class="card-body">
				<div class="row">
    				<div class="col d-flex justify-content-center">
   						<h:form>
    						<h:outputLabel value="Séance : "></h:outputLabel>&emsp;
	    					<h:selectOneMenu value="#{ajoutv2.seance_id}">
								<f:selectItems value="#{ajoutv2.seances}"/>
							</h:selectOneMenu>&emsp;
							<%-- <h:inputHidden binding="#{consultation.seanceInput}"></h:inputHidden> --%>
	    					<h:commandLink styleClass="btn btn-info" actionListener="#{ajoutv2.apply}">
	    						<i class="fas fa-search"></i> Accéder
	    					</h:commandLink>
    					</h:form>
    				</div>
    			</div>
    			<br><br>
    			<h:panelGroup rendered="#{ajoutv2.show_table}">
	    			<div class="row text-center">
	   					<div class="col">
							<h:form>
								<h:dataTable styleClass="table" value="#{ajoutv2.etudiants}" var="e">
									<h:column>
										<f:facet name="header"><h:outputText value="Nom"/></f:facet>
										<h:outputText value="#{e.nom}"></h:outputText>
									</h:column>
									<h:column>
										<f:facet name="header"><h:outputText value="Prenom"/></f:facet>
										<h:outputText value="#{e.prenom}"></h:outputText>
									</h:column>
									<h:column>
										<f:facet name="header"><h:outputText value="Numero d'inscription"/></f:facet>
										<h:outputText value="#{e.numero_inscription}"></h:outputText>
									</h:column>
									<h:column>
										<f:facet name="header"><h:outputText value="Email"/></f:facet>
										<h:outputText value="#{e.email}"></h:outputText>
									</h:column>
									<h:column>
										<f:facet name="header"><h:outputText value="Remarque"/></f:facet>
										<h:selectOneMenu styleClass="form-control" value="#{ajoutv2.remarque}">
											<f:selectItem itemValue = "#{null}" itemLabel = "-----" /> 
	   										<f:selectItem itemValue = "R" itemLabel = "R" />
	   										<f:selectItem itemValue = "E" itemLabel = "E" />
	   										<f:selectItem itemValue = "A" itemLabel = "A" />
										</h:selectOneMenu>
									</h:column>
									<h:column>
										<f:facet name="header"><h:outputText value="Action"/></f:facet>
										<h:commandLink type="button" styleClass="btn btn-warning" value="Sauvegarder" actionListener="#{ajoutv2.addAbsence}">
											<f:param name="etudiant_id" value="#{e.id}"></f:param>
										</h:commandLink>
									</h:column>
									<h:column>
										<f:facet name="header"><h:outputText value="Notifications"/></f:facet>
										<h:outputText value="#{ajoutv2.notifications.get(e)}"></h:outputText>
									</h:column>
								</h:dataTable>
							</h:form>
	    				</div>
	    			</div>
	    		</h:panelGroup>
	    		<h:panelGroup rendered="#{ajoutv2.no_students_exists}">
	    			<div class="alert alert-info text-center">
					  Il n'y a pas des étudiants disponibles pour cette seance
					</div>
	    		</h:panelGroup>
  			</div>
		</div><br><br>
	</div>
</f:view>
<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.js"></script>
<script type="text/javascript" src="assets/js/popper.js"></script>
</body>
</html>