<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*" %>
<%@ page import="reivax.norac.interviewprep.webapp.*" %>

<%
// RETRIEVE THE MAIN OBJECT
List<Entry> questions = (List<Entry>) request.getAttribute("questions");
Boolean uploadSuccess = (Boolean) request.getAttribute("upload-success");
Boolean uploadFail = (Boolean) request.getAttribute("upload-fail");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" prefix="og: http://ogp.me/ns#">
<head>
<meta charset="ISO-8859-1">
	<meta name="title" content="Interview Preparation - Upload and Rehearse">
	<meta name="description" content="Interview Preparation - A web app providing interview rehearsal by letting users upload their own set of questions">
	<meta name="keywords" content="Interview, rehearsal">
    <meta name="author" content="Xavier CARON">
	
	<meta property="og:title" content="Interview Preparation - Upload and Rehearse" />
	<meta property="og:type" content="website" />
	<meta property="og:url" content="interview-preparation.herokuapp.com" />
	<meta property="og:description" content="Interview Preparation - A web app providing interview rehearsal by letting users upload their own set of questions" />
	<meta property="og:image" content="http://xavier.w.caron.free.fr/website/resources/img/interview-preparation/interview-preparation-app.png" />
	<meta property="og:image:type" content="image/jpeg" />
	<meta property="og:image:width" content="150" />
	<meta property="og:image:height" content="150" />
	
	<meta name="twitter:card" content="summary" />
	<meta name="twitter:title" content="Interview Preparation - Upload and Rehearse" />
	<meta name="twitter:description" content="Interview Preparation - A web app providing interview rehearsal by letting users upload their own set of questions" />
	<meta name="twitter:creator" content="@Xavier_w_Caron" />
	<meta name="twitter:image:src" content="http://xavier.w.caron.free.fr/website/resources/img/interview-preparation/interview-preparation-app.png" />
	<meta name="twitter:image:width" content="150" />
	<meta name="twitter:image:height" content="150" />
	
	
    <link rel="shortcut icon" href="http://xavier.w.caron.free.fr/website/resources/img/interview-preparation/interview-preparation-app.ico">


	<title>Interview Preparation</title>

	<!-- CSS -->
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <link href="./bootstrap-3.2.0/dist/css/bootstrap.css" rel="stylesheet">
    
    <!-- JQUERY -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.js"></script>
    
    <!-- BOOTSTRAP -->
    <script type="text/javascript" src="./bootstrap-3.2.0/dist/js/bootstrap.js"></script>    
    
    <script type="text/javascript">
		var questionsJ = new Array();
		var timeoutsJ = new Array();
	</script>
	
	<%
	if(questions != null){
		for(int i=0; i<questions.size(); i++){
			Entry e = questions.get(i);
			String ques = e.getQuestion();
			long time = e.getTimeout();
			%>
			<script type="text/javascript">questionsJ.push("<%=ques%>");</script>
			<script type="text/javascript">timeoutsJ.push(<%=time%>);</script>
			<%
		}
	}%>
    
    <!-- MY JS FILES -->
    <script type="text/javascript" src="./bootstrap-3.2.0/js/questionshelper.js"></script>
    <script type="text/javascript" src="./bootstrap-3.2.0/js/formchecker.js"></script>
    
