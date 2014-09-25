<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="reivax.norac.interviewprep.webapp.*" %>

<%
// RETRIEVE THE MAIN OBJECT
List<String> questions = (List<String>) request.getAttribute("questions");
int total = questions.size();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" prefix="og: http://ogp.me/ns#">
<head>
<meta charset="ISO-8859-1">
	<meta name="title" content="Weekend Planner - Top 10 Places">
	<meta name="description" content="Weekend Planner - A web app providing top 10 places for the preparation of weekend adventure (featuring a choice of restaurants, attractions and pubs)">
	<meta name="keywords" content="Travel, weekend planner">
    <meta name="author" content="Xavier CARON">
	
	<meta property="og:title" content="Weekend Planner - Top 10 Places" />
	<meta property="og:type" content="website" />
	<meta property="og:url" content="xaviertraveltips.herokuapp.com" />
	<meta property="og:description" content="Weekend Planner - A web app providing top 10 places for the preparation of weekend adventure (featuring a choice of restaurants, attractions and pubs)" />
	<meta property="og:image" content="http://xavier.w.caron.free.fr/website/resources/img/weekend-planner/weekend-planner-app.png" />
	<meta property="og:image:type" content="image/jpeg" />
	<meta property="og:image:width" content="150" />
	<meta property="og:image:height" content="150" />
	
	<meta name="twitter:card" content="summary" />
	<meta name="twitter:title" content="Weekend Planner - Top 10 Places" />
	<meta name="twitter:description" content="Weekend Planner - A web app providing top 10 places for the preparation of weekend adventure (featuring a choice of restaurants, attractions and pubs)" />
	<meta name="twitter:creator" content="@Xavier_w_Caron" />
	<meta name="twitter:image:src" content="http://xavier.w.caron.free.fr/website/resources/img/weekend-planner/weekend-planner-app.png" />
	<meta name="twitter:image:width" content="150" />
	<meta name="twitter:image:height" content="150" />
	
	
    <link rel="shortcut icon" href="http://xavier.w.caron.free.fr/website/resources/img/weekend-planner/weekend-planner-app.ico">


	<title>Interview Preparations</title>

	<!-- CSS -->
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <link href="./bootstrap-3.2.0/dist/css/bootstrap.css" rel="stylesheet">
    
    <!-- JQUERY -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.js"></script>
    
    <!-- BOOTSTRAP -->
    <script type="text/javascript" src="./bootstrap-3.2.0/dist/js/bootstrap.js"></script>    
    
    <!-- MY JS FILES -->
    <script type="text/javascript" src="./bootstrap-3.2.0/js/questionshelper.js"></script>
    
</head>
<body onload="showFirstQuestion(<%=total%>);">

	<nav class="navbar navbar-default navbar-static-top" role="navigation">
		<div class="container">
	      <div class="container-fluid">
	        <div class="navbar-header">
	          <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-briefcase"></span> Interview Preparations</a>
	        </div>
	        <div class="collapse navbar-collapse">
	          <p class="navbar-text navbar-right">Upload your own Questions and Rehearse!</p>
	        </div>
	      </div>
    	</div>
    </nav>

	<div class="container">

      <%
      if(questions != null){
   	  %>

	  <div class="row">
		  <div class="col-md-12">
			  <div class="progress">
				  <div id="progress-bar" class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
				    0%
				  </div>
			  </div>
		</div>
	  </div>

	  <div id="questions" class="row">
          <%
	          for(int i=0; i<total; i++){
	        	  String q = questions.get(i);
	          %>
	            <div id="question_<%=i %>" style="display:none">
	              <div class="col-md-8">
		              <div class="shadow padding20">
		                <h1>Question n°<%=i+1 %></h1>
		              	<h2><%=q %></h2>
	            		<button type="button" class="btn btn-default" id="next-button_<%=i %>" onclick="showNextQuestion(<%=i %>, <%=total %>)">Next</button>
		              </div>
	              </div>
	              <div class="col-md-4">
		              <div class="shadow padding20">
		              	<h2 style="text-align:center"><span class="glyphicon glyphicon-time"></span></h2>
		              	<hr>
	            		<p>This question is not timed.</p>
		              </div>
		          </div>
	            </div>
	          <%
	          }%>
	          <div id="finished" class="col-md-12" style="display:none">
	          	<h1>You've finished the Interview!</h1>
	          	<a href="Home"><button type="button" class="btn btn-success">Do it again!</button></a>
	          </div>
        </div>
          
     <%} %>
          
       <hr>

      <footer>
        <p class="pull-right"><a href="#"><span style="font-size:30px" class="glyphicon glyphicon-chevron-up"></span></a></p>
        <p>© <a href="http://www.linkedin.com/in/xavierwilfriddimitrycaron" target="_blank"><b>Xavier CARON</b></a>, 2014</p>
      </footer>

    </div><!--/.container-->
	
</body>
</html>
