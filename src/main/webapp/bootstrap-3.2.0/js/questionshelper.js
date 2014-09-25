function showNextQuestion(index, total)
{
	if(index<=total-2){
		var next = index+1;
		document.getElementById('question_'+index).style.display = 'none';
		document.getElementById('question_'+next).style.display = 'block';
		refreshProgressBar(next, total);
		
		if(index == total-2){
			document.getElementById('next-button_'+next).innerHTML = 'Finish';
		}
	} else{
		document.getElementById('question_'+index).style.display = 'none';
		document.getElementById('finished').style.display = 'block';
		refreshProgressBar(total, total);
	}
}
function showFirstQuestion(total)
{
	document.getElementById('question_'+0).style.display = 'block';
	refreshProgressBar(0, total);
}
function refreshProgressBar(current, total){
	var percent = Math.round(current/total*100);
	var bar = document.getElementById('progress-bar');
	var percentString = ''+percent+'%';
	bar.innerHTML = percentString;
	bar.setAttribute('aria-valuenow', percent);
	bar.style.width = percentString;
}