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
		<h:inputText value="#{manager.title}"></h:inputText><br><br>
		<h:commandButton value="add" actionListener="#{manager.add}"></h:commandButton>
			
	</h:form>
</f:view>
</body>
</html>