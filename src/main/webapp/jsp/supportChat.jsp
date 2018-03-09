<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<title>Customer Support</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script>
	$(document).load(function() {
		$('.b-agent-demo_powered_by').hide();
	});
</script>
<script src="resources/ChatJs/js/jquery-1.9.1.min.js"></script>
<script src="resources/ChatJs/js/jquery.autosize.js"></script>
<script src="resources/js/support.js"></script>
<link rel="stylesheet" href="resources/css/democss.css" type="text/css" />
<link rel="icon" href="resources/images/favicon.ico.png" />
<!-- chatjs requirements -->
<link rel="stylesheet" href="resources/Styles/styles.css" />
<style type="text/css">

/* General CSS Setup */

/* container */
.container {
	padding: 5% 5%;
}

/* CSS talk bubble */
.talk-bubble {
	margin: 40px;
	display: inline-block;
	position: relative;
	width: 200px;
	height: auto;
	background-color: antiquewhite;
}

.border {
	border: 8px solid #666;
}

.round {
	border-radius: 30px;
	-webkit-border-radius: 30px;
	-moz-border-radius: 30px;
}

/* Right triangle placed top left flush. */
.tri-right.border.left-top:before {
	content: ' ';
	position: absolute;
	width: 0;
	height: 0;
	left: -40px;
	right: auto;
	top: -8px;
	bottom: auto;
	border: 32px solid;
	border-color: #666 transparent transparent transparent;
}

.tri-right.left-top:after {
	content: ' ';
	position: absolute;
	width: 0;
	height: 0;
	left: -20px;
	right: auto;
	top: 0px;
	bottom: auto;
	border: 22px solid;
	border-color: antiquewhite transparent transparent transparent;
}

/* Right triangle, left side slightly down */
.tri-right.border.left-in:before {
	content: ' ';
	position: absolute;
	width: 0;
	height: 0;
	left: -40px;
	right: auto;
	top: 30px;
	bottom: auto;
	border: 20px solid;
	border-color: #666 #666 transparent transparent;
}

.tri-right.left-in:after {
	content: ' ';
	position: absolute;
	width: 0;
	height: 0;
	left: -20px;
	right: auto;
	top: 8px;
	bottom: auto;
	border: 12px solid;
	border-color: antiquewhite antiquewhite transparent transparent;
}

/*Right triangle, placed bottom left side slightly in*/
.tri-right.border.btm-left:before {
	content: ' ';
	position: absolute;
	width: 0;
	height: 0;
	left: -8px;
	right: auto;
	top: auto;
	bottom: -40px;
	border: 32px solid;
	border-color: transparent transparent transparent #666;
}

.tri-right.btm-left:after {
	content: ' ';
	position: absolute;
	width: 0;
	height: 0;
	left: 0px;
	right: auto;
	top: auto;
	bottom: -20px;
	border: 22px solid;
	border-color: transparent transparent transparent antiquewhite;
}

/*Right triangle, placed bottom left side slightly in*/
.tri-right.border.btm-left-in:before {
	content: ' ';
	position: absolute;
	width: 0;
	height: 0;
	left: 30px;
	right: auto;
	top: auto;
	bottom: -40px;
	border: 20px solid;
	border-color: #666 transparent transparent #666;
}

.tri-right.btm-left-in:after {
	content: ' ';
	position: absolute;
	width: 0;
	height: 0;
	left: 8px;
	right: auto;
	top: auto;
	bottom: -20px;
	border: 12px solid;
	border-color: antiquewhite transparent transparent antiquewhite;
}

/*Right triangle, placed bottom right side slightly in*/
.tri-right.border.btm-right-in:before {
	content: ' ';
	position: absolute;
	width: 0;
	height: 0;
	left: auto;
	right: 30px;
	bottom: -40px;
	border: 20px solid;
	border-color: #666 #666 transparent transparent;
}

