var seconds;
var curQuestion;
var curTotal;
var curTimer;
var prevTimer;

function showNextQuestion(index, total)
{
	curQuestion = index + 1;
	curTotal = total;
	
	finishTimer(false);
	
	if(index<=curTotal-2){
		document.getElementById('question_'+index).style.display = 'none';
		document.getElementById('question_'+curQuestion).style.display = 'block';
		refreshProgressBar(curQuestion, curTotal);

		if(index == curTotal-2){
			document.getElementById('next-button_'+curQuestion).innerHTML = 'Finish';
		}
	} else{
		document.getElementById('question_'+index).style.display = 'none';
		document.getElementById('finished').style.display = 'block';
		refreshProgressBar(curTotal, curTotal);
	}
}
function showFirstQuestion(total)
{
	curQuestion = 0;
	curTotal = total;
	
	document.getElementById('question_'+0).style.display = 'block';
	document.getElementById('starting').style.display = 'none';
	refreshProgressBar(0, total);
}
function refreshProgressBar(current, total){
	var percent = Math.round(current/total*100);
	var bar = document.getElementById('progress-bar');
	var percentString = ''+percent+'%';

	if(current == total){
		bar.className = 'progress-bar progress-bar-success';
	}

	bar.innerHTML = percentString;
	bar.setAttribute('aria-valuenow', percent);
	bar.style.width = percentString;
}
function startTimer(sec){
	document.getElementById('start-timer-button_'+curQuestion).style.display = 'none';
	
	seconds = sec;
	refreshTime();

	finishTimer(false);
	curTimer = setInterval(timer, 1000);
}
function timer()
{
	seconds--;
	if(seconds <= 10){
		document.getElementById("time-left_"+curQuestion).style.color = 'red';
	}
	if (seconds <= 0)
	{
		finishTimer(true);
		return;
	}
	refreshTime();
}
function finishTimer(forceNext){
	if(curTimer != undefined){
		clearInterval(curTimer);
		if(forceNext){
			showNextQuestion(curQuestion, curTotal);
		}
	}
}
function refreshTime(){
	var minutes = Math.floor(seconds / 60);
	var secondsLeft = seconds - minutes * 60;
	document.getElementById("time-left_"+curQuestion).innerHTML = minutes + ' min ' + secondsLeft + ' secs';
}