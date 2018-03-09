
jQuery(document).on('click','#startChat' , function(event){
	$('#chatwindow').show();
	$('#startChat').hide();
});


jQuery(document).on('click','.closeChat' , function(event){
	$('#chatwindow').hide();
	$('#startChat').show();
	
});

jQuery(document).on('click','#clearbutton' , function(event){
	$('.questionFromUser').remove();
	$('.answerFromBot').remove();
});



jQuery(document).on('click','#sendButton' , function(e){
	var userinputtext = $('#inputTextFromUser').val();
	if(null != userinputtext && "" != userinputtext){
		/*$('.userquestion').text('');
			$('.questionFromUser').hide();*/

		if(userinputtext.length == 5 && $.isNumeric(userinputtext)){
			$('.typedQs').attr('data-userqs',userinputtext);
			$('.typedQs').attr('data-question','zip');
		}else{
			$('.typedQs').attr('data-userqs',userinputtext);
			$('.typedQs').attr('data-question',userinputtext);
		}
		$('.typedQs').click();
	}
});

jQuery(document).on('keydown','#inputTextFromUser' , function(e){
	if(e.keyCode == 13){
		var userinputtext = $('#inputTextFromUser').val();
		if(null != userinputtext && "" != userinputtext){
			/*$('.userquestion').text('');
			$('.questionFromUser').hide();*/
			
			if(userinputtext.length == 5 && $.isNumeric(userinputtext)){
				$('.typedQs').attr('data-userqs',userinputtext);
				$('.typedQs').attr('data-question','zip');
			}else{
				$('.typedQs').attr('data-userqs',userinputtext);
				$('.typedQs').attr('data-question',userinputtext);
			}
			$('.typedQs').click();
		}
	}
});



jQuery(document).on('click','#stdLinks' , function(event){
	var userinputClick = $(this).attr('data-userqs');
	
	var question = $(this).attr('data-question');
	$('.questionFromUser').show();
	var request;
	
	$('.botDiv').append('<div class="questionFromUser"><img	src="/SpringMVCloginExample/resources/images/profilepic.png" style="width: 40px; float: right;padding-left: 7px" />' 
			+'<div class="talk-bubble tri-right right-in"><div class="talktext"><p class="userquestion">'+userinputClick+'</p></div></div></div>');
	$('.questionFromUser').show();
	
	request = $.ajax({
		url: "/SpringMVCloginExample/chatMsgFromUser",
		cache: false,
		type: "POST",
		data : {question:question},
		dataType : 'json'
	});
	
	request.done(function (response, textStatus, jqXHR){

		if(null != response){
			
			
			$('.botDiv').append('<div class="answerFromBot"><img	src="/SpringMVCloginExample/resources/images/customer-service-icon.png" style="width: 35px; float: left;padding-left: 7px" />' 
					+'<div class="talk-bubble tri-right left-in"><div class="talktext"><p class="responseFromBot">'+response+'</p></div></div></div>');
			
			/*$('.questionFromUser')append('<div class="answerFromBot">'+
					'<img src="<c:url value="/resources/images/customer-service-icon.png" />" style="width: 35px; float: left" />'
				+'<div class="talk-bubble tri-right left-in"><div class="talktext"><p class="responseFromBot">'+response+'</p></div></div></div>');*/
			
			$('.answerFromBot').show();
		}
		$('#inputTextFromUser').val("");
		var d = $('#mainChatScrollDiv');
		d.scrollTop(d.prop("scrollHeight"));
	});
	
	request.fail(function(jqXHR, textStatus,
			errorThrown) {
		// log the error to the console
		$('.botDiv').append('<div class="answerFromBot"><img	src="/SpringMVCloginExample/resources/images/customer-service-icon.png" style="width: 35px; float: left;padding-left: 7px" />' 
				+'<div class="talk-bubble tri-right left-in"><div class="talktext"><p class="responseFromBot">'+" Sorry, I couldn't help you."+'</p></div></div></div>');
		
		/*$('.questionFromUser')append('<div class="answerFromBot">'+
				'<img src="<c:url value="/resources/images/customer-service-icon.png" />" style="width: 35px; float: left" />'
			+'<div class="talk-bubble tri-right left-in"><div class="talktext"><p class="responseFromBot">'+response+'</p></div></div></div>');*/
		
		$('.answerFromBot').show();
		$('#inputTextFromUser').val("");
		var d = $('#mainChatScrollDiv');
		d.scrollTop(d.prop("scrollHeight"));

	});

});