.tri-right.btm-right-in:after {
	content: ' ';
	position: absolute;
	width: 0;
	height: 0;
	left: auto;
	right: 8px;
	bottom: -20px;
	border: 12px solid;
	border-color: antiquewhite antiquewhite transparent transparent;
}
/*
	left: -8px;
  right: auto;
  top: auto;
	bottom: -40px;
	border: 32px solid;
	border-color: transparent transparent transparent #666;
	left: 0px;
  right: auto;
  top: auto;
	bottom: -20px;
	border: 22px solid;
	border-color: transparent transparent transparent antiquewhite;

/*Right triangle, placed bottom right side slightly in*/
.tri-right.border.btm-right:before {
	content: ' ';
	position: absolute;
	width: 0;
	height: 0;
	left: auto;
	right: -8px;
	bottom: -40px;
	border: 20px solid;
	border-color: #666 #666 transparent transparent;
}

.tri-right.btm-right:after {
	content: ' ';
	position: absolute;
	width: 0;
	height: 0;
	left: auto;
	right: 0px;
	bottom: -20px;
	border: 12px solid;
	border-color: antiquewhite antiquewhite transparent transparent;
}

/* Right triangle, right side slightly down*/
.tri-right.border.right-in:before {
	content: ' ';
	position: absolute;
	width: 0;
	height: 0;
	left: auto;
	right: -40px;
	top: 30px;
	bottom: auto;
	border: 20px solid;
	border-color: #666 transparent transparent #666;
}

.tri-right.right-in:after {
	content: ' ';
	position: absolute;
	width: 0;
	height: 0;
	left: auto;
	right: -20px;
	top: 8px;
	bottom: auto;
	border: 12px solid;
	border-color: antiquewhite transparent transparent antiquewhite;
}

/* Right triangle placed top right flush. */
.tri-right.border.right-top:before {
	content: ' ';
	position: absolute;
	width: 0;
	height: 0;
	left: auto;
	right: -40px;
	top: -8px;
	bottom: auto;
	border: 32px solid;
	border-color: #666 transparent transparent transparent;
}

.tri-right.right-top:after {
	content: ' ';
	position: absolute;
	width: 0;
	height: 0;
	left: auto;
	right: -20px;
	top: 0px;
	bottom: auto;
	border: 20px solid;
	border-color: antiquewhite transparent transparent transparent;
}

/* talk bubble contents */
.talktext {
	padding: 1em;
	text-align: left;
	line-height: 1.5em;
}

.talktext p {
	/* remove webkit p margins */
	-webkit-margin-before: 0em;
	-webkit-margin-after: 0em;
}

.button {
	background-color: #800020;
	color: white;
	/* border: 2px solid; */
	/* text-decoration: none; */
	padding-left: 10px;
	padding-right: 10px;
	padding-top: 2px;
	padding-bottom: 2px;
	text-align: center;
	font-size: smaller;
}