</head>
<body>

	<nav class="navbar navbar-default navbar-static-top" role="navigation">
		<div class="container">
	      <div class="container-fluid">
	        <div class="navbar-header">
	          <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-briefcase"></span> Interview Preparation</a>
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

	  <div class="row" id="progress-div" style="display:none">
		  <div class="col-sm-12">
			  <div class="progress">
				  <div id="progress-bar" class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
				    0%
				  </div>
			  </div>
		</div>
	  </div>

	  <div id="questions" class="row">
	  		<div id=starting>
				<div class="col-md-6">
		          <div class="shadow padding20">
		          	<h1><span class="glyphicon glyphicon-cog"></span> Step 1: Personalise</h1>
					<hr>
					<h2>Upload your own set of questions</h2>
					<form role="form" action="UploadQuestionsAction" id="upload_form"
							name="upload_form" method="post" enctype="multipart/form-data" 
							onsubmit="return(validateUploadForm());">
							<div class="form-group">
								<input type="file" id="inputFileQuestions" name="file">
								<p class="help-block">
									The file must be a JSON (.json) file. <br>
									Not sure what that is? No worries, a sample file is provided below.<br>
									Download it, modify it to match your expectations and upload it back!<br>
									Yes, it's THAT simple!
								</p>
								<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-upload"></span> Upload</button>
							</div>
					</form>
					<hr>
					<h2>Get a sample file here</h2>
					<p>
					<code>
						[<br>
						{"question": "What is your weakness?", "timeout": "60"}, <br>
						{"question": "Where do you see yourself in 3 years time?"}
						<br> ]
					</code>
					</p>
					<a href="DownloadSampleAction"><button type="button" class="btn btn-info"><span class="glyphicon glyphicon-download"></span> Download Sample</button></a>
				  </div>
				</div>
				<div class="col-md-6">
					<div id="error_form_upload" class="alert alert-warning" role="alert"
					style="display: none">
						<p>
							<strong>Oh snap!</strong> Invalid form format, verify 
							that the file you try to upload is a JSON file.
						</p>
					</div>
					<%if(uploadFail != null && uploadFail){ %>
					<div id="upload_fail" class="alert alert-danger" role="alert">
						<p>
							<strong>Oh snap!</strong> Invalid form format, verify 
							that the file you try to upload is a JSON file.
						</p>
					</div>
					<%} else if(uploadSuccess != null && uploadSuccess){ %>
					<div id="upload_success" class="alert alert-success" role="alert">
						<p>
							<strong>Uploaded!</strong> Your questions have been successfully uploaded 
							in the system.
						</p>
					</div>
					<%} %>
					<h1><span class="glyphicon glyphicon-play"></span> Step 2: Jump!</h1>
					<hr>
					<h2>Everything set? <span class="glyphicon glyphicon-ok"></span></h2>
					<h2>Ready for the Interview Training? <span class="glyphicon glyphicon-ok"></span> </h2>
					<button type="button" class="btn btn-success" onclick="showNextQuestion(-1);"><span class="glyphicon glyphicon-play-circle"></span> Let's Start!</button>
				</div>
			</div>
          <%
	          for(int i=0; i<questions.size(); i++){
	        	  Entry q = questions.get(i);
	          %>
	            <div id="question_<%=i %>" style="display:none">
	              <div class="col-sm-8">
		              <div class="shadow padding20">
		                <h1><span class="glyphicon glyphicon-question-sign"></span> Question n°<%=i+1 %></h1>
		              	<hr>
		              	<h2><%=q.getQuestion() %></h2>
	            		<button type="button" class="btn btn-default" id="next-button_<%=i %>" onclick="showNextQuestion(<%=i %>)">Next</button>
		              </div>
	              </div>
	              <div class="col-sm-4">
		              <div class="shadow padding20">
		              	<h1><span class="glyphicon glyphicon-time"></span> Time Left</h1>
		              	<hr>
		              	<%if(q.getTimeout() <= 0){ %>
		            		<h2 id="time-left_<%=i %>">This question is not timed.</h2>
		            	<%} else{%>
		            		<h2 id="time-left_<%=i %>" style="display:none"></h2>
		              	<%} %>
		              </div>
		          </div>
	            </div>
	          <%
	          }%>
	          <div id="finished" class="col-sm-12" style="display:none">
	          	<h1><span class="glyphicon glyphicon-thumbs-up"></span> Congratulations!</h1>
	          	<hr>
	          	<h2>You've finished the Interview Training!</h2>
	          	<h2>Think you can do better?</h2>
	          	<a href="Home"><button type="button" class="btn btn-success"><span class="glyphicon glyphicon-repeat"></span> Do it Again!</button></a>
	          </div>
        </div>
          
     <%} %>
          
       <hr>

      <footer>
      	<p>
			Design adapted from <i>Bootstrap</i>.
		</p>
        <p class="pull-right"><a href="#"><span style="font-size:30px" class="glyphicon glyphicon-chevron-up"></span></a></p>
        <p>© <a href="http://www.linkedin.com/in/xavierwilfriddimitrycaron" target="_blank"><b>Xavier CARON</b></a>, 2014</p>
      </footer>

    </div><!--/.container-->
	
</body>
</html>
