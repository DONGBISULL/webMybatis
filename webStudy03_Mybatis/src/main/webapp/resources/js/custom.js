/**
 * 
 */

$.customAlert = function(message){
	alert(message)
}

$.timeFormat = function(time){ //unit : seconds
	//1 : 59
	let min = Math.trunc(time/60);
	let second = time%60;
	return min +" : " + second;
	
}

$.fn.sessionTimer= function(obj){ //jquery 앨리먼트 객체가 생성된 후 사용 가능 
	if(!obj || !obj.timeout){//객체가 있으면 
		throw "타이머를 구현하기 위해 필수 파라미터가 필요함";
		return;
	}
	const SPEED = 100;
	const TIMEOUT = obj.timeout;
	const AJAXURL = obj.url ?obj.url:"";
	const TIMERAREA = this;
	
	let timer =-1;
	let timerJob = null;
	let messageArea = null;
	let messageJob = null;
	
	function MakeMessageArea(element){
		let messageArea = $("<div>").prop("id" , "messageArea").append("세션을 연장하시겠습니까?" , $("<br>") 
				,$("<input>").attr({"type" : "button" ,"id" : "yesBtn" , "value" :"예"}),
				 $("<input>").attr({"type" : "button" ,"id" : "noBtn" , "value" :"아니오"})
		).on("click" ,'input' , function(){
			let id = $(this).prop("id");
			if(id =="yesBtn"){
				init();
				$.ajax({
					url : AJAXURL,
					method : "head",//응답을 보내는 방식
				});
			}
			$(this).parents("#messageArea").hide();
		}).hide();
		element.after(messageArea);  
		return messageArea;
//		<div id="messageArea">
//			세션을 연장하시겠습니까?<br>
//			<input type="button" id="yesBtn" value="예">
//			<input type="button" id="noBtn" value="아니요">
//		</div>
	}
	
	
	function destroy(){
		if(timerJob!=null) {
			clearInterval(timerJob);
			timerJob = null;
		}
		if(messageJob!=null){
			clearInterval(messageJob);
			messageJob = null
		}
		if(messageArea!=null) {
			messageArea.remove();
			messageArea = null;
		}
		 location.reload()
	}
	
	function init(){//자바스크립트는 함수도 객체로 따짐
		timer =TIMEOUT -1 ;
		//timerJob이 널일 때 수행 
		if(timerJob==null){
			timerJob = setInterval(function(){
				TIMERAREA.text($.timeFormat(timer--));
				if(timer<0){
					destroy();//수정
				}
			} , SPEED);
		}
		
		if(messageArea==null) messageArea = MakeMessageArea(TIMERAREA);
		if(messageJob==null){
			messageJob = setTimeout(function(){
				messageArea.show();
				messageJob = null;
			} ,(TIMEOUT- 60)*SPEED)
		}
	}
	init();
	//console.log(this);
	/*this.html(TIMEOUT);*/
	//비동기 요청 (url)
	return this;
}