a:hover {
	background-color: #800020;
	color: white;
}
</style>
</head>
<body bgcolor="" style="font-family: verdana; font-size: small;">
	<img src="<c:url value="/resources/images/tridib_header.png" />"
		style="width: 100%;">
	<!--<img src="<c:url value="/resources/images/TopHeader.jpg" />"
		style="width: 100%;">
	-->
	<!-- <div style="right: 0px; bottom: 0px; position: absolute">
		<iframe width="350" height="430"
			src="https://console.api.ai/api-client/demo/embedded/0cc93d57-456e-4814-8506-a27977435c52">
		</iframe>
	</div> -->

	<a href="#" id="startChat"
		style="background-color: #8C001A; font-size: large; color: white; position: fixed; bottom: 0px; right: 0px; width: 356px; float: left; text-align: -webkit-center; text-decoration: none;">Chat
		Support</a>

	<div id="chatwindow"
		style="position: fixed; bottom: 0px; right: 0px; height: 500px; width: 350px; background-color: white; border-style: solid; border-width: medium; border-color: #800020; display: none;">
		<a
			style="background-color: #8C001A; font-size: large; color: white; position: fixed; bottom: 502px; width: 356px; float: left; right: inherit; text-align: -webkit-center; text-decoration: none;"
			href="#" class="closeChat">Need Help? </a>
		<div style="overflow-y: auto; height: 470px;" id="mainChatScrollDiv">
			<div>
				<img
					src="<c:url value="/resources/images/customer-service-icon.png" />"
					style="width: 35px; padding-left: 7px" />

				<div class="talk-bubble tri-right left-in">
					<div class="talktext">
						<p>Hello! How may I help you?</p>
					</div>
				</div>
			</div>
			<div class="botDiv">
				<img
					src="<c:url value="/resources/images/customer-service-icon.png" />"
					style="width: 35px; padding-left: 7px" />
				<div class="talk-bubble tri-right left-in">
					<div class="talktext">
						<p>Here are few quick links -</p>
						<p style="line-height: 35px;">
							<a href="#" id="stdLinks" data-question="block_credit_card"
								data-userqs="How Do I block my credit card?" class="button"
								data-url='<c:out value=""></c:out>'> Block Credit Card </a>
						</p>
						<p style="line-height: 35px;">
							<a href="#" id="stdLinks" data-question="block_debit_card"
								data-userqs="How Do I block my debit card?" class="button"
								data-url='<c:out value=""></c:out>'> Block Debit Card </a>
						</p>
						<p style="line-height: 35px;">
							<a href="#" id="stdLinks" data-question="reset_pin"
								data-userqs="How do I Reset my debit card PIN?" class="button"
								data-url='<c:out value=""></c:out>'> Reset Debit PIN </a>
						</p>
						<p style="line-height: 35px;">
							<a href="#" id="stdLinks" data-question="interest_rates"
								data-userqs="Interest rates" class="button"
								data-url='<c:out value=""></c:out>'> Interest Rates </a>
						</p>
						<p style="line-height: 35px;">
							<a href="#" id="stdLinks" data-question="branch_address"
								data-userqs="Address of branch" class="button"
								data-url='<c:out value=""></c:out>'> Branch Address </a>
						</p>
						<p style="line-height: 35px;">
							<a href="#" id="stdLinks" data-question="enroll_online"
								data-userqs="Enroll" class="button"
								data-url='<c:out value=""></c:out>'> Enroll Online Banking </a>
						</p>
						<p style="line-height: 35px;">
							<a href="#" id="stdLinks" class="typedQs" data-question=""
								data-userqs="" class="button"
								data-url='<c:out value=""></c:out>'></a>
						</p>
					</div>
				</div>
			</div>

			<%-- <div style="display: none;" class="questionFromUser">
				<img
					src="<c:url value="/resources/images/default_profile_img.png" />"
					style="width: 35px; float: right" />
				<div class="talk-bubble tri-right right-in">
					<div class="talktext">
						<p class="userquestion"></p>
					</div>
				</div>
			</div>

			<div style="display: none;" class="answerFromBot">
				<img
					src="<c:url value="/resources/images/customer-service-icon.png" />"
					style="width: 35px; float: left" />
				<div class="talk-bubble tri-right left-in">
					<div class="talktext">
						<p class="responseFromBot"></p>
					</div>
				</div>
			</div> --%>
		</div>
		<div
			style="bottom: 10 px; position: fixed; height: 30px; width: 305px; font-size: medium; font-family: inherit; float: left; bottom: 3px; padding-right: 45px; border-style: inherit; border-width: thin; border-color: #800020; background-color: antiquewhite;">
			<input type="text" value="" id="inputTextFromUser"
				style="padding-top: 5px; width: 200px; /* padding-right: 10px; */ height: 20px; */ width: 236px; border-left: none; border-bottom: none; border-top: none; /* border-right: none; */ /* float: inherit; */ /* border-right-style: inherit; */ /* border-right-width: inherit; */ background-color: white;">

			&nbsp;<a href="#"
				style="background-color: #800020; color: white; font-size: 10px; padding-left: 5px; padding-bottom: 5px; padding-right: 5px; padding-top: 5px;"
				class="" id="sendButton">Send</a>&nbsp;&nbsp;<a href="#"
				style="background-color: #800020; color: white; font-size: 10px; padding-left: 5px; padding-bottom: 5px; padding-right: 5px; padding-top: 5px;"
				class="" id="clearbutton">Clear</a>
		</div>
		<!-- <textarea
			style="position: fixed; bottom: 0px; height: 70px; width: 396px; font-size: medium; font-family: inherit;"></textarea> -->

	</div>

</body>
</html>