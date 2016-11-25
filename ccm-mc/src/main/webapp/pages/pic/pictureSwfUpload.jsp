

<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>

<link href="../swfupload/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../swfupload/swfupload.js"></script>
<script type="text/javascript" src="../swfupload/swfupload.queue.js"></script>
<script type="text/javascript" src="../swfupload/fileprogress.js"></script>
<script type="text/javascript" src="../swfupload/handlers.js"></script>
<script type="text/javascript">
		var swfu;

		window.onload = function() {
			var settings = {
				flash_url : "../swfupload/swfupload.swf",
				upload_url: "../pictureUpload.do",
				//上传文件的名称
				file_post_name: "file",
				post_params: {"bizId" : "${param.bizId}","bizType" : "${param.bizType}"},
				file_size_limit : "100 MB",
				file_types : "*.*",
				file_types_description : "All Files",
				file_upload_limit : 100,
				file_queue_limit : 0,
				custom_settings : {
					progressTarget : "fsUploadProgress",
					cancelButtonId : "btnCancel"
				},
				debug: false,

				// Button settings
				button_image_url: "../swfupload/TestImageNoText_65x29.png",
				button_width: "65",
				button_height: "29",
				button_placeholder_id: "spanButtonPlaceHolder",
				button_text: '<span class="theFont">选择</span>',
				button_text_style: ".theFont { font-size: 16; }",
				button_text_left_padding: 12,
				button_text_top_padding: 3,
				
				// The event handler functions are defined in handlers.js
				file_queued_handler : fileQueued,
				file_queue_error_handler : fileQueueError,
				file_dialog_complete_handler : fileDialogComplete,
				upload_start_handler : uploadStart,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess,
				upload_complete_handler : uploadComplete,
				queue_complete_handler : queueComplete	// Queue plugin event
			};

			swfu = new SWFUpload(settings);
	     };
	</script>
</head>
<body>

<div id="content">
	<h2>swf上传</h2>
	<h2><a href="picture_normalUpload.do?bizType=${param.bizType}&bizId=${param.bizId}">普通上传</a></h2>
	<form id="uploadForm" name="uploadForm" action="" method="post" enctype="multipart/form-data">
		<p></p>

			<div class="fieldset flash" id="fsUploadProgress">
			<span class="legend">上传队列</span>
			</div>
		<div id="divStatus">0个文件上传</div>
			<div>
				<span id="spanButtonPlaceHolder"></span>
				<input id="btnCancel" type="button" value="停止上传" onclick="swfu.cancelQueue();" disabled="disabled" 
				style="margin-left: 2px; font-size: 8pt; height: 29px;" />
			</div>

	</form>
	  <div class="backsurebtwp"> 
           <a class="surebt" href="picture_batchEdit.do?bizType=${param.bizType}&bizId=${param.bizId}">
             <span>上传完成</span></a>
             </div>
         <div class="clearfix"></div>
</div>
</body>
</html>
