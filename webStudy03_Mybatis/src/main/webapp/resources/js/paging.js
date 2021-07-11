/**
 *  $("#searchForm").paging({
 *  	pagingArea : "#pagingArea",
 *  	pageLink : ".pageLink",
 *  	searchUI : "#searchUI",
 *  	btnSelector : "#searchBtn",
 *  	pageKey : "page",
 *  	pageParam : "page"
 *  });
 */
$.fn.paging=function(param){
	let searchForm = this;
	param = param ? param : {};
	const PAGINGAREA = $(param.pagingArea? param.pagingArea: "#pagingArea"); //페이징 애리아 
	const PAGELINK = param.pageLink ? param.pageLink : ".pageLink"; //vo에서 만드는 페이지 링크
	const SEARCHUI = $(param.searchUI ? param.searchUI : "#searchUI");//서치 폼
	const BTNSELECTOR = param.btnSelector ? param.btnSelector : "#searchBtn"; //검색 버튼
	const PAGEKEY = param.pageKey ? param.pageKey : "page"; //페이지
	const PAGEPARAM = param.pageParam ? param.pageParam : "page";//페이지
	
	PAGINGAREA.on("click", PAGELINK, function(event){ //vo에서 만든 링크를 클릭하면 
		event.preventDefault();
		let page = $(this).data(PAGEKEY); //폼에 있는 data 키에서 페이지를 받아옴
		searchForm.find('[name="'+PAGEPARAM+'"]').val(page); //페이지를 보내는 히든 입력 폼에 페이지를 변경함
		searchForm.submit(); //그 상태로 제출
		return false;
	}).css("cursor", "pointer");
	SEARCHUI.on("click", BTNSELECTOR ,function(){
		let inputs = SEARCHUI.find(":input[name]"); //name 값있는 입력들을 찾음
		$(inputs).each(function(idx, input){
			let name = this.name; // 그 입력 태그의 name 즉 vo에 설정된 값
			let value = $(this).val(); // 태그에 입력된 값 
			searchForm.find("[name='"+name+"']").val(value);//그 폼에서 그 네임값을 가진 태그의 밸류를 설정
		})
		searchForm.submit();
	});
	
	return this;
}
















