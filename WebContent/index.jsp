<%@ page language="java" contentType="text/html; charset=windows-1256" pageEncoding="windows-1256"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
<f:view>
	<h:form>
		<h:outputLabel value="Title : "></h:outputLabel>
		<h:inputText value="#{manager.title}"></h:inputText>
		<h:inputHidden value="#{manager.id}"></h:inputHidden>
		<h:commandButton value="add" actionListener="#{manager.add}"></h:commandButton>
		<h:commandButton value="modify" actionListener="#{manager.modify}"></h:commandButton><br><br>
	</h:form>
	<h:dataTable value="#{manager.absencesListe}" var="absence" border="1" style="width:70%; border:1px solid #000;">
		<h:column>
			<f:facet name="header"><h:outputText value="Id"></h:outputText></f:facet>
			<h:outputText value="#{absence.id}"></h:outputText>
		</h:column>
		<h:column>
			<f:facet name="header"><h:outputText value="Title"></h:outputText></f:facet>
			<h:outputText value="#{absence.title}"></h:outputText>
		</h:column>
		<h:column>
			<f:facet name="header"><h:outputText value="Actions"></h:outputText></f:facet>
			<h:form>
				<h:commandButton value="Select" type="button" actionListener="#{manager.select}">
					<f:param name="id" value="#{absence.id}"></f:param>
					<f:param name="title" value="#{absence.title}"></f:param>
				</h:commandButton>
				<h:commandButton value="Remove"></h:commandButton>
			</h:form>
		</h:column>
	</h:dataTable>
</f:view>
</body>
</html>