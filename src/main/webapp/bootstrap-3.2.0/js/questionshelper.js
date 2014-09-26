var seconds;
var nextQuestion;
var curQuestion;
var curTotal = questionsJ.length;
var curTimer;
var prevTimer;

function showNextQuestion(index)
{
	curQuestion = index;
	nextQuestion = index + 1;
	var timeout = timeoutsJ[nextQuestion];
	finishTimer(false);
	
	if(timeout > 0){
		startTimer(timeout);
	}
	
	if(curQuestion == -1){
		//Initialise
		document.getElementById('progress-div').style.display = 'block';
		document.getElementById('question_'+nextQuestion).style.display = 'block';
		document.getElementById('starting').style.display = 'none';
		refreshProgressBar(nextQuestion);
	} else if(curQuestion<=curTotal-2){
		document.getElementById('question_'+curQuestion).style.display = 'none';
		document.getElementById('question_'+nextQuestion).style.display = 'block';
		refreshProgressBar(nextQuestion);

		if(curQuestion == curTotal-2){
			document.getElementById('next-button_'+nextQuestion).innerHTML = 'Finish';
		}
	} else{
		//Finish
		document.getElementById('question_'+curQuestion).style.display = 'none';
		document.getElementById('finished').style.display = 'block';
		refreshProgressBar(curTotal);
	}
}
function refreshProgressBar(current){
	var percent = Math.round(current/curTotal*100);
	var bar = document.getElementById('progress-bar');
	var percentString = ''+percent+'%';

	if(current == curTotal){
		bar.className = 'progress-bar progress-bar-success';
	}

	bar.innerHTML = percentString;
	bar.setAttribute('aria-valuenow', percent);
	bar.style.width = percentString;
}
function startTimer(sec){
//	document.getElementById('start-timer-button_'+nextQuestion).style.display = 'none';
	
	seconds = sec;
	document.getElementById("time-left_"+nextQuestion).style.display = 'block';
	refreshTime();

	finishTimer(false);
	curTimer = setInterval(timer, 1000);
}
function timer()
{
	seconds--;
	if(seconds <= 10){
		document.getElementById("time-left_"+nextQuestion).style.color = 'red';
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
			showNextQuestion(nextQuestion);
		}
	}
}
function refreshTime(){
	var minutes = Math.floor(seconds / 60);
	var secondsLeft = seconds - minutes * 60;
	document.getElementById("time-left_"+nextQuestion).innerHTML = minutes + ' min ' + secondsLeft + ' secs';
}