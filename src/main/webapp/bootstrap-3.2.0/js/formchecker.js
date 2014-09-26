function validateUploadForm()
{
   var re = /(?:\.([^.]+))?$/;
   var input = document.upload_form.inputFileQuestions.value;
   if( input == "" )
   {
     showError('error_form_upload');
     return false;
   } else if(re.exec(input)[1].toUpperCase() != "JSON"){
	   showError('error_form_upload');
	   return false;
   }
   hideError('error_form_upload');
   return true;
}
function showError(id)
{
	document.getElementById(id).style.display = 'block';
}
function hideError(id)
{
	document.getElementById(id).style.display = 'none';
}