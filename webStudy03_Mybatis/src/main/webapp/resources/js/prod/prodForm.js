/**
 * 
 */

$.fn.othersSelect = function(param){
	let prodLgu = this;
	let prodBuyer = param.buyerTag ;
	
	prodLgu.on("change" , function(){
		let lgu = $(this).val(); //lgu 셀렉터 
		if(lgu){
			prodBuyer.find("option").hide(); // 모든 옵션 숨김 --> lgu에 해당하지 않는 경우 안 보이게 하려고 
			prodBuyer.find("option." + lgu).show(); //해당 lgu에 맞는 바이어 명들만 보이게 함
			prodBuyer.find("option:first").show();//분류라고 된 option은 보이게 하려고 설정해줌 
			
		}else{
			prodBuyer.find("option").show();
		}
	});
	return this;
	
}